package Utils.GUI.UI.Components;

import Utils.GUI.UI.UIActionListener;
import Utils.GUI.UI.UIComponent;

import java.awt.*;

public abstract class Button extends UIComponent
{
    private UIActionListener listener;

    public Button(Rectangle position, UIActionListener listener)
    {
        super(position);
        onClickAction(listener);
    }

    public Button(Rectangle position) { this(position,null); }
    public Button() { this(new Rectangle(),null); }

    public void onClickAction(UIActionListener listener) { this.listener = listener; }

    @Override
    public void onClick(int x, int y)
    {
        if(listener == null || !inComponent(x,y)) return;

        listener.handle();
    }
}
