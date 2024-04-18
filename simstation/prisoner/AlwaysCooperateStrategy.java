package simstation.prisoner;

public class AlwaysCooperateStrategy implements CooperationStrategy {
    public boolean cooperate(Prisoner me) {
        return true;
    }
}
