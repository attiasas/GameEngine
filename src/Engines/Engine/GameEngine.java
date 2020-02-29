package Engines.Engine;

import Engines.Game;
import Utils.GUI.UI.UIManager;

/**
 * Created By: Assaf, On 23/02/2020
 * Description:
 */
public abstract class GameEngine implements Runnable
{
    protected Game currentGame;

    // dimensions and information
    private int screenWidth;
    private int screenHeight;
    public String gameTitle;

    // graphics
    public GraphicEngine graphicEngine;

    // game Thread
    private Thread thread;
    private volatile boolean running;
    private long startTime;
    private final int MAXFPS = 1000;
    private final long sleepTime = 1000 / MAXFPS;

    public GameEngine(Game game)
    {
        currentGame = game;
    }

    public abstract void constructFrame(int width, int height, String title);

    public void constructFrame(int width, int height)
    {
        constructFrame(width,height, "5m1l3 Engines");
    }

    public abstract void setTitle(String title);
    public abstract void setVisible(boolean b);

    @Override
    public void run()
    {
        // init
        if(!currentGame.onCreate()) return;

        UIManager.set(graphicEngine);

        // Start
        setVisible(true);

        long timeStamp;
        long lastTimeStamp = System.currentTimeMillis();
        float elapsed;
        long wait;

        startTime = lastTimeStamp;

        while (running)
        {
            timeStamp = System.currentTimeMillis();
            elapsed = (float)(timeStamp - lastTimeStamp) / 1000;

            setTitle(gameTitle + " - " + (int)(1 / elapsed) + "fps");

            try
            {
                running = currentGame.onUpdate(elapsed);

                currentGame.draw(graphicEngine);

                graphicEngine.drawToScreen();
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

    //endregion

    //region Getters And Setters

    public void setScreenWidth(int screenWidth) { this.screenWidth = screenWidth; }
    public void setScreenHeight(int screenHeight) { this.screenHeight = screenHeight; }

    public int ScreenWidth() { return screenWidth; }
    public int ScreenHeight() { return screenHeight; }
    //endregion
}
