package AbstractFactory;

public class Factory2 implements IFactory {
    @Override
    public ProductA GetProductA() {
        return new ConcreateProductA2();
    }

    @Override
    public ProductB GetProductB() {
        return new ConcreateProductB2();
    }
}
