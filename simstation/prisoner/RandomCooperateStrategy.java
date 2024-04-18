package simstation.prisoner;

public class RandomCooperateStrategy implements CooperationStrategy {
    public boolean cooperate(Prisoner me) {
        return Math.random() < 0.5;
    }
}
