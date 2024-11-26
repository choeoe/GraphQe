package uexpression;

/**
 * @className: UBinary
 * @description: done
 * @author: iscas
 * @date: 2023/07/03 16:18
 * @Company: Copyright© 2022/10/17 by iscas
 **/
public class UBinary implements UExpr {
    public UExpr l;
    public UExpr r;

    public UBinary(UExpr l, UExpr r) {
        this.l = l;
        this.r = r;
    }

    public static class UEq extends UBinary {
        public UEq(UExpr le, UExpr re) {
            super(le, re);
        }

        @Override
        public String toString() {
            return "[" + l + " = " + r + "]";
        }
    }

    public static class UMulti extends UBinary {

        @Override
        public String toString() {
            return l + "x" + r;
        }

        public UMulti(UExpr l, UExpr r) {
            super(l, r);
        }
    }

    public static class Project extends UBinary {
        public String name;

        public Project(UExpr from, UExpr to, String name) {
            super(from, to);
            this.name = name;
        }

        @Override
        public String toString() {
            return "[" + l + "=" + name + "(" + r + ")]";
        }
    }
}
