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

}
