package vn.uits.mockito;

/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 2/26/18.
 */

public class Bar {

    public String greet(Foo foo) {
        System.out.println("Bar Foo.greet");
        return foo.greet();
    }

    public void verifyFooConnection(Foo foo) {
        System.out.println("Is Foo available?");
        String response = foo.greet();
        if (!Foo.HELLO_WORLD.equals(response)) {
            System.out.println("No");
            throw new RuntimeException();
        }
        System.out.println("Yes");
    }
}
