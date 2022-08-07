package com.example.simpleFactory;

public class Test {
    public static void main(String[] args) {
        TestFactory t = new TestFactory();
        IProduct result = t.GetProduct(ProductList.ProductA);
        System.out.println(result.FunctionDesCription());
    }
}
