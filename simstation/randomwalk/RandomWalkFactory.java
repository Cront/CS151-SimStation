package simstation.randomwalk;

import mvc.*;
import simstation.SimulationFactory;

public class RandomWalkFactory extends SimulationFactory {
    @Override
    public Model makeModel() { return new RandomWalkSimulation(); }

    @Override
    public String getTitle() { return "Random Walks";}

//    @Override
//    public String[] getHelp() {
//        return new String[0];
//    }
//
//    @Override
//    public String about() {
//        return null;
//    }
}
