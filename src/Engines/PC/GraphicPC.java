package Engines.PC;

import Engines.Engine.GraphicEngine;
import Utils.UI.Graphics.RGB;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public class GraphicPC implements GraphicEngine
{
    private Graphics2D graphics;
    private BufferedImage buffer;
    private JPanel panel;

    private int width;
    private int height;

    public GraphicPC(int width, int height, JPanel panel)
    {
        this.width = width;
        this.height = height;
        this.panel = panel;

        buffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.getGraphics();
    }

    public void drawToScreen()
    {
        Graphics graphics2 = panel.getGraphics();

        graphics2.drawImage(buffer,0,0,width,height,null);
        graphics2.dispose();
    }

    @Override
    public void setColor(int r, int g, int b)
    {
        graphics.setColor(new java.awt.Color(r,g,b));
    }
    @Override
    public void setColor(RGB color)
    {
        setColor(color.r,color.g,color.b);
    }
    @Override
    public void setFont(String name, int style, int size)
    {
        graphics.setFont(new Font(name,style,size));
    }
    @Override
    public int getStringHeight()
    {
        return graphics.getFontMetrics().getHeight();
    }
    @Override
    public int getStringWidth(String txt)
    {
        return graphics.getFontMetrics().stringWidth(txt);
    }
    @Override
    public void drawString(String s, float x, float y)
    {
        graphics.drawString(s,x,y);
    }
    @Override
    public void drawLine(float sx, float sy, float ex, float ey)
    {
        graphics.drawLine((int)sx,(int)sy,(int)ex,(int)ey);
    }
    @Override
    public void fillCircle(float x, float y, float r)
    {
        graphics.fillArc((int)(x - r),(int)(y-r),(int)(2 * r),(int)(2 * r),0,360);
    }
    @Override
    public void fillRectangle(float x, float y, float w, float h)
    {
        graphics.fillRect((int)x,(int)y,(int)w,(int)h);
    }

    @Override
    public void fillRectangle(Rectangle rectangle)
    {
        fillRectangle(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }
}
