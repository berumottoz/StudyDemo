package com.example.AbstractFactory;

public class ConcreateProductA1 implements ProductA{

    public ConcreateProductA1(){};

    @Override
    public String ProductAFunction() {
        return "生产A1产品";
    }
}
