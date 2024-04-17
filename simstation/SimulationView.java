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

        //System.out.println("hits");
        super.paintComponent(gc);
        for (int count = 0; count < ((Simulation)model).agents.size(); count ++) {
            Agent temp = ((Simulation)model).agents.get(count);
            temp.subscribe(this);
            gc.fillOval(temp.xc - dotSize / 2, temp.yc - dotSize / 2, dotSize, dotSize);
        }

    }

    public void update(){
        //System.out.println("repainting person/thing");
        this.repaint();
    }

}
