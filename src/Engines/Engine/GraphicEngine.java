package Engines.Engine;

import Utils.GUI.Graphics.RGB;

import java.awt.*;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public interface GraphicEngine
{
    void drawToScreen();

    //region Draw Utils
    void setColor(int r, int g, int b);
    void setColor(RGB color);
    void setFont(String name, int style, int size);
    int getStringHeight();
    int getStringWidth(String txt);

    void drawString(String s, float x, float y);
    void drawLine(float sx, float sy, float ex, float ey);
    void fillCircle(float x, float y, float r);
    void fillRectangle(float x, float y, float w, float h);
    void fillRectangle(Rectangle rectangle);

    //endregion
}
