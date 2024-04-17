package simstation.randomwalk;

import mvc.*;
import simstation.Simulation;
import simstation.SimulationPanel;

public class  RandomWalkSimulation extends Simulation {


    public void populate() {
        for(int i = 0; i < 15; i++)
            add_agent(new Drunk());
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new RandomWalkFactory());
        panel.display();
    }

}
