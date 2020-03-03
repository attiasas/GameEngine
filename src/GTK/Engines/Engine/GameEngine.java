package GTK.Engines.Engine;

import GTK.Engines.GameState.GameState;
import GTK.Engines.GameState.GameStateManager;
import GTK.Engines.GameState.GameStates;
import GTK.Utils.GUI.UI.UIManager;

/**
 * Created By:      Assaf, On 23/02/2020
 * Description:     Main Engine that responsible for Running Games that implements GameStates.
 *                  * Game Loop will be running in different thread when calling start function.
 *                  * This Class will be extended for each platform and will adapt all necessary features.
 *                  * This Class uses GameStateManager that needs to be extended as well for each platform.
 *                  * This class needs to be provided with an initialize game state to run.
 */
public abstract class GameEngine implements Runnable
{
    private GameState startGameState;
    protected GameStateManager manager;

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
    private final int MAX_FPS = 1000;
    private final long sleepTime = 1000 / MAX_FPS;

    /**
     * Constructor
     * @param manager - GameStateManager for the current platform
     * @param gameState - starting game state that will be the first to shown
     */
    public GameEngine(GameStateManager manager, GameState gameState)
    {
        startGameState = gameState;
        this.manager = manager;
    }

    /**
     * This method needs to be called first before starting the engine.
     * Construct all the engines and graphic frames for the current platform.
     * @param width - screen (frame) width
     * @param height - screen (frame) height
     * @param title - Game Title
     */
    public abstract void constructFrame(int width, int height, String title);

    /**
     * Construct frame without title to the game.
     * @param width - screen (frame) width
     * @param height - screen (frame) height
     */
    public void constructFrame(int width, int height)
    {
        constructFrame(width,height, "5m1l3 GTK.Engines");
    }

    /**
     * Set The Title of the game to the current platform frame
     * @param title
     */
    public abstract void setTitle(String title);

    /**
     * Make the current panel/graphics/frame visible, this method will be called
     * after initialization and before game loop starts.
     * @param b - true to make it visible and finish all preparations
     */
    public abstract void setVisible(boolean b);

    /**
     * Main Game Loop for updating and drawing the game states.
     */
    @Override
    public void run()
    {
        // init
        GameStates.setManager(manager);

        if(!manager.showState(startGameState)) return;

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
                running = manager.onUpdate(elapsed);

                manager.draw(graphicEngine);

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

        manager.clearAll();
    }

    /**
     * Stop the Engine and shut down the game main loop and thread
     */
    public void stop() { running = false; }

    /**
     * Start running the engine.
     * This method can only be called after constructing the frame.
     * This method will generate a Thread for the engine game loop.
     */
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

    /**
     * Set The width size of the Screen
     * @param screenWidth - current screen width in pixel
     */
    public void setScreenWidth(int screenWidth) { this.screenWidth = screenWidth; }
    /**
     * Set The Height size of the Screen
     * @param screenHeight - current screen Height in pixel
     */
    public void setScreenHeight(int screenHeight) { this.screenHeight = screenHeight; }

    /**
     * Set The width size of the Screen
     * @return - current screen width in pixel
     */
    public int ScreenWidth() { return screenWidth; }
    /**
     * Set The Height size of the Screen
     * @return - current screen width in pixel
     */
    public int ScreenHeight() { return screenHeight; }
    //endregion
}
