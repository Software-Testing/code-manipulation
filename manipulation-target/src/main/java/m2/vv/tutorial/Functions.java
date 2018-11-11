package m2.vv.tutorial;

public class Functions {

    public static double twice(double x) {
        return 2 * x;
    }

    public static double twiceplusone(double x) {
        return 2 * x + 1;
    }

    public static double exp1(double x) {
        return Math.exp(x) + 1;
    }

    public static double doublesin(double x) {
        return 2 * Math.sin(Math.PI + x);
    }

    public static double composite(double x) {
        return Math.exp(Math.sin(x));
    }

    public static double sc(double x, double y) {
        return Math.sin(x) + Math.sin(y);
    }

    public static void main(String... args) {
        System.out.println(twice(1));
        System.out.println(twiceplusone(2));
        System.out.println(exp1(3));
        System.out.println(doublesin(4));
        System.out.println(composite(5));
        System.out.println(sc(6, 7));
    }

}
