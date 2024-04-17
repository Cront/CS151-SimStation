package simstation.randomwalk;

import mvc.*;
import simstation.*;

class Drunk extends Agent {

    public Drunk() {
        super();
        heading = Heading.random();

    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }

//    @Override
//    public void start()
//    {
//        while(running) {
//            update();
//        }
//    }

}