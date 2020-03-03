package GTK.Engines;

import GTK.Engines.Engine.GameEngine;
import GTK.Engines.GameState.GameState;
import GTK.Engines.EngineTypes.PC.EnginePC;

/**
 * Created By: Assaf, On 24/02/2020
 * Description: class with static methods to create all types of implemented engines
 */
public class GameEngines
{
    /**
     * Create PC Game Engine With key and mouse listener
     * @param gameState
     * @return
     */
    public static GameEngine PC(GameState gameState) { return new EnginePC(gameState); }
}
