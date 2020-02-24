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

    public EnginePC(Game game)
    {
        super(game);
    }

    public void constructFrame(int width, int height, String title)
    {
        graphicEngine = new GraphicPC(width,height);

        frame = new JFrame();

        frame.setBounds(0,0,width,height);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(((GraphicPC)graphicEngine));

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
