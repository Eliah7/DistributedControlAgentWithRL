package tests.rl;

import ac.udsm.dca.Main;
import ac.udsm.dca.core.Dlf;
import ac.udsm.dca.math.Matrix;
import ac.udsm.dca.rl.environment.Action;
import ac.udsm.dca.rl.environment.Environment;
import ac.udsm.dca.rl.environment.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentTests {
    Environment environment;

    @Before
    public void setUp(){
        Dlf pf = new Dlf(new Matrix(Main.loadData), new Matrix(Main.lineData), 1);
        environment = new Environment(pf, new State(
                new Matrix(Main.loadData),
                new Matrix(Main.lineData)
        ));
    }

    @Test
    public void testStep(){
        List<Double> actions = new ArrayList<>();
        List<Double> results = new ArrayList<>();

        for (int i = 0; i < new Matrix(Main.loadData).getRowSize(); i++){
            actions.add(0.0);  // turn each node off
        }

        this.environment.step(actions); // apply actions

        for (int i = 0; i < new Matrix(Main.loadData).getRowSize(); i++){
            results.add(this.environment.getState().getBusData().getAt(i + 1, 5) == 0.0 ? 0.0 : 1.0);
        }

        Assert.assertEquals(actions, results);
    }

    @Test
    public void testNoStep(){
        List<Action> actions = new ArrayList<>();
        List<Action> results = new ArrayList<>();

        Assert.assertNotSame(actions, results);
    }

    @Test
    public void testUpdatePGOAnalyzerData(){

    }

    @Test
    public void testReward(){
        Assert.assertNotNull(this.environment);
        Assert.assertEquals(this.environment.reward(), 0.0);
    }
}
