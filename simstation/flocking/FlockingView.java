package simstation.flocking;

import mvc.Model;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;

import java.awt.*;

public class FlockingView extends SimulationView {
//    protected Color agent_color = Color.WHITE;

    public FlockingView(Model model) {
        super(model);
    }

//    @Override
//    public Color get_agent_color(Agent agent) {
//        return Color.WHITE;
//    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation) model;
        for (Agent agent : simulation.getAgents()) {
            gc.setColor(Color.WHITE);
        }
    }

}
