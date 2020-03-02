package GTK.Engines.EngineTypes.PC;

import GTK.Engines.Engine.IO.ActionEvent;
import GTK.Engines.Engine.GameEngine;
import GTK.Engines.GameState.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public class EnginePC extends GameEngine
{
    private JFrame frame;
    private JPanel panel;

    private boolean listenKey;
    private boolean listenMouse;

    public EnginePC(GameState gameState)
    {
        this(gameState,true,true);
    }

    public EnginePC(GameState gameState, boolean listenKey, boolean listenMouse)
    {
        super(new GameStateManagerPC(),gameState);

        this.listenKey = listenKey;
        this.listenMouse = listenMouse;
    }

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
