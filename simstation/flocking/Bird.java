package simstation.flocking;

import mvc.Utilities;
import simstation.Agent;
import java.util.List;

class Bird extends Agent {
    private double speed;

    public Bird() {
        super();
        speed = Utilities.rng.nextInt(5) + 1;
    }

    @Override
    public void update() {
        List<Agent> neighbors = world.get_all_neighbors(this, 20);

        if(!neighbors.isEmpty()) {
            double avg_speed = 0;
            double avg_x_dir = 0;
            double avg_y_dir = 0;

            for(Agent agent : neighbors) {
                Bird bird = (Bird) agent;
                avg_speed += bird.get_speed();
                avg_x_dir += bird.heading.get_x_dir();
                avg_y_dir += bird.heading.get_y_dir();
            }

            // calculates the new heading
            Heading old_heading = heading;
            heading = new Heading(avg_x_dir, avg_y_dir);
            heading = new Heading(heading.get_x_dir() + (0.85 * old_heading.get_x_dir()), heading.get_y_dir() + (0.85 * old_heading.get_y_dir()));

            // calculates the new speed
            speed = (1 - 0.85) * (avg_speed / neighbors.size()) + (0.85 * speed);
        }

        move((int)speed);
    }

    public double get_speed() {
        return speed;
    }
}
