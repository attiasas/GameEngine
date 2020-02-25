package Engines.Engine;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public interface GraphicEngine
{
    void drawToScreen();

    //region Draw Utils
    public enum Colors
    {
        BLACK,WHITE,RED,BLUE,GREEN
    }

    void setColor(int r, int g, int b);
    void setColor(Colors color);
    void setFont(String name, int style, int size);
    int getStringHeight();
    int getStringWidth(String txt);

    void drawString(String s, float x, float y);
    void drawLine(float sx, float sy, float ex, float ey);
    void fillCircle(float x, float y, float r);
    void fillRectangle(float x, float y, float w, float h);
    //endregion
}
