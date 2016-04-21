package gamemodel;

import java.util.ArrayList;

/**
 * Observable
 * Listener of GameObservers.
 * @author  Ata Deniz Aydin
 * @version 21/04/16
 */
public abstract class Observable
{
    ArrayList<Observer> observers;

    public Observable()
    {
        observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer o)
    {
        observers.add(o);
    }

    public void removeObserver(Observer o)
    {
        observers.remove(o);
    }

    public void notifyObservers()
    {
        for (Observer o : observers)
            o.update();
    }
}
