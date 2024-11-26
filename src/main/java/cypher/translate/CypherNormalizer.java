package cypher.translate;

import cypher.ast.QueryPart;
import cypher.ast.SingleQuery;
import cypher.ast.Statement;
import cypher.ast.Union;
import cypher.ast.clause.Clause;
import cypher.ast.clause.Unwind;
import cypher.ast.clause.Where;
import cypher.ast.clause.match.Match;
import cypher.ast.clause.match.pattern.*;
import cypher.ast.clause.projection.*;
import cypher.ast.expression.*;
import cypher.err.CypherNormalizationError;
import cypher.err.CypherSemanticError;
import cypher.Pair;

import java.util.*;

public class CypherNormalizer {
    private static boolean success;
    private static Map<String, Integer> strMap = new HashMap<>();

    private static int strValue = 1000;
    private static int postFix;

    private static List<Pair<Variable, Variable>> eqVars = new ArrayList<>();
    public static <T> T findInList(List<T> list, T target) {
        for (T element : list) {
            if (element.equals(target)) {
                return element;
            }
        }
        return null;
    }

    public static void standardizingReferences(Statement stmt, String prefix) {
        postFix = 1;
        standardize(stmt.query.part, prefix);
        if (!eqVars.isEmpty()) {
            for (Pair<Variable,Variable> pair : eqVars) {
                Variable v1 = pair.getLeft();
                Variable v2 = pair.getRight();
//                v2.name = v1.name;
                v1.linked = v2;
            }
        }
        eqVars.clear();
//        standardizingReferences(stmt, prefix);
    }

    private static void standardize(QueryPart part, String prefix) {
        if (part instanceof Union union) {
            standardize(union.lhs, prefix);
            standardize(union.rhs, prefix);
        } else if (part instanceof SingleQuery singleQuery) {
            standardize(singleQuery, prefix);
        }
    }

    private static void standardize(SingleQuery query, String prefix) {
        List<Variable> vars = new ArrayList<>();
        for (Clause clause : query.clauses) {
            if (clause instanceof Match match) {
                Pattern pattern = match.pattern;
                Where where = match.where.orElse(null);
                // TODO 对于有变量的pattern处理
                for (PatternPart part : pattern.patternParts) {
                    if (part instanceof NamedPatternPart namedPatternPart) {
                        vars.add(namedPatternPart.variable);
                        standardizeGraphPattern(vars, namedPatternPart.variable, part.element);
                    }else {
                        standardizeGraphPattern(vars, null, part.element);
                    }
                }
                // TODO parse where
                if (where != null) {
                    where.expression = standardizeExpr(vars, where.expression);
                }
            } else if (clause instanceof Return ret) {
                for (ReturnItem item : ret.returnItems) {
                    item.expression = standardizeExpr(vars, item.expression);
                    if (item instanceof ReturnItem.Aliased aliased) {
                        vars.add(aliased.alias);
                        aliased.alias.linked = item.expression;
                    }
                }
                if (ret.orderBy.isPresent()) {
                    for (SortItem sortItem : ret.orderBy.get().sortItems) {
                        sortItem.expression = standardizeExpr(vars, sortItem.expression);
                    }
                }
                ret.limit.ifPresent(limit -> limit.expression = standardizeExpr(vars, limit.expression));
            } else if (clause instanceof With w) {
                for (ReturnItem item : w.returnItems) {
                    item.expression = standardizeExpr(vars, item.expression);
                    if (item instanceof ReturnItem.Aliased aliased) {
                        vars.add(aliased.alias);
                        aliased.alias.linked = item.expression;
                    }
                }
                if (w.orderBy.isPresent()) {
                    for (SortItem sortItem : w.orderBy.get().sortItems) {
                        sortItem.expression = standardizeExpr(vars, sortItem.expression);
                    }
                }
                w.where.ifPresent(where -> standardizeExpr(vars, where.expression));
            } else if (clause instanceof Unwind unwind) {
                unwind.expr = standardizeExpr(vars, unwind.expr);
                if (unwind.expr instanceof Variable) {
                    ((Variable) unwind.expr).addSubVar(unwind.alias);
                }
                vars.add(unwind.alias);
            }
        }
        for (Variable var : vars) {
            var.name = prefix + postFix++;
        }

    }

    private static boolean isId(Expression expr) {
        if (expr instanceof FunctionInvocation functionInvocation) {
            return functionInvocation.functionName.name.equalsIgnoreCase("id")
                    && functionInvocation.args.get(0) instanceof Variable;
        }
        return false;
    }
    private static Expression standardizeExpr(List<Variable> vars, Expression expr) {
        if (expr instanceof Variable var) {
            return findInList(vars, var);
        } else if (expr instanceof Binary binary) {
            binary.lhs = standardizeExpr(vars, binary.lhs);
            binary.rhs = standardizeExpr(vars, binary.rhs);
            if (binary instanceof Binary.Equals) {
                if (isId(binary.lhs) && isId(binary.rhs)) {
                    binary.lhs = ((FunctionInvocation) binary.lhs).args.get(0);
                    binary.rhs = ((FunctionInvocation) binary.rhs).args.get(0);
                }
                if (binary.lhs instanceof Variable v1 && binary.rhs instanceof Variable v2) {
                    eqVars.add(new Pair<>(v1, v2));
                }
            }
            return binary;
        } else if (expr instanceof Unary unary) {
            unary.lhs = standardizeExpr(vars, unary.lhs);
            return unary;
        } else if (expr instanceof Property prop) {
            prop.map = standardizeExpr(vars, prop.map);
            return prop;
        } else if (expr instanceof FunctionInvocation functionInvocation) {
            if (functionInvocation.functionName.toString().equalsIgnoreCase("tolower")) {
                List<Expression> args = functionInvocation.args;
                assert args.size() == 1;
                String value = args.get(0).toString().toLowerCase();
//                return new Literal.StringLiteral(value);
                return new Literal.UnsignedInteger(value.hashCode());
            }
            if (functionInvocation.functionName.toString().equalsIgnoreCase("toupper")) {
                List<Expression> args = functionInvocation.args;
                assert args.size() == 1;
                String value = args.get(0).toString().toUpperCase();
//                return new Literal.StringLiteral(value);
                return new Literal.UnsignedInteger(value.hashCode());
            }
            if (functionInvocation.functionName.toString().equalsIgnoreCase("size")) {
                List<Expression> args = functionInvocation.args;
                assert args.size() == 1;
                int value = args.get(0).toString().length();
                return new Literal.Integer(value);
            }
            if (functionInvocation.functionName.toString().equalsIgnoreCase("trim")) {
                List<Expression> args = functionInvocation.args;
                assert args.size() == 1;
                String value = args.get(0).toString().trim();
//                return new Literal.StringLiteral(value);
                return new Literal.UnsignedInteger(value.hashCode());
            }
            functionInvocation.args.replaceAll(expression -> standardizeExpr(vars, expression));
            return functionInvocation;
        } else if (expr instanceof ListExpression listExpression) {
            listExpression.exprs.replaceAll(expression -> standardizeExpr(vars, expression));
            return listExpression;
        } else if (expr instanceof MapExpression mapExpression) {
//            mapExpression.props.stream().map(t->t.setValue(standardizeExpr(vars, t.getRight())));
            List<Pair<PropertyKeyName, Expression>> newProps = new ArrayList<>();
            for (Pair<PropertyKeyName, Expression> prop : mapExpression.props) {
                newProps.add(new Pair<>(prop.getLeft(), standardizeExpr(vars, prop.getRight())));
            }
            mapExpression.props = newProps;
            return mapExpression;
        } else if (expr instanceof Literal.StringLiteral stringLiteral) {
            String str = stringLiteral.stringValue;
            if (!strMap.containsKey(str)) {
                strMap.put(str, strValue++);
            }
            return new Literal.UnsignedInteger(str.hashCode());
//            str = str.replaceAll("^['\"]+|['\"]+$", "");
//            stringLiteral.stringValue = str;
//            return expr;
        } else if (expr instanceof RelTypeExpression relTypeExpression) {
            relTypeExpression.var = findInList(vars, relTypeExpression.var);
            return relTypeExpression;
        } else if (expr instanceof LabelExpression labelExpression) {
            labelExpression.var = findInList(vars, labelExpression.var);
            return labelExpression;
        } else if (expr instanceof CaseExpression caseExpression) {
            if (caseExpression.expression.isPresent()) {
                caseExpression.expression = Optional.of(standardizeExpr(vars, caseExpression.expression.get()));
            }
            if (caseExpression.default_.isPresent()) {
                caseExpression.default_ = Optional.of(standardizeExpr(vars, caseExpression.default_.get()));
            }
            List<Pair<Expression, Expression>> newalts = new ArrayList<>();
            for (Pair<Expression, Expression> alt : caseExpression.alternatives) {
                newalts.add(new Pair<>(standardizeExpr(vars, alt.getLeft()), standardizeExpr(vars, alt.getRight())));
            }
            caseExpression.alternatives.clear();
            caseExpression.alternatives.addAll(newalts);
            return caseExpression;
        } else if (expr instanceof ExistsFunction e) {
            if (e.arg != null) {
                e.arg = standardizeExpr(vars, e.arg);
            } else {
                Match match = e.match;
                Pattern pattern = match.pattern;
                Where where = match.where.orElse(null);
                // TODO 对于有变量的pattern处理
                for (PatternPart part : pattern.patternParts) {
                    if (part instanceof NamedPatternPart namedPatternPart) {
                        vars.add(namedPatternPart.variable);
                        standardizeGraphPattern(vars, namedPatternPart.variable, part.element);
                    }else {
                        standardizeGraphPattern(vars, null, part.element);
                    }
                }
                // TODO parse where
                if (where != null) {
                    where.expression = standardizeExpr(vars, where.expression);
                }
            }
            return e;
        } else if (expr instanceof PatternElement element) {
            if (element instanceof NodePattern node) {
                if (node.variable.isPresent()) {
                    if (findInList(vars, node.variable.get()) != null) {
                        node.variable = Optional.ofNullable(findInList(vars, node.variable.get()));
                    } else {
                        vars.add(node.variable.get());
                    }
                } else {
                    Variable var = new Variable();
                    node.variable = Optional.of(var);
                    vars.add(var);
                }
                return node;
            } else if (element instanceof RelationshipChain chain) {
                while (true) {
                    chain.rightNode = (NodePattern) standardizeExpr(vars, chain.rightNode);
                    RelationshipPattern rel = chain.relationship;
                    if (rel.variable.isPresent()) {
                        if (findInList(vars, rel.variable.get()) != null) {
                            rel.variable = Optional.ofNullable(findInList(vars, rel.variable.get()));
                        } else {
                            vars.add(rel.variable.get());
                        }
                    } else {
                        Variable var = new Variable();
                        rel.variable = Optional.of(var);
                        vars.add(var);
                    }
                    if (chain.element instanceof RelationshipChain chain1) {
                        chain = chain1;
                    } else {
                        chain.element = (PatternElement) standardizeExpr(vars, chain.element);
                        break;
                    }
                }
                return chain;
            }
            return element;
        } else {
            return expr;
        }
    }

    private static void standardizeGraphPattern(List<Variable> vars, Variable parent, PatternElement element) {
        if (element instanceof NodePattern node) {
            if (node.variable.isPresent()) {
                Variable var = findInList(vars, node.variable.get());
                if (var != null) {
                    node.variable = Optional.of(var);
                } else {
                    vars.add(node.variable.get());
                }
                if (parent != null) {
                    parent.addSubVar(node.variable.get());
                }
                if (node.properties.isPresent()) {
                    node.properties = Optional.of(((MapExpression) standardizeExpr(vars, node.properties.get())));
                }
            } else {
                Variable var = new Variable();
                node.variable = Optional.of(var);
                vars.add(var);
                if (parent != null) {
                    parent.addSubVar(var);
                }
            }
        } else if (element instanceof RelationshipChain chain) {
            while (true) {
                NodePattern node = chain.rightNode;
                if (node.variable.isPresent()) {
                    Variable var = findInList(vars, node.variable.get());
                    if (var != null) {
                        node.variable = Optional.of(var);
                    } else {
                        vars.add(node.variable.get());
                    }
                    if (parent != null) {
                        parent.addSubVar(node.variable.get());
                    }
                } else {
                    Variable var = new Variable();
                    node.variable = Optional.of(var);
                    vars.add(var);
                    if (parent != null) {
                        parent.addSubVar(var);
                    }
                }
                if (node.properties.isPresent()) {
                    node.properties = Optional.of(((MapExpression) standardizeExpr(vars, node.properties.get())));
                }
                RelationshipPattern rel = chain.relationship;
                if (rel.variable.isPresent()) {
                    Variable var = findInList(vars, rel.variable.get());
                    if (var != null) {
                        rel.variable = Optional.of(var);
                    } else {
                        vars.add(rel.variable.get());
                    }
                    if (parent != null) {
                        parent.addSubVar(rel.variable.get());
                    }
                } else {
                    Variable var = new Variable();
                    rel.variable = Optional.of(var);
                    vars.add(var);
                    if (parent != null) {
                        parent.addSubVar(var);
                    }
                }
                if (rel.properties.isPresent()) {
                    rel.properties = Optional.of(((MapExpression) standardizeExpr(vars, rel.properties.get())));
                }
                if (chain.element instanceof RelationshipChain chain1) {
                    chain = chain1;
                } else {
                    NodePattern n = ((NodePattern) chain.element);
                    if (n.variable.isPresent()) {
                        Variable var = findInList(vars, n.variable.get());
                        if (var != null) {
                            n.variable = Optional.of(var);
                        } else {
                            vars.add(n.variable.get());
                        }
                        if (parent != null) {
                            parent.addSubVar(n.variable.get());
                        }
                    } else {
                        Variable var = new Variable();
                        n.variable = Optional.of(var);
                        vars.add(var);
                        if (parent != null) {
                            parent.addSubVar(var);
                        }
                    }
                    if (n.properties.isPresent()) {
                        n.properties = Optional.of(((MapExpression) standardizeExpr(vars, n.properties.get())));
                    }
                    break;
                }
            }
        }
    }

    public static void eliminatingNormalize(Statement stmt) {
        success = true;
        while (success) {
            QueryPart part = stmt.query.part;
            stmt.query.part = eliminatingNormalize(part, 1);
        }
        success = true;
        while (success) {
            QueryPart part = stmt.query.part;
            stmt.query.part = eliminatingNormalize(part, 2);
        }
    }

    private static QueryPart eliminatingNormalize(QueryPart part, int type) {
        if (part instanceof Union union) {
            union.lhs = eliminatingNormalize(union.lhs, type);
            union.rhs = eliminatingNormalize(union.rhs, type);
            return union;
        } else if (part instanceof SingleQuery singleQuery) {
            if (type == 1) {
                return eliminatingUndirectedRel(singleQuery);
            }
            if (type == 2) {
                return eliminatingVariableLengthPath(singleQuery);
            }
        }
        return part;
    }

    private static QueryPart eliminatingUndirectedRel(SingleQuery singleQuery) {
        List<Clause> clauses = singleQuery.clauses;
        for (Clause clause : clauses) {
            if (clause instanceof Match match) {
                Pattern pattern = match.pattern;
                for (PatternPart patternPart : pattern.patternParts) {
                    if (patternPart.element instanceof RelationshipChain chain) {
                        while (true) {
                            if (chain.relationship.direction == RelationshipPattern.SemanticDirection.BOTH) {
                                System.out.println("undirected");
                                RelationshipPattern rel = chain.relationship;
                                Union.All all = new Union.All(singleQuery, singleQuery.copy());
                                rel.clone.direction = RelationshipPattern.SemanticDirection.INCOMING;
                                rel.direction = RelationshipPattern.SemanticDirection.OUTGOING;
                                success = true;
                                return all;
                            }
                            if (chain.element instanceof RelationshipChain chain1) {
                                chain = chain1;
                            } else {
                                break;
                            }
                        }
                    }
                }
                if (match.where.isPresent()) {
                    Expression expr = match.where.get().expression;
                    match.where.get().expression = eliminateExpr(expr);
                }
            }
        }
        success = false;
        return singleQuery;
    }

    private static Expression eliminateExpr(Expression expr) {
        if (expr instanceof Binary binary) {
            binary.lhs = eliminateExpr(binary.lhs);
            binary.rhs = eliminateExpr(binary.rhs);
            return binary;
        } else if (expr instanceof Unary u) {
            u.lhs = eliminateExpr(u.lhs);
            return u;
        } else if (expr instanceof PatternElement element) {
            if (element instanceof RelationshipChain chain) {
                while (true) {
                    RelationshipPattern rel = chain.relationship;
                    if (rel.direction.equals(RelationshipPattern.SemanticDirection.BOTH)) {
                        Expression clone = expr.copy();
                        rel.direction = RelationshipPattern.SemanticDirection.INCOMING;
                        rel.clone.direction = RelationshipPattern.SemanticDirection.OUTGOING;
                        success = true;
                        return new Binary.Or(expr, clone);
                    }
                    if (chain.element instanceof RelationshipChain chain1) {
                        chain = chain1;
                    } else {
                        break;
                    }
                }
            }
        }
        success = false;
        return expr;
    }

    private static QueryPart eliminatingVariableLengthPath(SingleQuery singleQuery) {
        List<Clause> clauses = singleQuery.clauses;
        for (Clause clause : clauses) {
            if (clause instanceof Match match) {
                Pattern pattern = match.pattern;
                for (PatternPart patternPart : pattern.patternParts) {
                    if (patternPart.element instanceof RelationshipChain chain) {
                        while (true) {
                            if (chain.relationship.length.isPresent()) {
                                success = true;
                                RelationshipPattern relationship = chain.relationship;
                                if (relationship.length.get().isPresent()) {
                                    System.out.println("var-length");
                                    Range range = chain.relationship.length.get().get();
                                    relationship.length = Optional.empty();
                                    long lower = range.lower.get().value;
                                    if (range.upper.isEmpty()) {
                                        for (long i = 0; i < lower; i++) {
                                            RelationshipPattern rel = new RelationshipPattern(null,
                                                    null, relationship.properties.orElse(null
                                            ), relationship.types);
                                            rel.direction = relationship.direction;
                                            NodePattern node = new NodePattern(null, null, null);
                                            RelationshipChain tmpChain = new RelationshipChain(chain, rel, chain.rightNode);
                                            chain.rightNode = node;
                                            chain = tmpChain;
                                        }
                                        patternPart.element = chain;
                                        return singleQuery;
                                    } else {
                                        long upper = range.upper.get().value;
                                        QueryPart tmpPart = singleQuery;
                                        for (long i = lower; i < upper; i++) {
                                            SingleQuery q1 = singleQuery.copy();
                                            RelationshipChain tChain = chain.clone;
                                            for (long j = 0; j < i; j++) {
                                                RelationshipPattern rel = new RelationshipPattern(null,
                                                        null, relationship.properties.orElse(null
                                                ), relationship.types);
                                                rel.direction = relationship.direction;
                                                NodePattern node = new NodePattern(null, null, null);
                                                tChain.element = new RelationshipChain(tChain.element, rel, node);
                                            }
                                            tmpPart = new Union.All(tmpPart, q1);
                                        }
                                        return tmpPart;
                                    }
                                }
                            }
                            if (chain.element instanceof RelationshipChain chain1) {
                                chain = chain1;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        success = false;
        return singleQuery;
    }

    public static void returnNormalize(Statement stmt) {
        QueryPart part = stmt.query.part;
        returnNormalize(part);
    }

    private static void returnNormalize(QueryPart part) {
        if (part instanceof Union union) {
            returnNormalize(union.lhs);
            returnNormalize(union.rhs);
        } else if (part instanceof SingleQuery singleQuery) {
            replaceAsterisk(singleQuery);
        }
    }

    private static void replaceAsterisk(SingleQuery query) {
        List<Clause> clauses = query.clauses;
        Return re = null;
        boolean needReplacing = false;
        for (Clause clause : clauses) {
            if (clause instanceof Return ret) {
                re = ret;
                if (ret.returnItems.size() == 1) {
                    if (ret.returnItems.get(0).expression instanceof Literal.Star) {
                        needReplacing = true;
                    }
                }
            }
        }
        if (needReplacing) {
            List<Variable> vars = new ArrayList<>();
            for (Clause clause : clauses) {
                if (clause instanceof Match match) {
                    Pattern pattern = match.pattern;
                    for (PatternPart part : pattern.patternParts) {
                        PatternElement element = part.element;
                        while (element instanceof RelationshipChain chain) {
                            chain.relationship.variable.ifPresent(vars::add);
                            chain.rightNode.variable.ifPresent(vars::add);
                            element = chain.element;
                        }
                        ((NodePattern) element).variable.ifPresent(vars::add);
                    }
                }
                if (clause instanceof With with) {
                    List<ReturnItem> returnItems = with.returnItems;
                }
            }
            if (re != null) {
                List<ReturnItem.Unaliased> returnItems = vars.stream().map(x -> new ReturnItem.Unaliased(x, x.toString())).toList();
                re.returnItems.clear();
                re.returnItems.addAll(returnItems);
            } else {
                throw new CypherSemanticError("Missing RETURN clause");
            }
        }
    }

    public static void returnPathNormalize(Statement stmt) {
        QueryPart part = stmt.query.part;
        returnPathNormalize(part);
    }

    private static void returnPathNormalize(QueryPart part) {
        if (part instanceof Union union) {
            returnPathNormalize(union.lhs);
            returnPathNormalize(union.rhs);
        } else if (part instanceof SingleQuery singleQuery) {
            replacePath(singleQuery);
        }
    }

    private static void replacePath(SingleQuery query) {
        List<Clause> clauses = query.clauses;
        for (Clause clause : clauses) {
            if (clause instanceof Return ret) {
                for (int i = 0; i < ret.returnItems.size(); i++) {
                    if (ret.returnItems.get(i).expression instanceof Variable var && var.getSubVars().size() > 0) {
                        ret.returnItems.get(i).expression = var.getSubVars().get(var.getSubVars().size() - 1);
                        ret.returnItems.add(i+1,new ReturnItem.Unaliased(var.getSubVars().get(0),
                                var.getSubVars().get(0).toString()));
                    }
                }
            }
        }
    }
    public static void advancedExpressionRewriting(Statement stmt) {
        QueryPart part = stmt.query.part;
        advancedExpressionRewriting(part);
    }

    private static void advancedExpressionRewriting(QueryPart part) {
        if (part instanceof SingleQuery singleQuery) {
            advancedExpressionRewriting(singleQuery);
        }
    }

    private static void advancedExpressionRewriting(SingleQuery part) {
        List<Clause> clauses = part.clauses;
        List<Match> matches = new ArrayList<>();
        Return ret = null;
        for (Clause clause : clauses) {
            if (clause instanceof Match m) {
                matches.add(m);
            }
            if (clause instanceof Return r) {
                ret = r;
            }
        }
        if (matches.size() == 0) {
            throw new CypherSemanticError("Missing MATCH clause!");
        }
        if (ret == null) {
            throw new CypherSemanticError("Missing RETURN clause!");
        }
        while (true) {
            int advancedIndex = -1;
            int matchIndex = -1;
            for (int j = 0; j < matches.size(); j++) {
                for (int i = 0; i < matches.get(j).pattern.patternParts.size(); i++) {
                    if (matches.get(j).pattern.patternParts.get(i) instanceof ShortestPathFunc) {
                        advancedIndex = i;
                        matchIndex = j;
                        break;
                    }
                }
            }
            if (advancedIndex == -1) {
                break;
            } else {
                Match match = matches.get(matchIndex);
                ShortestPathFunc func = ((ShortestPathFunc) match.pattern.patternParts.get(advancedIndex));
                //TODO case 1: func has var and is returned, check if variable is combined
                //TODO case 2: func has no var
                // Case1
                if (func.variable != null) {
                    int retIndex = -1;
                    Variable var = func.variable;
                    NamedPatternPart namedPatternPart = new NamedPatternPart(func.element, var);
                    for (int i = 0; i < ret.returnItems.size(); i++) {
                        if (ret.returnItems.get(i).expression instanceof Variable variable) {
                            if (var.equals(variable)) {
                                retIndex = i;
                            }
                        }
                        if (retIndex != -1) {
                            SortItem orderItem = new SortItem.Asc(
                                    new FunctionInvocation(new FunctionName("length"), false,
                                            Collections.singletonList(var)));
                            OrderBy orderBy = new OrderBy(Collections.singletonList(orderItem));
                            ReturnItem withItem = new ReturnItem.Unaliased(var, var.toString());
                            With with = new With(false, Collections.singletonList(withItem),
                                    orderBy, null, null, null);
                            match.pattern.patternParts.set(advancedIndex, namedPatternPart);
                            part.clauses.add(matchIndex + 1, with);
                        }
                    }
                }
            }
        }
    }

    public static void caseNormalize(Statement stmt) {
        QueryPart part = stmt.query.part;
        stmt.query.part = caseNormalize(part);
    }

    private static QueryPart caseNormalize(QueryPart part) {
        if (part instanceof Union union) {
            union.lhs = caseNormalize(union.lhs);
            union.rhs = caseNormalize(union.rhs);
            return union;
        } else if (part instanceof SingleQuery singleQuery) {
            return eliminateCase(singleQuery);
        }
        return null;
    }

    private static QueryPart eliminateCase(SingleQuery singleQuery) {
        List<Clause> clauses = singleQuery.clauses;
        Return ret = null;
        Match match = null;
        for (Clause clause : clauses) {
            if (clause instanceof Return r) {
                ret = r;
            }
            if (clause instanceof Match m) {
                match = m;
            }
        }
        if (ret == null) {
            throw new CypherNormalizationError("Missing Return Clause!");
        }
        if (match == null) {
            return singleQuery;
        }
        ret.returnItems = new ArrayList<>(ret.returnItems);
        for (int i = 0; i < ret.returnItems.size(); i++) {
            ReturnItem item = ret.returnItems.get(i);
            if (item.expression instanceof CaseExpression caseExpression) {
                SingleQuery c = singleQuery.copy();
                ret.clone.returnItems.remove(i);
                Expression easy = caseExpression.expression.orElse(null);
                if (caseExpression.alternatives.size() == 1) {
                    if (easy != null) {
                        Binary.Equals equals = new Binary.Equals(easy, caseExpression.alternatives.get(0).getLeft());
                        if (match.clone.where.isEmpty()) {
                            match.clone.where = Optional.of(new Where(equals));
                        } else {
                            match.clone.where.get().expression = new Binary.And(match.clone.where.get().expression, equals);
                        }
                        ret.clone.returnItems.add(i, new ReturnItem.Unaliased(caseExpression.alternatives.get(0).getRight(),
                                caseExpression.alternatives.get(0).getRight().toString()));
                    } else {
                        if (match.clone.where.isEmpty()) {
                            match.clone.where = Optional.of(new Where(caseExpression.alternatives.get(0).getLeft()));
                        } else {
                            match.clone.where.get().expression = new Binary.And(match.clone.where.get().expression,
                                    caseExpression.alternatives.get(0).getLeft());
                        }
                        ret.clone.returnItems.add(i, new ReturnItem.Unaliased(caseExpression.alternatives.get(0).getRight(),
                                caseExpression.alternatives.get(0).getRight().toString()));
                    }
                    if (caseExpression.default_.isPresent()) {
                        SingleQuery def = singleQuery.copy();
                        ret.clone.returnItems.remove(i);
                        if (match.clone.where.isEmpty()) {
                            match.clone.where = Optional.of(new Where(new Unary.Not(caseExpression.alternatives.get(0).getLeft())));
                        } else {
                            match.clone.where.get().expression = new Binary.And(match.clone.where.get().expression,
                                    new Unary.Not(caseExpression.alternatives.get(0).getLeft()));
                        }
                        ret.clone.returnItems.add(i, new ReturnItem.Unaliased(caseExpression.default_.get(),
                                caseExpression.default_.get().toString()));
                        success = true;
                        return new Union.All(c, def);
                    }
                } else {
                    List<SingleQuery> queries = new ArrayList<>();
                    for (int j = 0; j < caseExpression.alternatives.size(); j++) {
                        SingleQuery clone = singleQuery.copy();
                        singleQuery = clone;
                        queries.add(clone);
                    }
                    ret = ret.clone;
                    match = match.clone;
                    int epoch = 0;
                    while (ret != null && match != null) {
                        Pair<Expression, Expression> exprPair = caseExpression.alternatives.get(i);
                        if (easy != null) {
                            Binary.Equals equals = new Binary.Equals(easy, caseExpression.alternatives.get(epoch).getLeft());
                            if (match.where.isEmpty()) {
                                match.where = Optional.of(new Where(equals));
                            } else {
                                match.where.get().expression = new Binary.And(match.where.get().expression, equals);
                            }
                            ret.returnItems.add(i, new ReturnItem.Unaliased(caseExpression.alternatives.get(epoch).getRight(),
                                    caseExpression.alternatives.get(epoch).getRight().toString()));
                        } else {
                            if (match.where.isEmpty()) {
                                match.where = Optional.of(new Where(caseExpression.alternatives.get(epoch).getLeft()));
                            } else {
                                match.where.get().expression = new Binary.And(match.where.get().expression,
                                        caseExpression.alternatives.get(epoch).getLeft());
                            }
                            ret.returnItems.add(i, new ReturnItem.Unaliased(caseExpression.alternatives.get(epoch).getRight(),
                                    caseExpression.alternatives.get(epoch).getRight().toString()));
                        }
                        epoch++;
                        ret = ret.clone;
                        match = match.clone;
                    }
                    success = true;
                    return queries.stream().map(q -> ((QueryPart) q)).reduce(Union.All::new).orElse(null);
                }
            }
        }


        success = false;
        return singleQuery;
    }
//
//    private static Union.All assembleAllByCase(SingleQuery singleQuery, int caseIndex) {
//        SingleQuery clone = singleQuery.copy();
//    }


    public static void normalize(Statement stmt, String prefix) {
        returnNormalize(stmt);
        standardizingReferences(stmt, prefix);
//        returnPathNormalize(stmt);
        eliminatingNormalize(stmt);
//        caseNormalize(stmt);
        standardizingReferences(stmt, prefix);
    }
}
