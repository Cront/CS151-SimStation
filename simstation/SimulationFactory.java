package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public abstract class SimulationFactory implements AppFactory {

    public String[] getEditCommands() {
        return new String[] { "Start", "Suspend", "Resume", "Stop", "Stats" };
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        return switch (type) {
            case "Start" -> new StartCommand(model);
            case "Suspend" -> new SuspendCommand(model);
            case "Resume" -> new ResumeCommand(model);
            case "Stop" -> new StopCommand(model);
            case "Stats" -> new StatsCommand(model);
            default -> null;
        };
    }

    public String[] getHelp() {
        return new String[] {
                "Click start to start the simulation",
                "Click suspend to pause the simulation",
                "Click resume to resume the simulation",
                "Click stop to stop the simulation",
                "Click stats to show statistics"
        };
    }

    public View makeView(Model m) {
        return new SimulationView(m);
    }

//    public abstract Model makeModel();

    @Override
    public String getTitle() { return "SimStation Simulator"; }

    @Override
    public String about() {
        return "SimStation Team ZSH";
    }
}
