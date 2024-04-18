package simstation.prisoner;

import mvc.*;
import simstation.Simulation;
import simstation.SimulationPanel;
import simstation.randomwalk.RandomWalkFactory;

import javax.swing.*;

public class PrisonerDilemmaSimulation extends Simulation {

    @Override
    public void populate() {
        CooperationStrategy[] strategies = {
                new AlwaysCheatStrategy(),
                new AlwaysCooperateStrategy(),
                new RandomCooperateStrategy(),
                new TitForTatStrategy()
        };

        for (int i = 0; i < 40; i++) {  // Assume 40 prisoners
            Prisoner prisoner = new Prisoner("Prisoner " + i, this, strategies[i % 4]);
            add_agent(prisoner);
        }
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerDilemmaFactory());
        panel.display();
    }
}
