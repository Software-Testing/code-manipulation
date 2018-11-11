package m2.vv.tutorial;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.CtAbstractBiScanner;
import spoon.reflect.visitor.CtAbstractVisitor;
import spoon.reflect.visitor.CtBiScannerDefault;
import spoon.reflect.visitor.CtScanner;
import spoon.support.JavaOutputProcessor;

import java.io.File;

public class InstructionModifier {

    public static void main(String... args) {

        Launcher launcher = new Launcher();
        launcher.addInputResource("./manipulation-target/src/");

        launcher.buildModel();

        CtModel model = launcher.getModel();
        Factory factory = launcher.getFactory();

        CtType functionsClass = model.getElements((CtType type) -> type.getSimpleName().equals("Functions")).get(0);

        CtMethod twiceMethod = functionsClass.getMethod("twice",factory.createCtTypeReference(double.class));


        twiceMethod.accept(new CtScanner() {
            @Override
            public <T> void visitCtBinaryOperator(CtBinaryOperator<T> operator) {
                super.visitCtBinaryOperator(operator);
                if(operator.getKind() == BinaryOperatorKind.MUL)
                    operator.setKind(BinaryOperatorKind.PLUS);
            }
        });


        JavaOutputProcessor processor = new JavaOutputProcessor(new File("./manipulation-target/target/generated-sources/"), launcher.createPrettyPrinter());
        processor.setFactory(launcher.getFactory());
        processor.createJavaFile(functionsClass);
    }

}
