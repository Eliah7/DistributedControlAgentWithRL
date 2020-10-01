package ac.udsm.dca.rl.agents;

import ac.udsm.dca.rl.environment.Action;
import ac.udsm.dca.rl.environment.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NArmedBandit extends Agent{
    Map<List<Action>, Double> q_values;
    Map<List<Action>, Integer> n;

    public NArmedBandit(Environment environment, Integer episodes, Double epsilon){
        this.environment = environment;
        this.epsilon = epsilon;
        this.episodes = episodes;

        initialize();
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Map<List<Action>, Integer> getN() {
        return n;
    }

    public Map<List<Action>, Double> getQ_values() {
        return q_values;
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
    @Override
    public void train(){
        int episode = 0;
        while (episode < episodes){

            // run until episode ends
            episode++;
        }
    }

    private void initialize(){
        initializeQValues();
        initializeNValues();
    }

    private void initializeQValues(){
        q_values = new HashMap();

        List actions = new ArrayList<Action>();
        actions.add(Action.OFF);
        q_values.put(
                actions, 0.0
        );

    }

    private void initializeNValues(){
        n = new HashMap();

        List actions = new ArrayList<Action>();
        actions.add(Action.OFF);
        n.put(
                actions, 0
        );
    }

}
