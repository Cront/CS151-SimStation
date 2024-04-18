package simstation.flocking;

import mvc.Model;
import simstation.SimulationFactory;

public class FlockingFactory extends SimulationFactory {
    @Override
    public Model makeModel() { return new FlockingSimulation(); }

    @Override
    public String getTitle() { return "Flocking"; }
}
