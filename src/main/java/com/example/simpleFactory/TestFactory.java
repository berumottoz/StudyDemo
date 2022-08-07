package com.example.simpleFactory;

public class TestFactory {

    public TestFactory() {
    }

    public IProduct GetProduct(ProductList list) {
        IProduct product = null;
        switch (list) {
            case ProductA:
                product = new ConcreteProductA();
                break;
            case ProductB:
                product = new ConcreteProductB();
                break;
            default:
                System.out.println("不存在");
        }
        return product;
    }
}
