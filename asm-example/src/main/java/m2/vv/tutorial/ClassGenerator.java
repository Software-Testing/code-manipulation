package m2.vv.tutorial;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;

public class ClassGenerator {

    public static void main(String... args) throws IOException {
        //System.out.println("Hellow world");

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        writer.visit(
                Opcodes.V1_8, // Java version
                Opcodes.ACC_PUBLIC, // Modifiers
                "HelloWorld", // Name
                null, // Signature if generic
                "java/lang/Object", // Base class
                new String[0] // Interfaces
        );

        MethodVisitor constructor = writer.visitMethod(
                Opcodes.ACC_PUBLIC, // Modifiers
                "<init>", // Name, observe the special name for the constructor
                "()V",  // Description
                null, // Signature if generic
                null // Exceptions
        );


        constructor.visitCode();
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitMethodInsn(
                Opcodes.INVOKESPECIAL, // Opcode to invoke an instance non-virtual method
                "java/lang/Object", // Class declaring the method
                "<init>",  // Name of the method
                "()V",  // Description
                false // Whether the class is an interface
        );
        constructor.visitInsn(Opcodes.RETURN);
        constructor.visitMaxs(0, 0);


        MethodVisitor mainMethod = writer.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, // Modifiers
                "main",
                "([Ljava/lang/String;)V",
                null,
                null
        );

        mainMethod.visitCode(); // to start writing the code
        mainMethod.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mainMethod.visitLdcInsn("Hello World");
        mainMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mainMethod.visitInsn(Opcodes.RETURN);
        mainMethod.visitMaxs(0, 0);

        FileOutputStream stream = new FileOutputStream("HelloWorld.class");
        stream.write(writer.toByteArray());
        stream.close();

    }

}
