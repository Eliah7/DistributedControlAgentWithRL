package ac.udsm.dca.rl.agents;

import ac.udsm.dca.rl.environment.Action;
import ac.udsm.dca.rl.environment.Environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NArmedBandit {
    Environment environment;
    Integer actionSize; //
    Integer episodes = 1000;
    Double epsilon = 1e-2;

    Map<List<Action>, Integer> q_values;
    Map<List<Action>, Integer> n;

    public NArmedBandit(Environment environment, Integer episodes, Double epsilon){
        this.environment = environment;
        this.actionSize = this.environment.getState().getBusData().getRowSize();
        this.epsilon = epsilon;

        initialize();
    }

    /**
     * Initialize,
     *  for a = 1 to k:
     *      Q(a) ← 0
     *      N(a) ← 0
     *
     * Repeat forever:
     *      A ← a random action with probability ε or arg maxa Q(a) with probability 1 − ε
     *      R ← bandit(A)
     *      N(A) ← N(A) + 1
     *      Q(A)← Q(A) +  [ R−Q(A)􏰅 ] / N (A)
     */
    void train(){
        int episode = 0;
        while (episode < episodes){

            // run until episode ends
            episode++;
        }
    }

    void initialize(){
        q_values = new HashMap();
//        q_values.put();

        n = new HashMap();
    }

}
