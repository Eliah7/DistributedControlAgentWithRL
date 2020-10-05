package tests.rl;

import ac.udsm.dca.Main;
import ac.udsm.dca.core.Dlf;
import ac.udsm.dca.math.Matrix;
import ac.udsm.dca.rl.agents.NArmedBandit;
import ac.udsm.dca.rl.agents.NArmedBanditWithValueApproximation;
import ac.udsm.dca.rl.environment.Environment;
import ac.udsm.dca.rl.environment.State;
import org.junit.Before;
import org.junit.Test;

public class NArmedBanditWithValueFunctionApproximationTests extends AgentTest{
   NArmedBanditWithValueApproximation bandit;


    @Before
    public void setUp(){
        super.setUp();
        bandit = new NArmedBanditWithValueApproximation(environment, 1000, 0.01, 0.01);
//        System.out.println(environment.getActionSize());
    }

    @Test
    public void testInitializeListOfActions(){
        System.out.println();
    }

    @Test
    public void testInitializeWeights(){

    }

    @Test
    public void testTrain(){
        bandit.train();
    }

}
