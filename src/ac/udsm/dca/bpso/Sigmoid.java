package ac.udsm.dca.bpso;

public class Sigmoid {
    public static double function(double velocity) {
        double result = 0.0;
        result = 1 / (1 + Math.exp(-velocity));
        return result;
    }
}
