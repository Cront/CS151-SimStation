package mvc;

public interface Subscriber {
    public void update(String msg, Object oldState, Object newState);
    public void update();
}
