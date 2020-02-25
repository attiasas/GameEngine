package Engines;

import Engines.Engine.GameEngine;
import Engines.PC.EnginePC;

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
