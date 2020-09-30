package ac.udsm.dca.rl;

import ac.udsm.dca.Main;
import ac.udsm.dca.core.Dlf;
import ac.udsm.dca.math.Matrix;
import ac.udsm.dca.rl.environment.Environment;
import ac.udsm.dca.rl.environment.State;

public class RLForPowerGridOptimization {

    public static void main(String args[]){
        Dlf pf = new Dlf(new Matrix(Main.loadData), new Matrix(Main.lineData), 1);

        Environment environment = new Environment(pf, new State(
                new Matrix(Main.loadData),
                new Matrix(Main.lineData)
        ));

        System.out.println("=====> Running a multi armed bandit algorithm for Power Grid Optimization ");


    }
}
