package ac.udsm.dca.interfaces;

import ac.udsm.dca.math.ComplexMatrix;
import ac.udsm.dca.math.Matrix;

public abstract class PGOAnalyzer {
    public Matrix busData;
    public Matrix lineData;
    protected int centralBus;
    protected ComplexMatrix IB;
    protected ComplexMatrix VB;

    abstract public double calculate();
}
