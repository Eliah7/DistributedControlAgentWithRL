package tests.rl;

import ac.udsm.dca.Main;
import ac.udsm.dca.core.Dlf;
import ac.udsm.dca.math.Matrix;
import ac.udsm.dca.rl.agents.NArmedBandit;
import ac.udsm.dca.rl.environment.Environment;
import ac.udsm.dca.rl.environment.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NArmedAgentTests {
    NArmedBandit nArmedBandit;
    Environment environment;

    @Before
    public void setUp(){
        Dlf pf = new Dlf(new Matrix(Main.loadData), new Matrix(Main.lineData), 1);

        environment = new Environment(pf, new State(
                new Matrix(Main.loadData),
                new Matrix(Main.lineData)
        ));

        nArmedBandit = new NArmedBandit(environment, 1000, 1e-2);
    }

    @Test
    public void testInitializingQValues(){
        // After initializing Q_values
        Assert.assertNotNull(nArmedBandit.getQ_values());

        Assert.assertFalse(nArmedBandit.getQ_values().isEmpty());
    }

    @Test
    public void testInitializingNValues(){
        // After initializing N_values
        Assert.assertNotNull(nArmedBandit.getN());

        Assert.assertFalse(nArmedBandit.getN().isEmpty());
    }

    @Test
    public void hasCorrectQSize(){
        int qSize = nArmedBandit.getQ_values().size();
        int expectedQSize = (int) Math.pow(2, nArmedBandit.getEnvironment().getActionSize());

        Assert.assertEquals(qSize, expectedQSize);
    }

    @Test
    public void hasCorrectNSize(){
        int nSize = nArmedBandit.getN().size();
        int expectedNSize = (int) Math.pow(2, nArmedBandit.getEnvironment().getActionSize());

        Assert.assertEquals(nSize, expectedNSize);
    }
}
