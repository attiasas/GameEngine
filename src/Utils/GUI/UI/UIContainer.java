package Utils.GUI.UI;

import Engines.Engine.GraphicEngine;

import java.awt.*;
import java.util.ArrayList;

public abstract class UIContainer extends UIComponent
{
    protected ArrayList<UIComponent> components;
    public int margin;

    public UIContainer(Rectangle position)
    {
        super(position);
        components = new ArrayList<>();
    }

    public UIContainer(int x, int y) { this(new Rectangle(x,y,0,0)); }

    public UIContainer() { this(new Rectangle()); }

    public void addComponent(UIComponent component)
    {
        components.add(component);
    }

    public void removeComponent(UIComponent component)
    {
        components.remove(component);
    }

    @Override
    public void build(GraphicEngine graphicEngine)
    {
        for(int i = 0; i < components.size(); i++)
        {
            components.get(i).build(graphicEngine);
        }
    }

    @Override
    public void onClick(int x, int y)
    {
        if(!inComponent(x,y)) return;

        for(int i = 0; i < components.size(); i++)
        {
            UIComponent component = components.get(i);
            if(component.inComponent(x,y)) component.onClick(x,y);
        }
    }

    @Override
    public void draw(GraphicEngine graphic)
    {
        if(!show) return;

        graphic.setColor(color);
        graphic.fillRectangle(position);

        for(int i = 0; i < components.size(); i++)
        {
            UIComponent component = components.get(i);
            if(component.show)
            {
                component.draw(graphic);
            }
        }
    }
}
