package m2.vv.tutorial;

public class Pair {

    private double value, derivative;

    public double getValue() {
        return value;
    }

    public double getDerivative(){
        return  derivative;
    }

    public Pair(double value) {
        this.value = value;
        derivative = 0;
    }

    public static Pair var(double value) {
        return new Pair(value, 1);
    }

    protected Pair(double value, double derivative) {
        this.value = value;
        this.derivative = derivative;
    }

    public Pair add(Pair another) {
        return new Pair(value + another.value, derivative + another.derivative);
    }

    public Pair sub(Pair another) {
        return new Pair(value - another.value, derivative - another.derivative);
    }

    public Pair mult(Pair another) {
        return new Pair(value * another.value, derivative * another.value + value * another.derivative);
    }

    public Pair div(Pair another) {
        return new Pair(value / another.value, (another.value*derivative - value*another.derivative)/(another.value*another.value));
    }


    public static Pair exp(Pair x) {
        return new Pair(Math.exp(x.value), Math.exp(x.value)*x.derivative);
    }

    public static Pair sin(Pair x) {
        return new Pair(Math.sin(x.value), Math.cos(x.value)*x.derivative);

    }

    public static Pair cos(Pair x) {
        return new Pair(Math.cos(x.value), - Math.sin(x.value)*x.derivative);
    }

}
