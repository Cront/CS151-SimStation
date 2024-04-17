package simstation;


import mvc.Command;
import mvc.Model;


public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation map = (Simulation)model;
        System.out.println("Start command");
        map.start();

    }

}
