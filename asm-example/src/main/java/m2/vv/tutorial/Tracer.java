package m2.vv.tutorial;

import org.objectweb.asm.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.ASM5;

public class Tracer extends ClassVisitor {


    public Tracer(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return new MethodTransformer(this.api, super.visitMethod(access, name, desc, signature, exceptions), name + desc);
    }

    static class MethodTransformer extends MethodVisitor {

        private String fullName;

        public MethodTransformer(int i, MethodVisitor methodVisitor, String fullName) {
            super(i, methodVisitor);
            this.fullName = fullName;
        }

        @Override
        public void visitCode() {
            super.visitCode(); // Original visitCode
            super.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            super.visitLdcInsn(fullName);
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }

        public String getFullName() {
            return fullName;
        }
    }

    public static void main(String... args) throws Exception {

        final String target = "manipulation-target/target/classes/m2/vv/tutorial/Functions.class";

        FileInputStream file = new FileInputStream(target);
        ClassReader reader = new ClassReader(file);


        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);

        reader.accept(new Tracer(ASM5, writer), ClassReader.EXPAND_FRAMES);

        FileOutputStream output = new FileOutputStream(target);
        output.write(writer.toByteArray());
    }

}


