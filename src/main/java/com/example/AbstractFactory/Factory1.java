package AbstractFactory;

public class Factory1 implements IFactory {
    @Override
    public ProductA GetProductA() {
        return new ConcreateProductA1();
    }

    @Override
    public ProductB GetProductB() {
        return new ConcreateProductB1();
    }
}
