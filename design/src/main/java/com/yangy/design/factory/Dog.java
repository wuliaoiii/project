package com.yangy.design.factory;

/**
 * @author yang yang
 * @create 2018/8/20
 * @since 1.0.0
 */
public class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("dog eat : ");
    }

    @Override
    public void sleep() {
        System.out.println("dog sleep : ");
    }

    @Override
    public void run() {
        System.out.println("dog run : ");
    }
}