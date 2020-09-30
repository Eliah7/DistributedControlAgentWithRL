package ac.udsm.dca.rl.environment;

import ac.udsm.dca.interfaces.PGOAnalyzer;
import ac.udsm.dca.math.Matrix;

import java.util.List;

public class Environment {
    private PGOAnalyzer pgoAnalyzer; // for analyzing power flow
    private State state;

    public Environment(PGOAnalyzer pgoAnalyzer, State state){
        this.pgoAnalyzer = pgoAnalyzer;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void step(List<Action> actions){
        // take an action from what the agent has specified
        if (actions.size() == state.busData.getRowSize()){ // there is an equal number of columns in actions and
            for(int row = 0; row < actions.size(); row++){
                state.busData = state.busData.setValue(row+1, 5, actions.get(row).value);
            }
        }

        // use the new lineData and busData for analysis
        updatePGOAnalyzerData(state.lineData, state.busData);
    }

    void updatePGOAnalyzerData(Matrix lineData, Matrix busData){
        pgoAnalyzer.lineData = lineData;
        pgoAnalyzer.busData = busData;
    }

    public double reward(){
        // add a reward from dot product of status and priority
        return pgoAnalyzer.calculate();
    }

    public void reset(){

    }




}
