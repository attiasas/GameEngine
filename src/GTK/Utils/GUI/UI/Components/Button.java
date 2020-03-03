package GTK.Utils.GUI.UI.Components;

import GTK.Utils.GUI.UI.UIActionListener;
import GTK.Utils.GUI.UI.UIComponent;

import java.awt.*;

/**
 * Created By:      Assaf, On 23/02/2020
 * Description:     defines an abstract button UI Component.
 *                  * has an action method that can be assigned dyanamcally to handle click events.
 **/
public abstract class Button extends UIComponent
{
    private UIActionListener listener;

    /**
     * Constructor
     * @param position - screen space position of the button.
     * @param listener - action listener for a click action.
     */
    public Button(Rectangle position, UIActionListener listener)
    {
        super(position);
        onClickAction(listener);
    }

    /**
     * Constructor of button without clicking actions.
     * @param position - screen space position of the button.
     */
    public Button(Rectangle position) { this(position,null); }

    /**
    * Constructor of button without clicking actions with default position values (0).
    **/
    public Button() { this(new Rectangle(),null); }

    /**
     * Set An Action listener to handle clicking actions on the button
     * @param listener - listener to handle actions.
     */
    public void onClickAction(UIActionListener listener) { this.listener = listener; }

    @Override
    public void onClick(int x, int y)
    {
        if(listener == null || !inComponent(x,y)) return;

        listener.handle();
    }
}
