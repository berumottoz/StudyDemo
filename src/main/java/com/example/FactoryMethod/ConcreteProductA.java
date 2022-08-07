package FactoryMethod;

public class ConcreteProductA implements IProduct{

    public ConcreteProductA() {}

    @Override
    public String FunctionDescription() {
        return "生产A类商品";
    }
}
