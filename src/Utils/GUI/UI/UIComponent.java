package Utils.GUI.UI;

import Engines.Engine.GraphicEngine;
import Utils.GUI.Graphics.RGB;

import java.awt.*;

public abstract class UIComponent
{
    private static int idCounter = 1;

    private final int ID;

    public Rectangle position;
    public int padding;
    public RGB color;
    public boolean show;

    public UIComponent(Rectangle position)
    {
        ID = idCounter++;
        show = true;

        color = RGB.Black();

        this.position = position;
    }

    public UIComponent() { this(new Rectangle()); }

    public void build(GraphicEngine engine){}

    public int getID() { return ID; }
    public boolean inComponent(int x, int y) { return position == null ? false : position.contains(x,y); }

    public abstract void onClick(int x, int y);
    public abstract void draw(GraphicEngine graphic);
}
