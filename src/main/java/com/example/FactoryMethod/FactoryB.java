package com.example.FactoryMethod;

public class FactoryB implements IFactory{
    @Override
    public IProduct GetProduct() {
        return new ConcreteProductB();
    }
}
