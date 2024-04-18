package simstation.prisoner;

import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;
import mvc.Model;
import java.awt.*;

public class PrisonerDilemmaView extends SimulationView {

    public PrisonerDilemmaView(Model model) {
        super(model);  // Call the superclass constructor with the model
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);  // Maintain the basic painting logic

        // Now add specific drawing logic for Prisoner's Dilemma
        Simulation simulation = (Simulation) model;
        for (Agent agent : simulation.getAgents()) {
            if (agent instanceof Prisoner) {
                Prisoner prisoner = (Prisoner) agent;
                int x = (int) (prisoner.getX() % getWidth());
                int y = (int) (prisoner.getY() % getHeight());

                // Color coding based on whether the prisoner cooperated or cheated last
                if (prisoner.partnerCheatedLastTime()) {
                    gc.setColor(Color.RED);
                } else {
                    gc.setColor(Color.GREEN);
                }

                int dotSize = 50;
                gc.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize);

                // Optionally, draw the fitness score
                gc.setColor(Color.BLACK);
                gc.drawString("Fitness: " + prisoner.getFitness(), x + dotSize, y);
            }
        }
    }
}
