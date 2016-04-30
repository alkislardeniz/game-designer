package gui.graph;


/**
 * Represents a graph graphically.
 * Created by admin on 4/30/16.
 */
public class GraphView<E>
{
    Graph<E> graph;
    Grid<E> grid;

    public GraphView(Graph<E> graph)
    {
        this.graph = graph;

    }

    // TODO add and remove E, draw each GraphEdge<E> between two Es based on first available slot in queue
    // Perhaps also relegate elements to the sides based on position of predecessor
    // Infer position of successor based on positions of other elements

    // Better yet, use neighborhoods that fill topmost available space, going left if right is full,
    // right if left is full, and also count lines as filling a neighborhood

    // Algorithm:
    // - Add each screen in sequence, starting from the start screen at the top
    // - For each screen, add its next screens in sequence
    // - If the next screen isn't added already, add it to first available neighbor with most neighbors and draw edge
    // - If it is added already, draw edge from it to next screen
}
