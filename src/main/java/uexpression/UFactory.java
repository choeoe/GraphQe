package uexpression;

import java.util.HashSet;

/**
 * @className: UFactory
 * @description: done
 * @author: iscas
 * @date: 2023/07/04 15:42
 * @Company: Copyright© 2022/10/17 by iscas
 **/
public class UFactory {
    private final static HashSet<String> labels = new HashSet<>();
    private final static HashSet<String> attrs = new HashSet<>();

    public static void addLabel(String name){
        labels.add(name);
    }

    public static void addAttr(String name){
        attrs.add(name);
    }
}
