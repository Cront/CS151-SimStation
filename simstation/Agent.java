package simstation;


import mvc.Publisher;
import mvc.Utilities;


import java.io.Serializable;


public class Agent extends Publisher implements Serializable, Runnable {

    protected String name;
    protected Heading heading;
    protected int xc;
    protected int yc;
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

    public void run()
    {
        while (!stopped) {
            if (!suspended) {
                update();
                world.changed();
            }
            try {
                Thread.sleep(20); // Adjust sleep time for smooth graphics
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start()
    {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }


    public void suspend()
    {
        suspended = true;
    }

    public void resume()
    {
        suspended = false;
    }

    public void stop()
    {
        stopped = true;
    }

    public void update()
    {
        //to do in subclasses
    }

    public void move(int steps) {
        for (int i = 0; i < steps; i ++) {
            Heading randomHeading = Heading.random();
            switch (randomHeading.direction) {
                case 0:
                    xc += 1;
                    break;
                case 1:
                    yc -= 1;
                    break;
                case 2:
                    xc -= 1;
                    break;
                case 3:
                    yc += 1;
                    break;
            }
            world.changed();
        }
    }

    protected static class Heading implements Serializable
    {

        public  int direction;

        public Heading(int direction) {
            this.direction = direction;
        }

        public static Heading random() {
            return new Heading(Utilities.rng.nextInt(4));
        }
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
}
