// Paths for extensibility:
// - More components
// - More types of screens, restricted to different groups of components
// - More customization in objects

// store an editable game as a serialized Game instance,
// a playable game as a serialized GameState instance
// or perhaps JSON

// represent a screen as it is edited
public class ScreenEditView extends JPanel
{
    // selected object, hover over mouse as
    ScreenComponent selected;
}

public class ScreenPlayView extends JPanel
{
    // action listener an inner class of the controller
}

public class ObjectPlayView extends JPanel
{
    
}

// ...

/***** MODEL *****/

// class executing a game
public class GamePlayer implements Serializable
{
    Game game;
    Screen currentScreen;
    Map<String,Expr> varBinds;
    
    // for serialization, instantiate everything as null
    public GamePlayer();
    
    // only method called from outside
    public GamePlayer(Game game);
    
    // called from the constructor and individual screens
    // calls the current screen in the state
    // returns false if currentScreen null, i.e. there are no screens left to execute
    public boolean call();
    
    public Screen getCurrentScreen();
    public void setCurrentScreen(Screen new);
    
    public Expr getVariable(String name);
    public void addVariable(String name, String valueString);
}

// data structure containing information about one game
public class Game implements Serializable
{
    // possibly make the lists maps, so as to memoize screen names and speed up program
    List<Screen> screens; // search through screens by their names
    List<ScreenObject> sharedObjects; // searched by names, represent objects shared across screens
    List<String> variables;
    Screen startScreen;
    
    // initialize empty collections, null startScreen
    public Game();

    // getters, setters
    // for lists, get() and remove() methods call objects by their names
    // return null if no objects of that name
    
    // editor interface
    
    // create new screen, playable, asignment or cond
    public boolean newPlayableScreen(String name);
    public boolean newAssignmentScreen(String name);
    public boolean newCondScreen(String name);
    
    // return ScreenEditor for a playable screen
    // called by controller in moving to a new panel to edit a screen
    public ScreenEditor getEditor(String name);
}

// class codifying a screen, visible or not
public abstract class Screen implements Serializable
{
    // name already defined inside game
    String name;
    String description;
    Game parent; // why?
    List<Screen> options; // searched by name
    int optionLimit = -1; // affects add, changed for AssignScreen and CondScreen
    final boolean playable; // true for PlayableScreen, used to determine type w/o calling instanceof
    
    // for serialization, set everything to null
    public Screen();
    
    // set other properties to default values, to be changed by edit description screen
    public Screen(Game parent, String name);
    
    // called from player, either responds directly or waits for input from GUI interface
    public abstract void fromPlayer(GamePlayer player);
    
    // calls player after updating its state, sets new screen of player to option,
    // then calls the player's call() method
    // can be called either from fromPlayer or from GUI action listeners
    public void toPlayer(GamePlayer player, String option);
    
    // getOptions() to be called from editor
    protected List<Screen> getOptions();
    public boolean addOption(String name, Screen screen);
    public Screen getOption(String name);
    public boolean removeOption(String name);
    
    // getter, setter for playable etc.
}

public class PlayableScreen extends Screen
{
    List<ScreenComponent> components; // container?
    ScreenComponent movableComponent;
    
    public PlayableScreen();
    
    // empty components other than shared objects
    // playable set to true
    public PlayableScreen(Game parent, String name);
    
    public ScreenComponent getComponent(String name);
    public boolean addComponent(String name, ScreenComponent comp);
    public ScreenComponent removeComponent(String name);
    
    // return true if all components are compatible with comp
    public boolean canAddComponent(ScreenComponent comp);
    
    // call components in leaving screen
    @Override
    public void toPlayer(GamePlayer player, String option);
}

// encapsulates methods to edit a screen
public class ScreenEditor
{
    PlayableScreen screen;
    
    public ScreenEditor(PlayableScreen screen);
    
    // editor methods
    
    // place component on screen, adjusting its position
    // called by GUI editor
    // calls canAddComponent()
    public boolean placeComponent(String name, Point point);
    
    // find component with name/location and return their editor
    public ComponentEditor findComponent(String name);
    public ComponentEditor findComponent(Point point);
    
    // change location of component
    // calls canAddComponent()
    public boolean moveComponent(String name, Point newPoint);
}

// make sure this is bound only to one screen
public class AssignScreen extends Screen
{
    String variable;
    Expr newValue;
    
    // initialize only one option, set optionLimit to 1
    public AssignScreen(Game parent, String name);
    
    // getters, setters
    // parse newValue automatically within the setter, which takes a String
    
    // calculates newValue in the context of player, sets value of variable in the
    // context of player, then calls toPlayer() with the default option
    public void fromPlayer(GamePlayer player);
}

// and this only to two
public class CondScreen extends Screen
{
    Expr pred;
    
    // initialize two options, set optionLimit to 2
    // one representing true, the other false
    public CondScreen(Game parent, String name);
    
    // getters, setters
    // parse pred automatically within the setter, which takes a String
    
    // evaluates pred in the current state of the game, then calls toPlayer() based on
    // the result of the evaluation
    public void fromPlayer(GamePlayer player);
    
    // whether pred is a valid predicate
    // possibly throw error or alert parent otherwise
    public boolean validExpr();
}

public abstract class ScreenComponent implements Serializable
{
    String name;
    Point  position;
    final Screen parent;
    
    // null everything
    public ScreenComponent();
    
    // point initially null, set by ScreenEditor
    public ScreenComponent(Screen par, String nam);
    
    // in subclasses, GUI components might be inner classes
    // so that the parent screen's method can be called
    
    // returns whether other's position is compatible with this
    // can be overridden, but by default returns false if the positions are equal
    public boolean isCompatible(ScreenComponent other);
    
    // what to do in leaving a screen, nothing by default
    public void leavingScreen(GamePlayer player);
}

// includes methods to change properties of a given component
// add subclasses for each type of component, tailored for their properties
public abstract class ComponentEditor
{
    ScreenComponent comp;
    
    public ComponentEditor(ScreenComponent comp);
    
    // get/set name, position
}

public class ScreenButton extends ScreenComponent
{
    String option;
    String text;
    boolean visible; // if invisible, an object will call it from its keyboard listener
    
    // possibly rearrange arguments
    public ScreenButton(Screen par, String nam);
    
    // getters, setters
    
    // call parent screen if clicked
    // called by game's action listeners
    public void clicked();

    // return true if not visible, call super.isCompatible() otherwise
    @Override
    public boolean isCompatible(ScreenComponent other);
}

public class ScreenTextBox extends ScreenComponent
{
    String variable;
    String text;
    
    // perhaps with less options and setters
    public ScreenTextBox(Screen par, String nam);
    
    // getters, setters go here
    
    // update value of variable to evaluation of text
    @Override
    public void leavingScreen(GamePlayer player);
}

public class ScreenObject extends ScreenComponent
{
    boolean movable; // false by default, one object in screen can be moved
    String  imageName;
    // ...
    
    public ScreenObject(Screen par, String nam, String img);
    
    // getters, setters
    
    // perhaps create new ObjectPlayer class to manage object movement in playing game
}

// parse string expression into tree, evaluate its value
public class Expr implements Serializable
{
    Tree<String> parseTree;
    
    // null tree
    public Expr();
    
    public Expr(String exprString);
    
    // return the result of evaluating the parse tree in the current environment
    // problem: type checking
    public Object eval(GamePlayer env);
}
