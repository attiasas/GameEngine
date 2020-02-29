package Utils.UI.Components;

import Engines.Engine.GraphicEngine;

public class UIManager extends UIContainer
{
    private static UIManager manager;

    public GraphicEngine graphicEngine;

    private UIManager()
    {

    }

    public static UIManager get()
    {
        if(manager == null) manager = new UIManager();
        return manager;
    }

    public static void set(GraphicEngine engine)
    {
        if(manager == null) return;

        manager.graphicEngine = engine;
        manager.build(engine);
    }

    public void build() { if(graphicEngine != null) build(graphicEngine); }
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
