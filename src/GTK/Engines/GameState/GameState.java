package GTK.Engines.GameState;

import GTK.Engines.Engine.GraphicEngine;
import GTK.Engines.Engine.IO.ActionEvent;

/**
 * Created By:  Assaf, On 24/02/2020
 * Description: Defines a state of a game and can be use with the GameEngine.
 *              When Developing a game that can be used with the game engine this interface
 *              needs to be implemented.
 */
public interface GameState
{
    /**
     * This method will be called when the game state is loaded and shown.
     * @return true if the creation is completed, if false is returned the state will not load
     */
    public boolean onCreate();

    /**
     * This method will be called when the game state is terminated
     */
    public void onDestroy();

    /**
     * This method will be called every frame before drawing the current state in the game loop.
     * thus method will update the current game state and contains the main logic of the game.
     * @param fElapsedTime - time in miliseconds that passed since the last update, can be used with movements
     *                       to insure smooth transitions and real time updates.
     * @return true while the game loop needs to continue, when false is returned the engine will stop.
     */
    public boolean onUpdate(float fElapsedTime);

    /**
     * This method will be called every time an action (input) event occurs.
     * this method will Handle all types of input events that the game contains.
     * if the event consumed flag is marked the event will not be passed to the next state.
     * @param event - current action event that has occurred.
     */
    public void handleEvent(ActionEvent event);

    /**
     * This method will be called every frame after updating the current state in the game loop.
     * this method will draw all the content of the game state.
     * @param graphic unified graphic engine that contains drawing methods
     */
    public void draw(GraphicEngine graphic);
}
