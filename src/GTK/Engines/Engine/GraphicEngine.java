package GTK.Engines.Engine;

import GTK.Utils.GUI.Graphics.RGB;

import java.awt.*;

/**
 * Created By:      Assaf, On 24/02/2020
 * Description:     Interface for unified Graphic Engine that the Game Engine can use.
 *                  allows to implement graphics for different types of platforms and implements only once for all.
 */
public interface GraphicEngine
{
    /**
     * This method will be called when finishing drawing the state in each iteration of game loop.
     * This will draw the current buffer in the engine to the screen buffer.
     */
    void drawToScreen();

    //region Draw Utils

    /**
     * Set the current color (in rgb format) of the engine for the drawing methods in it.
     * @param r - red value (0 - 255)
     * @param g - green value (0 - 255)
     * @param b - blue value (0 - 255)
     */
    void setColor(int r, int g, int b);

    /**
     * Set the current color (in rgb format) of the engine for the drawing methods in it.
     * @param color - RGB color to set in the engine
     */
    void setColor(RGB color);

    // TODO: Create enum for font styles
    /**
     * Set the current font that the engine will use when drawing strings
     * @param name - font family name
     * @param style - font style
     * @param size - font size
     */
    void setFont(String name, int style, int size);

    // TODO : Getter for fonts

    /**
     * Get the current string height in pixel base on the font that the engine uses
     * @return - the height in pixel
     */
    int getStringHeight();

    /**
     * Get the current string width of a given string base on the font that the engine uses
     * @param txt - txt to check its width
     * @return - the width in pixel of the given txt.
     */
    int getStringWidth(String txt);

    /**
     * Draw a given string to the buffer starting from a given point on the "screen"
     * @param s - txt to draw
     * @param x - x position in the string to start the drawing from
     * @param y - y position in the string to start the drawing from
     */
    void drawString(String s, float x, float y);

    // TODO : Set Size of line
    /**
     * Draw A line starting from a given point on screen to another given point.
     * @param sx - starting x position of the line on the screen
     * @param sy - starting y position of the line on the screen
     * @param ex - ending x position of the line on the screen
     * @param ey - ending y position of the line on the screen
     */
    void drawLine(float sx, float sy, float ex, float ey);

    /**
     * Fill a Circle on the screen
     * @param x - x position of the center of the circle
     * @param y - y position of the center of the circle
     * @param r - radius of the circle in pixel
     */
    void fillCircle(float x, float y, float r);

    /**
     * Fill a rectangle on the screen base on a given parameters
     * @param x - x position of the bottom (in screen space, actual will be in the upper of the screen) left corner of the screen
     * @param y - y position of the bottom (in screen space, actual will be in the upper of the screen) left corner of the screen
     * @param w - width of the rectangle
     * @param h - height of the rectangle
     */
    void fillRectangle(float x, float y, float w, float h);

    /**
     * Fill a rectangle on the screen base on a given rectangle
     * @param rectangle - rectangle to draw
     */
    void fillRectangle(Rectangle rectangle);

    //endregion
}
