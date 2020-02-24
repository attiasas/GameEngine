package Engines;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public abstract class GraphicEngine
{
    public abstract void drawToScreen();
    public abstract void setVisible(boolean b);

    //region Draw Utils
    public enum Colors
    {
        BLACK,WHITE,RED,BLUE,GREEN
    }

    public abstract void setColor(int r, int g, int b);
    public abstract void setColor(Colors color);
    public abstract void setFont(String name, int style, int size);
    public abstract int getStringHeight();
    public abstract int getStringWidth(String txt);

    public abstract void drawString(String s, float x, float y);
    public abstract void drawLine(float sx, float sy, float ex, float ey);
    public abstract void fillCircle(float x, float y, float r);
    public abstract void fillRectangle(float x, float y, float w, float h);
    //endregion
}
