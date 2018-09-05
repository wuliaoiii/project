package com.yangy.design.singleton;

/**
 * @author yang yang
 * @create 2018/8/20
 * @since 1.0.0
 */
public class Lazy {

    public static Lazy lazy;

    private Lazy() {
    }

    public static Lazy getInstance() {
        if (null == lazy) {
            lazy = new Lazy();
        }
        return lazy;
    }
}