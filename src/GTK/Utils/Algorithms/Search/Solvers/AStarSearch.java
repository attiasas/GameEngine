package GTK.Utils.Algorithms.Search.Solvers;

import GTK.Utils.Algorithms.Search.ASearchNode;
import GTK.Utils.Algorithms.Search.ISearchable;
import GTK.Utils.Algorithms.Search.ISearchingAlgorithm;

import java.util.*;

/**
 * Created By:      Assaf, on 06/03/2020.
 * Description:     This class implements A* search algorithm
 **/
public class AStarSearch implements ISearchingAlgorithm
{
    private PriorityQueue<ASearchNode> openList;
    private HashMap<Integer,ASearchNode> openUpdateList;
    private HashSet<ASearchNode> closeList;
    private int numOfEvaluatedNodes;

    /**
     * Reset data structures
     */
    private void reset()
    {
        openList = new PriorityQueue<>(Comparator.comparingDouble(ASearchNode::F).thenComparingDouble(ASearchNode::H));
        openUpdateList = new HashMap<>();
        closeList = new HashSet<>();
        numOfEvaluatedNodes = 0;
    }

    /**
     * Get the best node to explore next.
     * @return next node to explore or null if open list is empty
     */
    private ASearchNode getBest()
    {
        ASearchNode result = null;

        if(!openList.isEmpty())
        {
            result = openList.poll();
            numOfEvaluatedNodes++;
        }
        return result;
    }

    /**
     * Change the open list, add if not exists or change (relax) cost values if needed
     * @param nodeToAdd - node to check if change in the lists are needed
     */
    private void changeOpen(ASearchNode nodeToAdd)
    {
        if(!openUpdateList.containsKey(nodeToAdd))
        {
            openList.add(nodeToAdd);
            openUpdateList.put(nodeToAdd.hashCode(),nodeToAdd);
        }
        else
        {
            ASearchNode stored = openUpdateList.get(nodeToAdd);
            if(stored.getCost() > nodeToAdd.getCost())
            {
                stored.setCost(nodeToAdd.getCost());
            }
        }
    }


    @Override
    public ASearchNode solve(ISearchable problem)
    {
        // Init
        reset();
        ASearchNode Vs = problem.getStartState();
        changeOpen(Vs);

        // Search
        ASearchNode current	= null;

        while (openList.size() > 0)
        {
            current = getBest();
            if (current.isGoal())
                return current;
            List<ASearchNode> neighbors = problem.getAllPossibleStates(current);
            for (ASearchNode Vn : neighbors)
            {
                if (closeList.contains(Vn)) continue;

                changeOpen(Vn);
            }
            closeList.add(current);
        }
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() { return numOfEvaluatedNodes; }

    @Override
    public String getName() { return "A* (A Star)"; }
}
