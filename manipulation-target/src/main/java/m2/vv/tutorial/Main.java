package m2.vv.tutorial;

public class Main {

    public static void main(String... args)  {

        Pair two = new Pair(2);
        Pair one = new Pair(1);

        Pair x = Pair.var(5);

        Pair result = (two.mult(x)).add(one);

        System.out.println(result.getValue());
        System.out.println(result.getDerivative());

        System.out.println(Pair.cos(Pair.var(Math.PI)).getDerivative());

        System.out.print(Functions.composite(3));

    }
}
