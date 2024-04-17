package simstation;


import mvc.Publisher;
import mvc.Utilities;


import java.io.Serializable;


public abstract class Agent extends Publisher implements Serializable, Runnable {

    protected String name;
    protected Heading heading;
    protected double xc;
    protected double yc;
    protected boolean suspended = false;
    protected boolean stopped = false;
    protected transient Thread thread;
    protected Simulation world;

    public Agent(String name, Simulation world) {
        this.name = name;
        this.world = world;
        this.xc = Simulation.SIZE;
        this.yc = Simulation.SIZE;
    }

    public Agent() {
        suspended = false;
        stopped = false;
        thread = null;

        // randomly initialize position and heading
        heading = Heading.random();
        xc = Utilities.rng.nextInt(Simulation.SIZE);
        yc = Utilities.rng.nextInt(Simulation.SIZE);
    }

    @Override
    public void run()
    {
        thread = Thread.currentThread();
        while (!stopped) {
            try {
                update();
                Thread.sleep(20); // Adjust sleep time for smooth graphics
                checkSuspended();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        world.changed();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
    }


    public synchronized void suspend()
    {
        suspended = true;
    }

    public synchronized void resume()
    {
        suspended = false;
        notify();

        if (thread == null) {
            start();
        }
    }

    public synchronized void stop()
    {
        stopped = true;
    }

    public abstract void update();

    public void move(int steps) {
        for (int i = 0; i < steps; i++) {
            xc = (xc + heading.get_x_dir()) % Simulation.SIZE;
            yc = (yc + heading.get_y_dir()) % Simulation.SIZE;

            if (xc < 0) xc += Simulation.SIZE;
            if (yc < 0) yc += Simulation.SIZE;

            System.out.println("Moving to: " + xc + ", " + yc); // Debug print

            world.changed();
        }
    }



    private synchronized void checkSuspended() {
        try {
            while (!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }
    public double getX() {
        return xc;
    }

    public double getY() {
        return yc;
    }

    public void reset(boolean randomly) {
        if (randomly)
        {
            xc = Utilities.rng.nextInt(Simulation.SIZE);
            yc = Utilities.rng.nextInt(Simulation.SIZE);
        }
        else
        {
            xc = 0;
            yc = 0;
        }
    }

    //method to set the world
    public void setWorld(Simulation world)
    {
        this.world = world;
    }

    protected static class Heading implements Serializable
    {
        public final double x_dir;
        public final double y_dir;

        public Heading(double x_dir, double y_dir) {
            double direction = Math.pow(x_dir, 2) + Math.pow(y_dir, 2);

            double square_dir = Math.sqrt(direction);
            this.x_dir = x_dir / square_dir;
            this.y_dir = y_dir / square_dir;
        }

        public double get_x_dir() {
            return x_dir;
        }

        public double get_y_dir() {
            return y_dir;
        }

        public static Heading random() {
            double x_dir = Math.random();
            double y_dir = Math.sqrt(1 - Math.pow(x_dir, 2));

            double x_mul = Math.random() < 0.5 ? 1 : -1;
            double y_mul = Math.random() < 0.5 ? 1 : -1;

            return new Heading(x_dir * x_mul, y_dir * y_mul);
        }
    }
}
