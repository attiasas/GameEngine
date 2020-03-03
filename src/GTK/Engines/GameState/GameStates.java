package GTK.Engines.GameState;

/**
 * Created By:      Assaf, On 24/02/2020
 * Description:     Proxy to the current GameStateManager allowing static access from all the states.
 *                  Manager will be set by the current engine when it starts.
 *                  Providing access only to get all states (communication between them), Add/Remove states.
 */
public class GameStates
{
    private static GameStates instance;

    private GameStateManager manager;

    private GameStates(GameStateManager manager)
    {
        if(manager == null) throw new IllegalArgumentException("Manager cannot be null");
        this.manager = manager;
    }

    /**
     * This Method is called when the game engine is starting and will set dynamically the manager that is used.
     * Initialize the instance of this singleton, All other static method will be useless (will fail)
     * if this method is not called at least once before calling them.
     * @param manager
     */
    public static void setManager(GameStateManager manager)
    {
        instance = new GameStates(manager);
    }

    // Proxy For Adding new State to the manager.
    /**
     * Load new state to the top of the state stack and make it the current game state in the game.
     * @param state - new state to start and load as the current game state
     * @return true if creating is success and the game state is loaded, if false is returned the state is not loaded.
     */
    public static boolean showState(GameState state)
    {
        if(instance == null) return false;
        return instance.manager.showState(state);
    }

    // Proxy For getting a State from the manager.
    /**
     * Get a state that is stored in the state stack. (index) base on the inserted order.
     * @param index - index of the state base on the inserted order (the more recent insertion is the highest index)
     * @return Game State that is currently stored
     */
    public static GameState getState(int index)
    {
        if(instance == null) return null;
        return instance.manager.getState(index);
    }

    // Proxy For terminating the current State from the manager.
    /**
     * Remove and terminate the current showing state and returning it.
     * The state that will be shown is the state that was stored previously.
     * If the last state is removed the engine will terminate
     * @return - the game state that was terminated
     */
    public static GameState removeCurrentState()
    {
        if(instance == null) return null;
        return instance.manager.removeCurrentState();
    }
}
