import javassist.*;

import java.io.IOException;

public class ClassGenerator {

    public static void main(String... args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        CtClass theClass = pool.makeClass("HelloWorld");

        theClass.addMethod(
                CtNewMethod.make(
                        Modifier.STATIC | Modifier.PUBLIC,
                        CtClass.voidType,
                        "main",
                        new CtClass[]{pool.getCtClass("java.lang.String[]")},
                        new CtClass[0],
                        "{System.out.println(\"Hello world\");}",
                        theClass));

        //theClass.addMethod(CtNewMethod.make("public static void main(java.lang.String[] args){System.out.println(\"Hello World\");}", theClass));

        theClass.writeFile();
    }

}
