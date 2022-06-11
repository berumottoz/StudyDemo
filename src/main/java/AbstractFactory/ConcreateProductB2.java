package AbstractFactory;

public class ConcreateProductB2 implements ProductB{

    public ConcreateProductB2(){};
    
    @Override
    public String ProductBFunction() {
        return "生产B2产品";
    }
}
