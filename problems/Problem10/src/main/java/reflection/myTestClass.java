package reflection;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Date;

public class myTestClass {
    private int x;
    volatile Date d;

    public myTestClass() {

    }

    private myTestClass(int x, double y) {

    }
    @Ignore
    private synchronized void func2(int e){}

}

class TestClass {
    int func() {
        return 4;
    }
}