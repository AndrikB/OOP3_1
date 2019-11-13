package reflection;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClassInfoTest {

    @Test
    public void printConstructorsTest() {
        Class cl = myTestClass.class;
        String result = ClassInfo.printConstructors(cl);
        String expected = "   public reflection.myTestClass();\n" +
                "   private reflection.myTestClass(int, double);\n";
        assertEquals(result, expected);
        result = ClassInfo.printConstructors(TestClass.class);
        expected = "   reflection.TestClass();\n";
        assertEquals(result, expected);
    }

    @Test
    public void printMethodsTest() {
        String result = ClassInfo.printMethods(myTestClass.class);
        String expected = "   @jdk.nashorn.internal.ir.annotations.Ignore()\n" +
                "   private synchronized void func2(int);\n";
        assertEquals(result, expected);
        result = ClassInfo.printMethods(TestClass.class);
        expected = "   int func();\n";
        assertEquals(result, expected);
    }

    @Test
    public void printFieldsTest() {
        String result = ClassInfo.printFields(myTestClass.class);
        String expected = "   private int x;\n" +
                "   volatile java.util.Date d;\n";
        assertEquals(result, expected);
        result = ClassInfo.printFields(TestClass.class);
        expected = "";
        assertEquals(result, expected);
    }

    @Test
    public void printInfoTest() {
        String result = null;
        try {
            result = ClassInfo.printInfo(TestClass.class.getName());
        } catch (ClassNotFoundException e) {}
        String expected = "class reflection.TestClass\n" +
                "{\n" +
                "   reflection.TestClass();\n" +
                "\n" +
                "\n" +
                "   int func();\n" +
                "\n" +
                "\n" +
                "\n" +
                "}";
        assertEquals(result, expected);
    }
}
