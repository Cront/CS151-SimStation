package simstation.prisoner;

import mvc.Model;
import mvc.View;
import simstation.SimulationFactory;

public class PrisonerDilemmaFactory extends SimulationFactory {

    @Override
    public Model makeModel() {
        return new PrisonerDilemmaSimulation(); // Ensure this class is implemented
    }


    @Override
    public String getTitle() {
        return "Prisoner's Dilemma Simulation";
    }

    @Override
    public String about() {
        return "This simulation models the Prisoner's Dilemma, a standard example of a game analyzed in game theory.";
    }
    @Override
    public View makeView(Model model) {
        return new PrisonerDilemmaView(model);
    }

}
