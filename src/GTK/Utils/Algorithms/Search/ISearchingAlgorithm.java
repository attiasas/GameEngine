package GTK.Utils.Algorithms.Search;

/**
 * Created By:      Assaf, on 06/03/2020.
 * Description:     This interface represents an algorithm to solve a search problem
 **/
public interface ISearchingAlgorithm
{
    /**
     * solve a given search problem
     * @param problem - a searchable object implementing a search problem
     * @return - Solution state object of the given problem
     */
    ASearchNode solve(ISearchable problem);

    /**
     * get the number of nodes evaluated during the solve process
     * @return - number of nodes evaluated during the solve process
     */
    int getNumberOfNodesEvaluated();

    /**
     * the name of the algorithm
     * @return - a string representing the name of the algorithm
     */
    String getName();
}
