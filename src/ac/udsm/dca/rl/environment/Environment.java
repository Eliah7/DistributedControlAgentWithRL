package ac.udsm.dca.rl.environment;

import ac.udsm.dca.interfaces.PGOAnalyzer;
import ac.udsm.dca.math.Matrix;
import ac.udsm.dca.utils.ArrayOperationsService;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    private PGOAnalyzer pgoAnalyzer; // for analyzing power flow
    private State state;
    Integer actionSize = 0;
    ArrayOperationsService arrayOperationsService = new ArrayOperationsService();

    public Environment(PGOAnalyzer pgoAnalyzer, State state){
        this.pgoAnalyzer = pgoAnalyzer;
        this.state = state;
        if (this.state != null){
            this.actionSize = this.state.getBusData().getRowSize();
        }
    }

    public State getState() {
        return state;
    }

    public Integer getActionSize() {
        return actionSize;
    }

    public void step(List<Double> actions){
        // take an action from what the agent has specified
        if (actions.size() == state.busData.getRowSize()){ // there is an equal number of columns in actions and
            for(int row = 0; row < actions.size(); row++){
                state.busData = state.busData.setValue(row+1, 5, actions.get(row));
            }
        }

        // use the new lineData and busData for analysis
//        updatePGOAnalyzerData(state.lineData, state.busData);
    }

    void updatePGOAnalyzerData(Matrix lineData, Matrix busData){
        pgoAnalyzer.lineData = lineData;
        pgoAnalyzer.busData = busData;
    }

    public double reward(){
        List<Double> priority = new ArrayList<>();
        List<Double> status = new ArrayList<>();
        Double reward = 0.0;

        // add a reward from dot product of status and priority
        for (int i = 0; i < state.busData.getRowSize(); i++) {
            reward = Math.pow(state.busData.getAt(i+1, 6), 2) * state.busData.getAt(i+1, 5) * state.busData.getAt(i+1, 2);
            priority.add(Math.pow(state.busData.getAt(i+1, 6), 2));
            status.add(state.busData.getAt(i+1, 5));
        }

        try {
//            return reward;
            return arrayOperationsService.dot(priority, status);
        } catch (Exception e){
            System.out.println(e);
            return 0.0;
        }
//        return pgoAnalyzer.calculate();
    }

    public void reset(){

    }




}
