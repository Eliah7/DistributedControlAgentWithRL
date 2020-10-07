package ac.udsm.dca.rl.agents;

import ac.udsm.dca.rl.environment.Action;
import ac.udsm.dca.rl.environment.Environment;
import ac.udsm.dca.utils.ArrayOperationsService;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * NArmedBandit using Linear Value Function Approximation
 *    - Represent the value function by a linear combination of features
 *              q(a, w) = a * w where w is a vector of weights for each action
 *
 *     - Update rule becomes
 *              w' = w + learning_rate * (R - q(a, w))
 */
public class NArmedBanditWithValueApproximation extends Agent{
    List<Double> weights;

    ArrayOperationsService arrayOperationsService = new ArrayOperationsService();

    public NArmedBanditWithValueApproximation(Environment environment, Integer episodes, Double epsilon, Double learningRate){
        this.environment = environment;
        this.epsilon = epsilon;
        this.episodes = episodes;
        this.learningRate = learningRate;
        initializeRandomWeights();
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
    public void train() {
        int episode = 0;
        List<Double> actions;
        List<Double> rewards = new ArrayList<>();

        while (episode < episodes){
            // choose action with probability epsilon
            if ((new Random().nextDouble())  <= this.epsilon ){
                actions = arrayOperationsService.permutationForI(
                        this.environment.getActionSize(),
                        new Random().nextInt(this.environment.getActionSize()));
            } else {
                actions = this.getMaxAction();
            }

            // make action
            this.environment.step(actions);

            // get reward
            Double reward = this.environment.reward();
            rewards.add(reward);

            // update weights
            for (int i = 0; i < weights.size(); i++) {
                try {
                    weights.set(i, weights.get(i) + learningRate * (reward - arrayOperationsService.dot(actions, weights)));
                } catch (Exception e){
                    System.out.println(e);
                }
            }
            System.out.println("Weights: " + weights);

            // update epsilon
            this.epsilon = this.epsilon * 0.7 ;

            // run until episode ends
            episode++;
            this.environment.reset();
            System.out.println("EPISODE " + episode + ": REWARD: " + reward + " \nACTION: " + actions + "\n\n\n");
        }

        plotGraph(rewards);
    }

    private void plotGraph(List<Double> rewards){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < rewards.size(); i++) {
            dataset.addValue(rewards.get(i), "reward", String.valueOf(i));
        }

        JFreeChart c = (JFreeChart) ChartFactory.createLineChart(
                "NArmedBanditWithValueApproximation Training Curve",
                "Episodes",
                "Rewards",
               dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(c);
        Dimension d = new java.awt.Dimension(560, 367);

        chartPanel.setPreferredSize(d);
        JFrame f = new JFrame();
        f.setPreferredSize(d);
        f.setContentPane(chartPanel);
        f.setVisible(true);
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private List<Double> getMaxAction(){
        Double max = 0.0;
        int action = 0;

        for (int i = 0; i < this.environment.getActionSize(); i++) {
            try{
                Double val = arrayOperationsService.dot(weights, arrayOperationsService.permutationForI(this.environment.getActionSize(), i));
                if (val > max){
                    max = val;
                    action = i;
                }
            } catch (Exception e){
                System.out.println(e);
            }
        }

        return arrayOperationsService.permutationForI(this.environment.getActionSize(), action);
    }

    private void initializeRandomWeights(){
       this.weights = new ArrayList<>();

       System.out.println(Math.random());
       for (int i = 0; i <this.environment.getActionSize() ; i++) {
           this.weights.add(new Random().nextDouble());
       }

       System.out.print(this.weights);
    }

    private void initializeListOfActions(){

        System.out.println(arrayOperationsService.permutationForI(this.environment.getActionSize(), 4));

    }
}
