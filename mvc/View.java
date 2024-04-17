package mvc;
import javax.swing.*;

import java.awt.*;

public class View extends JPanel implements Subscriber {

    protected Model model;

    public View(Model model){
        this.model = model;
        model.subscribe(this);
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        this.repaint();
    }
}
