package m2.vv.tutorial;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.objectweb.asm.Opcodes.*;

public class EasierClassGenerator {

    public static void main(String... args) throws IOException {

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        writer.visit(V1_8, ACC_PUBLIC, "HelloWorld", null, "java/lang/Object", null);

        Method constructor = Method.getMethod("void <init> ()");
        GeneratorAdapter constructorGenerator = new GeneratorAdapter(ACC_PUBLIC, constructor, null, null, writer);
        constructorGenerator.loadThis();
        constructorGenerator.invokeConstructor(Type.getType(Object.class), constructor);
        constructorGenerator.returnValue();
        constructorGenerator.endMethod();


        Method main = Method.getMethod("void main (String[])");
        GeneratorAdapter mainGenerator = new GeneratorAdapter(ACC_PUBLIC + ACC_STATIC, main, null, null, writer);
        mainGenerator.getStatic(Type.getType(System.class), "out", Type.getType(PrintStream.class));
        mainGenerator.push("Hello world!");
        mainGenerator.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void println (String)"));
        mainGenerator.returnValue();
        mainGenerator.endMethod();

        writer.visitEnd();

        FileOutputStream stream = new FileOutputStream("HelloWorld.class");
        stream.write(writer.toByteArray());
        stream.close();

    }
}