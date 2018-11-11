package m2.vv.tutorial;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.support.JavaOutputProcessor;

import java.io.File;
import java.util.List;
import java.util.Set;

public class Tracer {

    public static void main(String... args) {

        Launcher launcher = new Launcher();
        launcher.addInputResource("./manipulation-target/src/");

        launcher.buildModel();

        CtModel model = launcher.getModel();

        List<CtType> filteredTypes = model.getElements(
                (CtType type) -> type.getSimpleName().equals("Functions"));

        CtType functionsClass = filteredTypes.get(0);

        Factory factory = launcher.getFactory();

        for(CtMethod method : (Set<CtMethod<?>>)functionsClass.getMethods()) {
            method.getBody().insertBegin(factory.createCodeSnippetStatement("System.out.println(\"" + method.getSimpleName() +  "\")"));
        }

        JavaOutputProcessor processor = new JavaOutputProcessor(new File("./manipulation-target/target/generated-sources/"), launcher.createPrettyPrinter());
        processor.setFactory(factory);
        processor.createJavaFile(functionsClass);
    }
}
