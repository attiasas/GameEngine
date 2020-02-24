package Engines;

/**
 * Created By: Assaf, On 24/02/2020
 * Description:
 */
public interface Game
{
    public boolean onCreate();
    public boolean onUpdate(float fElapsedTime);
    public void draw(GraphicEngine graphic);
}
