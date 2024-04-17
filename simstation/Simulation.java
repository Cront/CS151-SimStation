package simstation;

import mvc.Model;
import mvc.Utilities;

import java.util.*;

public abstract class Simulation extends Model {

    public final static int SIZE = 150;
    protected ArrayList<Agent> agents = new ArrayList<>();
    private int clock = 0;
    transient private Timer timer; // timers aren't serializable
    private boolean is_running = false;
    private boolean is_suspended = false;

    public Iterator<Agent> agents_iterator() {
        return agents.iterator();
    }

    public void start() {
        start_timer();
        stop();
        agents.clear();
        populate();

        for (Agent agent: agents) {
            agent.start();
        }

        is_running = true;
        is_suspended = false;
    }

    public void suspend() {
        stop_timer();

        for (Agent agent: agents) {
            agent.suspend();
        }

        is_suspended = true;
        changed();
    }

    public void resume() {
        for (Agent agent: agents) {
            agent.resume();
        }
    }

    public void stop() {
        start_timer();

        for (Agent agent: agents) {
            agent.stop();
        }

        is_running = false;
        is_suspended = false;
        changed();
    }

    private double distance(Agent agent1, Agent agent2) {
        return Math.sqrt(Math.pow(agent1.xc-agent2.xc, 2) + Math.pow(agent1.yx - agent2.yx, 2));
    }

    public Agent get_neighbors(Agent a, int radius) {
        int start = Utilities.rng.nextInt(agents.size());

        for(int i = 0; i < agents.size(); i++) {
            Agent current = agents.get((start + 1) % agents.size());

            if(distance(current, a) < radius) {
                return current;
            }
        }
        return null;
    }

    public List<Agent> get_all_neighbors(Agent a, int radius) {
        LinkedList<Agent> neighbors = new LinkedList<>();

        for(Agent current : agents) {
            if(distance(current, a) < radius) {
                neighbors.add(current);
            }
        }

        return neighbors;
    }

    public abstract void populate();

    public void add_agent(Agent agent) {
        agents.add(agent);
        agent.setWorld(this);
    }

    private void start_timer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stop_timer() {
        timer.cancel();
        timer.purge();
    }

    public String[] get_stats() {
        return new String[] {"# of agents = " + agents.size()};
    }

    public boolean is_running() {
        return is_running;
    }

    public boolean is_suspended() {
        return is_suspended;
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }
}
