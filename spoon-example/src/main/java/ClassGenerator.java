import com.sun.org.apache.xpath.internal.operations.Mod;
import spoon.Launcher;
import spoon.reflect.code.CtNewClass;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtArrayTypeReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.JavaOutputProcessor;
import spoon.support.reflect.declaration.CtClassImpl;
import spoon.support.reflect.declaration.CtMethodImpl;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClassGenerator {

    public static void main(String[] args) throws Exception {

        Launcher launcher = new Launcher();


        Factory factory = launcher.getFactory();

        CtClass theClass = factory.createClass();

        theClass.setSimpleName("HelloWorld");

        CtMethod mainMethod = factory.createMethod();

        Set modifiers = new HashSet();
        modifiers.add(ModifierKind.STATIC);
        modifiers.add(ModifierKind.PUBLIC);

        mainMethod.setSimpleName("main");
        mainMethod.setModifiers(modifiers);

        CtArrayTypeReference reference = factory.createArrayTypeReference();
        reference.setComponentType(factory.createCtTypeReference(String.class));


        factory.createParameter(
                mainMethod,
               reference,
                "args"
        );

        mainMethod.setBody(factory.createCodeSnippetStatement("System.out.println(\"Hello World\")"));

        theClass.addMethod(mainMethod);

        JavaOutputProcessor processor = new JavaOutputProcessor(new File("output"), launcher.createPrettyPrinter());
        processor.setFactory(factory);
        processor.createJavaFile(theClass);

    }
}
