package Engines.PC;

import Engines.*;

import javax.swing.*;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public class EnginePC extends GameEngine
{
    private JFrame frame;

    public EnginePC(Game game, GraphicPC graphicEngine)
    {
        super(game,graphicEngine);
    }

    public void constructFrame(int width, int height, String title)
    {
        if(!(graphicEngine instanceof GraphicPC)) return;

        frame = new JFrame();

        frame.setBounds(0,0,width,height);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(((GraphicPC)graphicEngine).getPanel());

        setScreenWidth(width);
        setScreenHeight(height);
        gameTitle = title;
    }

    @Override
    public void setTitle(String title) {
        frame.setTitle(title);
    }
}
