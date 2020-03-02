package GTK.Engines;

import GTK.Engines.Engine.GameEngine;
import GTK.Engines.GameState.GameState;
import GTK.Engines.EngineTypes.PC.EnginePC;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public class GameEngines
{
    public static GameEngine PC(GameState gameState) { return new EnginePC(gameState); }
}
