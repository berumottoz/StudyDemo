package AbstractFactory;

public class ConcreateProductA2 implements ProductA{

    public ConcreateProductA2(){};
    
    @Override
    public String ProductAFunction() {
        return "生产A2产品";
    }
}
