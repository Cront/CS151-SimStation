package simstation.prisoner;

public class TitForTatStrategy implements CooperationStrategy {
    @Override
    public boolean cooperate(Prisoner me) {
        return !me.partnerCheatedLastTime();
    }
}
