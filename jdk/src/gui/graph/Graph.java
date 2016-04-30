package gui.graph;

import java.util.List;

/**
 * Collection of items that binds each item to a list of predecessors and successors.
 * Created by admin on 4/30/16.
 */
public interface Graph<E>
{
    public boolean contains(E e);

    public List<E> getPredecessors(E e);

    public List<E> getSuccessors(E e);

    public GraphEdge<E> getEdge(E pred, E succ);
}
