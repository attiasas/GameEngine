package GTK.Utils.Algorithms.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created By:      Assaf, on 06/03/2020.
 * Description:     This class represents an abstract state of a search problem
 *
 * Notes:           * This class needs to be extended for each problem
 *                  * This class must override equals and hash methods to work.
 **/
public abstract class ASearchNode
{
    private ASearchNode prev;
    private double cost;

    /**
     * Constructor
     * @param prev - the node (state) that is previous to this node.
     * @param cost - the cost of the path from the start until this node
     */
    public ASearchNode(ASearchNode prev, double cost)
    {
        this.prev = prev;
        this.cost = cost;
    }

    /**
     * Constructor
     * @param prev - the node (state) that is previous to this node.
     */
    public ASearchNode(ASearchNode prev) { this(prev,0);}

    /**
     * Constructor
     */
    public ASearchNode() { this(null); }

    /**
     * Getter for the precursor of the state
     * @return - precursor of the state
     */
    public ASearchNode getPrev() { return prev; }

    /**
     * Goal Function, is this state a goal state.
     * @return true if a goal state
     */
    public abstract boolean     isGoal();

    /**
     * Get the current Heuristic for this state.
     * Heuristic - the cost for the path from this state to the goal state.
     * @return heuristic value for the current state
     */
    abstract public double 		H();

    /**
     * Get the current cost of the path from the start state to the current state
     * @return the cost value of the path from the start to this state.
     */
    public double getCost() { return cost; }

    /**
     * Set the cost of the current node
     * @param cost - new cost of the node
     */
    public void setCost(double cost) { this.cost = cost; }

    /**
     * Get the estimated value of the path from the start to the goal that this node includes in.
     * @return the cost of the path from the start to the goal that goes throw this node.
     */
    public double 		F() { return getCost() + H(); }

    /**
     * Get the search depth from the start to this state
     * @return - depth of the current path from the start to this state
     */
    public int			Depth() { return getPath().size(); }

    /**
     * Get the current path from the start to this node.
     * Path will be sorted from the current state (index 0) to the start state (reverse order)
     * @return
     */
    public List<ASearchNode> getPath()
    {
        ArrayList<ASearchNode> path = new ArrayList<>();

        ASearchNode currentState = prev;

        while (currentState != null)
        {
            path.add(currentState);
            currentState = currentState.prev;
        }

        return path;
    }
}
