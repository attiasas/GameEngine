package GTK.Utils.Algorithms.Search;

import java.util.List;

/**
 * Created By:      Assaf, on 06/03/2020.
 * Description:     This Interface defines a searchable object for a search problem
 **/
public interface ISearchable
{
    /**
     * Get the initial state of the problem
     * @return - AState object that represents the start
     */
    ASearchNode getStartState();

    /**
     * Get the Goal state of the problem
     * @return - AState object that represents the goal
     */
    ASearchNode getGoalState();

    /**
     * Get all the successors of a given state
     * @param state - a state to get the successors from
     * @return - array list of all the successors states of the given state
     */
    List<ASearchNode> getAllPossibleStates(ASearchNode state);
}
