package AbstractFactory;

public class ConcreateProductB1 implements ProductB{

    public ConcreateProductB1(){};
    
    @Override
    public String ProductBFunction() {
        return "生产B1产品";
    }
}
