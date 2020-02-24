package Engines;

import Engines.PC.EnginePC;
import Engines.PC.GraphicPC;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public class GameEngines
{
    public static GameEngine PC(Game game)
    {
        return new EnginePC(game);
    }
}
