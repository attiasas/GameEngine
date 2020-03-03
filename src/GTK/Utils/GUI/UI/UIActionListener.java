package GTK.Utils.GUI.UI;

/**
 * Created By:      Assaf, On 23/02/2020
 * Description:     Defines objects that can handle actions in a program.
 *                  This interface will be used with GUI components that have actions that needs to be handle.
 *                  This class helps to abstract components and can use them with any program.
 */
public interface UIActionListener
{
    /**
     * Handle an action.
     */
    void handle();
}
