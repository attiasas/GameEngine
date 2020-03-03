package GTK.Utils.GUI.UI;

import GTK.Engines.Engine.GraphicEngine;
import GTK.Utils.GUI.Graphics.RGB;

import java.awt.*;

/**
 * Created By:      Assaf, On 23/02/2020
 * Description:     Basic GUI Component that defines all the global features and methods of component.
 *
 * Features:        * ID - unique identification number of the component.
 *                  * position - the position (x,y,width,height) of the component on screen space
 *                  * color - background color of the component
 *                  * padding - padding from the border of the component to its content.
 *                  * show - flag that needs to be true if the component is showing - without it, the component will not show.
 **/
public abstract class UIComponent
{
    private static int idCounter = 1;

    private final int ID;

    public Rectangle position;
    public int padding;
    public RGB color;
    public boolean show;

    /**
     * Constructor, default color is black and show flag is set to true.
     * @param position - the position of the component in screen space.
     */
    public UIComponent(Rectangle position)
    {
        ID = idCounter++;
        show = true;

        color = RGB.Black();

        this.position = position;
    }

    /**
     * Default constructor, all position values will be equal to 0, default color is black and show flag is set to true.
     */
    public UIComponent() { this(new Rectangle()); }

    /**
     * this method can be called dynamically to calculate the properties of the component when changing its values, if needed.
     * build the current UI component, needs to be apply before any use of the component and after any change in positions.
     * @param engine - graphic engine to help building the component base on given graphic
     */
    public void build(GraphicEngine engine){}

    /**
     * Get the unique id number of the component.
     * @return - id number
     */
    public int getID() { return ID; }

    /**
     * Check if a given position in screen space is contained inside the component position
     * @param x - x position in screen space
     * @param y - y position in screen space
     * @return true if position is inside the component, false otherwise.
     */
    public boolean inComponent(int x, int y) { return position == null ? false : position.contains(x,y); }

    /**
     * Handle click events on a given position for this component
     * @param x - x position in screen space
     * @param y - y position in screen space
     */
    public abstract void onClick(int x, int y);

    /**
     * Define and Draw the component with a given graphic engine.
     * @param graphic - graphic engine for drawing the component
     */
    public abstract void draw(GraphicEngine graphic);
}
