package GTK.Engines.EngineTypes.PC;

import GTK.Engines.Engine.GameEngine;
import GTK.Engines.GameState.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created By:      Assaf, On 24/02/2020
 * Description:     Game Engine for PC platforms.
 *
 * Using:           * GameStateManager - GameStateManagerPC (potential inputs: Keyboard, Mouse)
 *                  * GraphicEngine - Graphic engine using Jframe and Jpanel.
 */
public class EnginePC extends GameEngine
{
    private JFrame frame;
    private JPanel panel;

    private boolean listenKey;
    private boolean listenMouse;

    /**
     * Constructor for Game Engine PC that listen to keyboard and mouse events
     * @param gameState - initial state when loading the engine
     */
    public EnginePC(GameState gameState)
    {
        this(gameState,true,true);
    }

    /**
     * Constructor for the engine with optional parameters
     * @param gameState - initial state when loading the engine
     * @param listenKey - is the engine listening to keyboard events
     * @param listenMouse - is the engine listening to mouse events
     */
    public EnginePC(GameState gameState, boolean listenKey, boolean listenMouse)
    {
        super(new GameStateManagerPC(),gameState);

        this.listenKey = listenKey;
        this.listenMouse = listenMouse;
    }

    @Override
    public void constructFrame(int width, int height, String title)
    {
        panel = new JPanel();

        panel.setPreferredSize(new Dimension(width,height));
        panel.setFocusable(true);
        panel.requestFocus();

        if(listenKey)panel.addKeyListener((KeyListener) manager);
        if(listenMouse)
        {
            panel.addMouseListener((MouseListener) manager);
            panel.addMouseMotionListener((MouseMotionListener) manager);
        }

        graphicEngine = new GraphicPC(width,height,panel);

        frame = new JFrame();
        frame.setBounds(0,0,width,height);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(panel);

        setScreenWidth(width);
        setScreenHeight(height);
        gameTitle = title;
    }

    @Override
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    @Override
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
