package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created By: Assaf, On 23/02/2020
 * Description:
 */
public abstract class GameEngine extends JPanel implements KeyListener, MouseListener, MouseMotionListener,Runnable
{
    // dimensions and information
    private int screenWidth;
    private int screenHeight;
    public String gameTitle;

    // graphics
    private JFrame frame = null;
    private BufferedImage buffer;
    private Graphics2D graphics;

    // game Thread
    private Thread thread;
    private volatile boolean running;
    private long startTime;
    private final int MAXFPS = 1000;
    private final long sleepTime = 1000 / MAXFPS;

    public GameEngine(boolean keyListener,boolean mouseListener)
    {
        super();
        if(keyListener) addKeyListener(this);
        if(mouseListener)
        {
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        //setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        requestFocus();
    }

    public GameEngine() { this(true,true); }

    public abstract boolean onCreate();
    public abstract boolean onUpdate(float fElapsedTime);
    public abstract void draw(Graphics g);

    //region Construction and Game Loop
    public void constructFrame(int width, int height)
    {
        constructFrame(width,height, "5m1l3 Game Engine");
    }

    public void constructFrame(int width, int height, String title)
    {
        frame = new JFrame();

        setPreferredSize(new Dimension(width,height));
        frame.setBounds(0,0,width,height);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(this);

        screenWidth = width;
        screenHeight = height;
        gameTitle = title;

        buffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.getGraphics();
    }

    @Override
    public void run()
    {
        if(!onCreate()) return;

        // show panel
        frame.setVisible(true);

        long timeStamp;
        long lastTimeStamp = System.currentTimeMillis();
        float elapsed;
        long wait;

        startTime = lastTimeStamp;

        //System.out.println("Frame: " + frame + " | buffer: " + buffer + " | graphics: " + graphics);

        while (running)
        {
            timeStamp = System.currentTimeMillis();
            elapsed = (float)(timeStamp - lastTimeStamp) / 1000;

            frame.setTitle(gameTitle + " - " + (int)(1 / elapsed) + "fps");

            //System.out.println("FPS: " + (1 / elapsed));

            try
            {
                running = onUpdate(elapsed);
                draw(graphics);
                drawToScreen();
            }
            catch (Exception e) { e.printStackTrace(); }

            wait = sleepTime - (timeStamp - lastTimeStamp) / 1000;
            if(wait > 0)
            {
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            lastTimeStamp = timeStamp;
        }
    }

    public void stop() { running = false; }

    public void start()
    {
        thread = new Thread(this);

        running = true;
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void drawToScreen()
    {
        Graphics graphics2 = getGraphics();
        graphics2.drawImage(buffer,0,0,screenWidth,screenHeight,null);
        graphics2.dispose();
    }
    //endregion

    //region Getters And Setters
    public int ScreenWidth() { return screenWidth; }
    public int ScreenHeight() { return screenHeight; }
    //endregion

    //region Default inherited Functions
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseDragged(MouseEvent e) { }
    @Override
    public void mouseMoved(MouseEvent e) { }
    //endregion

    //region Drawing functions

    //region Draw Utils
    public enum Colors
    {
        BLACK,WHITE,RED,BLUE,GREEN
    }

    public void setColor(Colors color)
    {
        switch (color)
        {
            case BLACK: graphics.setColor(java.awt.Color.BLACK); break;
            case WHITE: graphics.setColor(java.awt.Color.WHITE); break;
            case RED:   graphics.setColor(java.awt.Color.RED); break;
            case BLUE:  graphics.setColor(java.awt.Color.BLUE); break;
            case GREEN: graphics.setColor(java.awt.Color.GREEN); break;
        }
    }

    public void setColor(int r, int g, int b)
    {
        graphics.setColor(new java.awt.Color(r,g,b));
    }

    public void setFont(String name, int style, int size)
    {
        graphics.setFont(new Font(name,style,size));
    }

    public int getStringHeight()
    {
        return graphics.getFontMetrics().getHeight();
    }

    public int getStringWidth(String txt)
    {
        return graphics.getFontMetrics().stringWidth(txt);
    }
    //endregion

    public void drawString(String s, float x, float y)
    {
        graphics.drawString(s,x,y);
    }

    public void drawLine(float sx, float sy, float ex, float ey)
    {
        graphics.drawLine((int)sx,(int)sy,(int)ex,(int)ey);
    }

    public void fillCircle(float x, float y, float r)
    {
        graphics.fillArc((int)(x - r),(int)(y-r),(int)(2 * r),(int)(2 * r),0,360);
    }

    public void fillRectangle(float x, float y, float w, float h)
    {
        graphics.fillRect((int)x,(int)y,(int)w,(int)h);
    }
    //endregion
}
