package ac.udsm.dca.rl.agents;

import ac.udsm.dca.rl.environment.Environment;

public abstract class Agent {
    Environment environment;
    Integer episodes = 1000;
    Double epsilon = 0.01;
    Double learningRate = 0.01;

    abstract void train();
}
