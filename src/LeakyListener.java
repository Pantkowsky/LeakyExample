import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class LeakyListener implements MouseListener {

    @Override
    public void mouseEntered(MouseEvent mouseEvent){
        System.out.println("mouse entered");
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent){
        System.out.println("mouse pressed");
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){
        System.out.println("performed action".concat(mouseEvent.getClickCount() + ""));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent){
        System.out.println("mouse released");
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){
        System.out.println("mouse exited");
    }
}
