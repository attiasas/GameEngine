package Engines.PC;

import Engines.*;
import Engines.Engine.GameEngine;

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

    public EnginePC(Game game)
    {
        super(game);
    }

    public void constructFrame(int width, int height, String title)
    {
        panel = new JPanel();

        panel.setPreferredSize(new Dimension(width,height));
        panel.setFocusable(true);
        panel.requestFocus();

        if(currentGame instanceof KeyListener) panel.addKeyListener((KeyListener) currentGame);
        if(currentGame instanceof MouseListener) panel.addMouseListener((MouseListener) currentGame);
        if(currentGame instanceof MouseMotionListener) panel.addMouseMotionListener((MouseMotionListener)currentGame);

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
