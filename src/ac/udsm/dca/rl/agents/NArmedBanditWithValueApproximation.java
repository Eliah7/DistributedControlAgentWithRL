package ac.udsm.dca.rl.agents;

import ac.udsm.dca.rl.environment.Action;
import ac.udsm.dca.rl.environment.Environment;
import ai.djl.ndarray.NDArray;

import java.util.ArrayList;
import java.util.List;

/**
 * NArmedBandit using Linear Value Function Approximation
 *    - Represent the value function by a linear combination of features
 *              q(a, w) = a * w where w is a vector of weights for each action
 *
 *     - Update rule becomes
 *              w' = w + learning_rate * (R - q(a, w))
 */
public class NArmedBanditWithValueApproximation extends Agent{
    List<NDArray> listOfActions;

    public NArmedBanditWithValueApproximation(Environment environment, Integer episodes, Double epsilon, Double learningRate){
        this.environment = environment;
        this.epsilon = epsilon;
        this.episodes = episodes;
        this.learningRate = learningRate;

        initialize();
    }

    /**
     * Initialize,
     *  for a = 1 to k:
     *      W ← 0  where k is the length of the action vector
     *
     * Repeat forever:
     *      A ← a random action with probability ε or arg maxa q(A, W) for each A with probability 1 − ε
     *      R ← bandit(A)
     *      W' = W + learning_rate * (R - q(A, W))
     *
     *      ε = ε / episode_num // decay epsilon - divide by number of episodes
     */
    @Override
    void train() {

    }

    private void initialize(){
        initializeListOfActions();
    }

    private void initializeListOfActions(){
        this.environment.getActionSize();

    }
}
