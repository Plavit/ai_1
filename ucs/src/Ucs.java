// uniform-cost search

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

class Ucs<S, A> {
    public static <S, A> Solution<S, A> search(Problem<S, A> prob) {
        /// Your implementation goes here.

        //If empty assignment, simple solution
        if (prob.isGoal(prob.initialState())) {
            return new Solution<S, A>(prob.actions(prob.initialState()), prob.initialState(), 0);
        }

//        Node node;
        PriorityQueue<Node<S, A>> frontier = new PriorityQueue<Node<S, A>>();
        var initialNode = new Node<S, A>(prob.initialState());


        HashSet<Node<S, A>> explored = new HashSet<Node<S, A>>();
        HashMap<S, Node<S, A>> frontierMap = new HashMap<S, Node<S, A>>();

        initialNode.setCost(0);
        frontier.add(initialNode);
        frontierMap.put(prob.initialState(), initialNode);

        // GRAPH
        // The minimum-cost solution is 6.0, passing through vertices
        //    0 -> 2 -> 1 -> 3 -> 4.


        // LINE
        // A simple puzzle involving movement on a line.
        // You start at square 0.  In each move you may move right either
        // - by 1 square (cost = 8)
        // - by 2 squares (cost = 3)
        // - by 3 squares (cost = 5)
        //
        // The goal is to get to square 101 with minimal total cost.
        // The minimal solution has cost = 152 (e.g. move 49 times by 2, then once by 3.)


        // GRID
        // A simple puzzle involving movement on a grid.
        //
        // You start at (0, 0).  In each move you may move either
        //   - left 1 (cost = 1)
        //   - right 1 (cost = 1)
        //   - up 1 (cost = 1)
        //   - down 1 (cost = 1)
        //   - right 1, up 2 (cost = 2)
        //   - right 2, up 2 (cost = 5)
        //
        // The goal is to get to (80, 80) with minimal total cost.
        // The cheapest solution has cost = 120.



        //While there are unexplored nodes in the frontier
        while (!frontier.isEmpty()) {

            Node<S, A> node = frontier.poll();

            //if solution is found, return
            if (prob.isGoal(node.state)) {
//                System.out.println("Solution found. Node state: " + node.state + "; Cost: " + node.getCost());
                return new Solution<S, A>(prob.actions(node.state), node.state, node.getCost());
            }

            //loop prevention - do not go into explored nodes
            if (!explored.contains(node)) {
                List<A> actions = prob.actions(node.state);

                // go through all possible actions from node
                for (A action : actions) {
                    S targetState = prob.result(node.state, action);

                    // examined next level node
                    Node<S, A> relaxedNode = frontierMap.get(targetState);

                    //another loop prevention, see above
                    if (!explored.contains(relaxedNode)) {

                        if (relaxedNode == null) relaxedNode = new Node<S, A>(targetState);

                        double cost = node.getCost() + prob.cost(node.state, action);
                        //if currently shortest path, add to frontier
                        if (relaxedNode.getCost() > cost) {
                            relaxedNode.setCost(cost);
                            relaxedNode.setParent(node);
                            relaxedNode.setAction(action);

                            final boolean add = frontier.add(relaxedNode);
//                            System.out.println("Added? "+add);
                            frontierMap.put(targetState, relaxedNode);
                        }
                    }
                }

                explored.add(node);
            }
        }

        return null;


// Pseudocode example:

// function UNIFORM-COST-SEARCH(problem) returns a solution, or failure
// node ← a node with STATE = problem.INITIAL-STATE, PATH-COST = 0
// frontier ← a priority queue ordered by PATH-COST, with node as the only element
// explored ← an empty set
// loop do
//   if EMPTY?(frontier) then return failure
//   node ← POP(frontier) /* chooses the lowest-cost node in frontier */
//   if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)
//   add node.STATE to explored
//   for each action in problem.ACTIONS(node.STATE) do
//      child ← CHILD-NODE(problem,node,action)
//      if child.STATE is not in explored or frontier then
//         frontier ← INSERT(child,frontier)
//      else if child.STATE is in frontier with higher PATH-COST then
//         replace that frontier node with child


//        return null;
    }

}
