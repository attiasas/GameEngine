package GTK.Engines.GameState;

import GTK.Engines.Engine.GameEngine;
import GTK.Engines.Engine.GraphicEngine;
import GTK.Engines.Engine.IO.ActionEvent;

import java.util.Stack;

public class GameStateManager
{
    private Stack<GameState> gameStates;

    public GameStateManager()
    {
        gameStates = new Stack<>();
    }

    public boolean showState(GameState state)
    {
        gameStates.push(state);
        return state.onCreate();
    }

    public GameState getState(int index)
    {
        if(gameStates.isEmpty()) return null;
        return gameStates.get(index);
    }

    public GameState removeCurrentState()
    {
        if(gameStates.isEmpty()) return null;
        GameState res = gameStates.pop();
        res.onDestroy();
        return res;
    }

    public void clearAll()
    {
        while (!gameStates.isEmpty())
        {
            gameStates.pop().onDestroy();
        }
    }

    public boolean onUpdate(float fElapsedTime)
    {
        if(gameStates.isEmpty()) return false;
        return gameStates.peek().onUpdate(fElapsedTime);
    }

    public void handleEvent(ActionEvent event)
    {
        if(gameStates.isEmpty()) return;
        gameStates.peek().handleEvent(event);
    }

    public void draw(GraphicEngine graphic)
    {
        if(gameStates.isEmpty()) return;
        gameStates.peek().draw(graphic);
    }
}
