package simstation.flocking;

import mvc.AppFactory;
import simstation.Simulation;
import simstation.Agent;
import simstation.SimulationPanel;

import java.util.Iterator;

public class FlockingSimulation extends Simulation {

    @Override
    public void populate() {
        for(int i = 0; i < 14; i++)
            add_agent(new Bird());
    }

    @Override
    public String[] get_stats() {
        int[] speeds = new int[5];
        Iterator<Agent> agent_iterator = agents_iterator();

        while(agent_iterator.hasNext()) {
            double speed = ((Bird)agent_iterator.next()).get_speed();
            speeds[(int)speed - 1]++;
        }

        String[] bird_stats = new String[speeds.length];
        String stat_to_append;

        for(int i = 0; i < speeds.length; i++) {
            stat_to_append = "#birds @ Speed " + (i + 1) + " = " + speeds[i];
            bird_stats[i] = stat_to_append;
        }
        return bird_stats;
    }

    public static void main(String[] args) {
        AppFactory factory = new FlockingFactory();
        SimulationPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
