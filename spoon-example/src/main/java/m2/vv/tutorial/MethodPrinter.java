package m2.vv.tutorial;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtType;

public class MethodPrinter {

    public static void main(String[] args) {

        Launcher launcher = new Launcher();
        launcher.addInputResource("./manipulation-target/src/");

        launcher.buildModel();

        CtModel model = launcher.getModel();

        for(CtType type : model.getAllTypes()) {

            if (!type.getSimpleName().equals("Functions")) {
                for(Object method : type.getAllMethods()) {
                    System.out.println(method);
                }
            }
        }

    }

}
