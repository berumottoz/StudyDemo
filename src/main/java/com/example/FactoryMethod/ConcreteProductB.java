package FactoryMethod;

public class ConcreteProductB implements IProduct{

    public ConcreteProductB() {}

    @Override
    public String FunctionDescription() {
        return "生产B类商品";
    }
}
