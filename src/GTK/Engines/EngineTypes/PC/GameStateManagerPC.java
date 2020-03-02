package GTK.Engines.EngineTypes.PC;

import GTK.Engines.GameState.GameStateManager;
import GTK.Engines.Engine.IO.ActionEvent;

import java.awt.event.*;


public class GameStateManagerPC extends GameStateManager implements KeyListener, MouseListener, MouseMotionListener
{
    private ActionEvent mouseEvent(ActionEvent.Event event, MouseEvent mEvent)
    {
        return new ActionEvent(ActionEvent.Type.MOUSE,event,mEvent.getX(),mEvent.getY(),-1,mEvent.isAltDown(),mEvent.isControlDown(),mEvent.isShiftDown(),mEvent.getWhen());
    }

    private ActionEvent keyEvent(ActionEvent.Event event, KeyEvent kEvent)
    {
        return new ActionEvent(ActionEvent.Type.KEYBOARD,event,0,0,kEvent.getKeyCode(),kEvent.isAltDown(),kEvent.isControlDown(),kEvent.isShiftDown(),kEvent.getWhen());
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        handleEvent(keyEvent(ActionEvent.Event.TYPED,e));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleEvent(keyEvent(ActionEvent.Event.PRESS,e));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleEvent(keyEvent(ActionEvent.Event.RELEASE,e));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        handleEvent(mouseEvent(ActionEvent.Event.CLICK,e));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        handleEvent(mouseEvent(ActionEvent.Event.PRESS,e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        handleEvent(mouseEvent(ActionEvent.Event.RELEASE,e));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        handleEvent(mouseEvent(ActionEvent.Event.ENTER,e));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        handleEvent(mouseEvent(ActionEvent.Event.EXIT,e));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        handleEvent(mouseEvent(ActionEvent.Event.DRAG,e));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        handleEvent(mouseEvent(ActionEvent.Event.MOVE,e));
    }
}
