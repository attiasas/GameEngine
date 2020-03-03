package GTK.Utils.GUI.UI;

import GTK.Engines.Engine.GraphicEngine;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created By:      Assaf, On 23/02/2020
 * Description:     Basic GUI Component Container that defines all the global features and methods of Container.
 *                  containers can contain multiple components and group them as a single managed unit.
 *                  * a container is an extended component (composite design pattern)
 *                  * Building will happen recursively to the components (build leaf first and root last)
 * */
 public abstract class UIContainer extends UIComponent
{
    protected ArrayList<UIComponent> components;
    public int margin;

    /**
     * Constructor
     * @param position - screen space position of the container
     */
    public UIContainer(Rectangle position)
    {
        super(position);
        components = new ArrayList<>();
    }

    /**
     * Constructor, the height and width will be set as 0.
     * @param x - x position in screen space of the container
     * @param y - y position in screen space of the container
     */
    public UIContainer(int x, int y) { this(new Rectangle(x,y,0,0)); }

    /**
     * Constructor, all the position values will be set to 0.
     */
    public UIContainer() { this(new Rectangle()); }

    /**
     * Add new UI component as the child (group) of the container
     * @param component - to add to the container
     */
    public void addComponent(UIComponent component)
    {
        components.add(component);
    }

    /**
     * remove a given component from the container
     * @param component - to remove.
     */
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
