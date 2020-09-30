package ac.udsm.dca.rl.environment;

public enum Action{
    ON(1),
    OFF(0);

    public int value;

    Action(int val){
        this.value = val;
    }
}