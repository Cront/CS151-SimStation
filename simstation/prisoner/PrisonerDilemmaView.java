package simstation.prisoner;

import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;
import mvc.Model;
import java.awt.*;

public class PrisonerDilemmaView extends SimulationView {

    public PrisonerDilemmaView(Model model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        Simulation simulation = (Simulation) model;
        for (Agent agent : simulation.getAgents()) {
            if (agent instanceof Prisoner) {
                Prisoner prisoner = (Prisoner) agent;
                int x = (int) (prisoner.getX() % getWidth());
                int y = (int) (prisoner.getY() % getHeight());

                if (prisoner.partnerCheatedLastTime()) {
                    gc.setColor(Color.RED);
                } else {
                    gc.setColor(Color.GREEN);
                }

                int dotSize = 50;
                gc.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize);

                gc.setColor(Color.BLACK);
                gc.drawString("Fitness: " + prisoner.getFitness(), x + dotSize, y);
            }
        }
    }
}
