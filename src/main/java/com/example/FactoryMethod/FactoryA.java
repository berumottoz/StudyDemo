package FactoryMethod;

public class FactoryA implements IFactory{
    @Override
    public IProduct GetProduct() {
        return new ConcreteProductA();
    }
}
