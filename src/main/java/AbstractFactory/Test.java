package AbstractFactory;

public class Test {
    public static void main(String[] args) {
        Factory1 f1 = new Factory1();
        System.out.println(f1.GetProductA().ProductAFunction());
        System.out.println(f1.GetProductB().ProductBFunction());

        Factory2 f2 = new Factory2();
        System.out.println(f2.GetProductA().ProductAFunction());
        System.out.println(f2.GetProductB().ProductBFunction());
    }
}
