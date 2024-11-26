package representations;

import java.util.List;

public interface GraphAlgebra {
    public enum Kind{
        GRAPH_PATTERN_PART,
        PROJ,
        PROJ_OP,
        SELECTION,
        NATURE_JOIN,
        LEFT_OUTER_JOIN,
        UNION,
        BAG_UNION,
        SET,
        EMPTY_SET,
        DEDUP,
        SORT,
        GROUP,
        UNWIND,
        ALLDIFF,
        BAG,
        TOP,
        VAR
    }

    public abstract List<GraphAlgebra> subAlgebras();


    public abstract Kind kind();

    public default GraphAlgebra copy(){
        return this;
    }
}
