package reflection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name;

        if (args.length > 0) name = args[0];
        else
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name: ");
            name = in.next();//reflection.*className*
            System.out.println(name);
        }
        try {
            System.out.println(ClassInfo.printInfo(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
