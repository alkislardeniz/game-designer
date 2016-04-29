package gui.graph;

import java.util.LinkedList;

/**
 * Represents a graph graphically.
 * Created by admin on 4/30/16.
 */
public class GraphView<E>
{
    Graph<E> graph;
    LinkedList<Integer> queue; // store queue of x-coordinate offsets

    public GraphView(Graph<E> graph)
    {
        this.graph = graph;
        queue = new LinkedList<>();
        queue.add(0);
    }

    // TODO add and remove E, draw each GraphEdge<E> between two Es based on first available slot in queue
    // Perhaps also relegate elements to the sides based on position of predecessor
    // Infer position of successor based on positions of other elements
}
