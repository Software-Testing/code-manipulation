package m2.vv.tutorial;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class Tracer {


    public static void main(String... args) throws Exception {

        ClassPool pool = ClassPool.getDefault();

        final String folder = "./manipulation-target/target/classes/";
        pool.appendClassPath(folder);

        CtClass functions = pool.get("m2.vv.tutorial.Functions");

        for(CtMethod method : functions.getDeclaredMethods()) {
            String instruction = String.format("{System.out.println(\"%s\");}", method.getName() + method.getMethodInfo().getDescriptor());
            method.insertBefore(instruction);
        }
        functions.writeFile(folder);


    }

}