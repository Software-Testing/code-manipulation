# Code manipulation tutorial

This project contains examples on how to query Java source code and
JVM bytecode and generate new classes with [ASM](https://asm.ow2.io),
[Javassist](http://www.javassist.org/) and [Spoon](http://spoon.gforge.inria.fr/).

It contains the following four modules:
* `asm-example`
* `javassist-example`
* `spoon-example`
* `manipulation-target`

The `manipulation-target` module contains the code that will be used as
input for the examples. The three remaning modules contain the examples,
one for each library.

Each example contains the following classes:
* `MethodPrinter`: This class will search the `Functions` class and will
print all methods of this class.
* `ClassGenerator`: This class will generate a `HelloWorld` class such
    as the following:
    ```Java
    public class HelloWorld {
        public static void main(String[] args) {
            System.out.println("Hello World");
        }
    }
    ```
* `Tracer`: Instruments each method declared in the `Functions` class to
    print its name and description before the execution.
* `InstructionModifier`: Replaces the multiplication in the `twice`
    method of the `Functions` class by an addition.


