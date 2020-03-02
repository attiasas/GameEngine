package GTK.Engines.GameState;

import GTK.Engines.Engine.GraphicEngine;
import GTK.Engines.Engine.IO.ActionEvent;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public interface GameState
{
    public boolean onCreate();
    public void onDestroy();
    public boolean onUpdate(float fElapsedTime);
    public void handleEvent(ActionEvent event);
    public void draw(GraphicEngine graphic);
}
