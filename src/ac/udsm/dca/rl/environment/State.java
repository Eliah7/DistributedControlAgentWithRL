package ac.udsm.dca.rl.environment;

import ac.udsm.dca.math.Matrix;

public class State {
    protected Matrix busData;
    protected Matrix lineData;

    public State(Matrix busData, Matrix lineData){
        this.busData = busData;
        this.lineData = lineData;
    }

    public Matrix getBusData() {
        return busData;
    }
}
