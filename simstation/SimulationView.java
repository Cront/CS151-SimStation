package simstation;

import mvc.Model;
import mvc.View;

import java.awt.*;



public class SimulationView extends View {
    int dotSize = 5;
    private Agent myAgent;
    public SimulationView(Model model) {

        super(model);
        setSize(500, 500);
        setBackground(Color.LIGHT_GRAY);
        repaint();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation) model;
        for (Agent agent : simulation.getAgents()) {
            int x = (int) (agent.getX() % getWidth());
            int y = (int) (agent.getY() % getHeight());
            gc.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize);
        }
    }


    public void update(){
        //System.out.println("repainting person/thing");
        this.repaint();
    }

}
