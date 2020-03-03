package GTK.Utils.GUI.UI;

import GTK.Engines.Engine.GraphicEngine;

/**
 * ** Optional feature of the game engine. **
 * Created By:      Assaf, On 23/02/2020
 * Description:     Responsible of managing all the UI components of the game.
 *
 *                  * Granting static access to the current graphic engine (and for building components).
 *                  * Enables to dynamically calculates positions of components and change them.
 *                  * Managing and storing all the graphical information of the Game States.
 *
 * Use Info:        * Manager will be created only if access happened in 'onCreate()' of the initial game state
 *                  * For showing the components in the game, in the game state 'draw()' method this manager drawing needs to be called.
 *
 **/
public class UIManager extends UIContainer
{
    private static UIManager manager;

    public GraphicEngine graphicEngine;

    private UIManager()
    {

    }

    /**
     * Getter for the manager singleton, for access.
     * @return - the manager instance with the graphic information
     */
    public static UIManager get()
    {
        if(manager == null) manager = new UIManager();
        return manager;
    }

    /**
     * This method will be called after the initial game state created and will set the graphic to the
     * singleton if it was accessed and being used in the program.
     * @param engine - graphic engine of the current platform
     */
    public static void set(GraphicEngine engine)
    {
        if(manager == null) return;

        manager.graphicEngine = engine;
        manager.build(engine);
    }

    /**
     * Build all the components that are contains in the manager.
     */
    public void build() { if(graphicEngine != null) build(graphicEngine); }

    /**
     * Build any given UI component without building the others, with the current graphic of the platform
     * @param component - component to build
     */
    public void buildComponent(UIComponent component) { if(graphicEngine != null) component.build(graphicEngine); }

    @Override
    public void draw(GraphicEngine graphic)
    {
        for(int i = 0; i < components.size(); i++)
        {
            UIComponent component = components.get(i);
            if(component.show)
            {
                graphic.setColor(component.color);
                component.draw(graphic);
            }
        }
    }


}
