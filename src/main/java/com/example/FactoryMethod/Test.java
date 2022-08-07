package com.example.FactoryMethod;

public class Test {

    public static void main(String[] args) {
        FactoryA a = new FactoryA();
        System.out.println(a.GetProduct().FunctionDescription());

        FactoryB b = new FactoryB();
        System.out.println(b.GetProduct().FunctionDescription());
    }
}
