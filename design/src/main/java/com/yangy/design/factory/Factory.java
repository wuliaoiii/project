package com.yangy.design.factory;

/**
 * @author yang yang
 * @create 2018/8/20
 * @since 1.0.0
 */
public class Factory {

    public static Animal getAnimal(AnimalEnum animalEnum) {
        switch (animalEnum) {
            case DOG:
                return new Dog();
            case CAT:
                return new Cat();
            default:
                return new Dog();
        }
    }

    public static void main(String[] args) {
        Animal animal = getAnimal(AnimalEnum.CAT);
        System.out.println(animal instanceof Cat);
        animal.eat();
        animal.run();
        animal.sleep();
    }

}