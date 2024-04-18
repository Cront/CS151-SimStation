package simstation.prisoner;

import simstation.*;

public class Prisoner extends Agent {
    private int fitness = 0;
    private boolean partnerCheatedLastTime = false;
    private CooperationStrategy strategy;

    public Prisoner(String name, Simulation world, CooperationStrategy strategy) {
        super(name, world);
        this.strategy = strategy;
    }

    public boolean cooperate() {
        return strategy.cooperate(this);
    }

    @Override
    public void update() {
        Prisoner partner = (Prisoner) world.get_neighbors(this, 1);
        if (partner != null) {
            boolean myDecision = this.cooperate();
            boolean partnerDecision = partner.cooperate();

            updateFitness(myDecision, partnerDecision);
            partner.updateFitness(partnerDecision, myDecision);
        }
    }

    private void updateFitness(boolean myDecision, boolean partnerDecision) {
        if (myDecision && partnerDecision) {
            fitness += 3; // Both cooperate
        } else if (!myDecision && partnerDecision) {
            fitness += 5; // I cheat, partner cooperates
        } else if (myDecision && !partnerDecision) {
            fitness += 0; // I cooperate, partner cheats
        } else {
            fitness += 1; // Both cheat
        }
        partnerCheatedLastTime = !partnerDecision;
    }

    public int getFitness() {
        return fitness;
    }

    public boolean partnerCheatedLastTime() {
        return partnerCheatedLastTime;
    }

    public CooperationStrategy getStrategy() {
        return strategy;
    }
}
