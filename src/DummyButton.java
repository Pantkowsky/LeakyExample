import java.awt.*;


public class DummyButton extends Button {
    private final LeakyListener listener = new LeakyListener();

    /**
     * Constructor for a dummy example button
     */
    public DummyButton(){}

    /**
     * Setup method for {@link LeakyListener}
     */
    public void setupLeakingListener(){
        this.addMouseListener(listener);
    }

    /**
     * After adding event handlers, such as {@link java.awt.event.MouseListener}
     * preventing them from leaking memory can be done by explicitly
     * removing them whenever they no longer have any events to listen for.
     */
    public void clearLeakingListener(){
        this.removeMouseListener(listener);
    }
}
