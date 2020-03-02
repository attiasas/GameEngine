package GTK.Engines.GameState;

public class GameStates
{
    private static GameStates instance;

    private GameStateManager manager;

    private GameStates(GameStateManager manager)
    {
        if(manager == null) throw new IllegalArgumentException("Manager cannot be null");
        this.manager = manager;
    }

    public static void setManager(GameStateManager manager)
    {
        instance = new GameStates(manager);
    }

    public static boolean showState(GameState state)
    {
        if(instance == null) return false;
        return instance.manager.showState(state);
    }

    public static GameState getState(int index)
    {
        if(instance == null) return null;
        return instance.manager.getState(index);
    }

    public static GameState removeCurrentState()
    {
        if(instance == null) return null;
        return instance.manager.removeCurrentState();
    }
}
