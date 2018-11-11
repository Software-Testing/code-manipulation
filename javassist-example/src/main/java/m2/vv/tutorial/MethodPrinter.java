package m2.vv.tutorial;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class MethodPrinter {

    public static void main(String[] args) throws Exception {

        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath("./manipulation-target/target/classes/");

        CtClass functions = pool.get("m2.vv.tutorial.Functions");

        System.out.println("All methods:");
        for(CtMethod method : functions.getMethods())
            System.out.println(method.getLongName());
        System.out.println("---");

        System.out.println();

        System.out.println("Declared methods:");
        System.out.println("---");

        for(CtMethod method : functions.getDeclaredMethods())
            System.out.println(method.getLongName());


    }
}
