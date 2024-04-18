package simstation.prisoner;

public class AlwaysCheatStrategy implements CooperationStrategy {
    public boolean cooperate(Prisoner me) {
        return false;
    }
}
