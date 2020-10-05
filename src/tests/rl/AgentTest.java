package tests.rl;

import ac.udsm.dca.Main;
import ac.udsm.dca.core.Dlf;
import ac.udsm.dca.math.Matrix;
import ac.udsm.dca.rl.agents.NArmedBandit;
import ac.udsm.dca.rl.environment.Environment;
import ac.udsm.dca.rl.environment.State;

public class AgentTest {
    Environment environment;

    public void setUp(){
        Dlf pf = new Dlf(new Matrix(Main.loadData), new Matrix(Main.lineData), 1);

        environment = new Environment(pf, new State(
                new Matrix(Main.loadData),
                new Matrix(Main.lineData)
        ));

    }
}
