package GTK.Engines.GameState;

import GTK.Engines.Engine.GraphicEngine;
import GTK.Engines.Engine.IO.ActionEvent;

import java.util.Stack;

/**
 * Created By:      Assaf, On 24/02/2020
 * Description:     This class responsible for managing the game state in the game engine.
 *                  capable of adding/removing states, storing is base on a stack.
 *                  the peek of the stack (the most recent state) is the state that will be shown.
 *                  (Suggestion: Menu State will be the first to load - always return to it).
 *
 *                  * This class can be extended to adapt all the available inputs sources.
 **/
public class GameStateManager
{
    private Stack<GameState> gameStates;

    /**
     * Constructor
     */
    public GameStateManager()
    {
        gameStates = new Stack<>();
    }

    /**
     * Load new state to the top of the state stack and make it the current game state in the game.
     * @param state - new state to start and load as the current game state
     * @return true if creating is success and the game state is loaded, if false is returned the state is not loaded.
     */
    public boolean showState(GameState state)
    {
        boolean result = state.onCreate();
        if(result) gameStates.push(state);

        return result;
    }

    /**
     * Get a state that is stored in the state stack. (index) base on the inserted order.
     * @param index - index of the state base on the inserted order (the more recent insertion is the highest index)
     * @return Game State that is currently stored
     */
    public GameState getState(int index)
    {
        if(gameStates.isEmpty()) return null;
        return gameStates.get(index);
    }

    /**
     * Remove and terminate the current showing state and returning it.
     * The state that will be shown is the state that was stored previously.
     * If the last state is removed the engine will terminate
     * @return - the game state that was terminated
     */
    public GameState removeCurrentState()
    {
        if(gameStates.isEmpty()) return null;

        GameState res = gameStates.pop();
        res.onDestroy();
        return res;
    }

    /**
     * Terminate and destroy all the states that remains in the stack.
     */
    public void clearAll()
    {
        while (!gameStates.isEmpty())
        {
            gameStates.pop().onDestroy();
        }
    }

    /**
     * This Method will be called in the main game loop and will update the current state.
     * @param fElapsedTime - time in miliseconds since the last update.
     * @return - true if the state is still running. if false is returned the engine will shut down.
     */
    public boolean onUpdate(float fElapsedTime)
    {
        if(gameStates.isEmpty()) return false;
        return gameStates.peek().onUpdate(fElapsedTime);
    }

    /**
     * This Method will pass the event to all the currently stored states base on the shown order.
     * the currently shown state will be the first to handle the event.
     * if the event consumed flag is marked the event will not be passed to the next state.
     * @param event - current Action (input) event.
     */
    public void handleEvent(ActionEvent event)
    {
        if(gameStates.isEmpty()) return;

        for(int i = gameStates.size() - 1; i >= 0  && !event.consumed; i--)
        {
            gameStates.get(i).handleEvent(event);
        }
    }

    /**
     * This method will be called in the main game loop and it will draw the current shown state.
     * @param graphic - graphic engine for drawing the current state
     */
    public void draw(GraphicEngine graphic)
    {
        if(gameStates.isEmpty()) return;
        gameStates.peek().draw(graphic);
    }
}
