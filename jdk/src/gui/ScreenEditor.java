package gui;

import javax.swing.*;
import gamemodel.*;
import java.awt.event.*;

/**
 * Panel containing ScreenView that allows to edit an individual screen.
 * Created by admin on 4/12/16.
 */
public class ScreenEditor extends JPanel
{
    Game game;
    PlayableScreen screen;
    ScreenView screenView;

    // perhaps make these constants for Screen
    // TODO Or just store grid size and positions in the model, and scale them up in the view
    private final int WIDTH = 504, HEIGHT = 504;
    private final int IMAGE_HEIGHT = 24;
    private final int IMAGE_WIDTH = IMAGE_HEIGHT;
    private final int JUMP = IMAGE_HEIGHT;  // increment for image movement

    public ScreenEditor(PlayableScreen screen)
    {
        this.screen = screen;
        game = screen.getParent();

        screenView = new ScreenView(screen);
        add(screenView);

        // have screenView at the center of the panel,
        // and a pane of components to add to the left
    }

    // perhaps put these in a component controller
    private class DirectionListener implements KeyListener
    {
        public void keyPressed (KeyEvent event)
        {
            setFocusable(true);

            screenView.movable.move(event.getKeyCode(), JUMP);

            // replicate these in move() and setImage()
//            int key = event.getKeyCode();
//            boolean check = true;
//
//            if (key == KeyEvent.VK_UP)
//            {
//                currentImage = up;
//                if (y > 0)
//                {
//                    // checking compatibility before moving
//                    for (ComponentView comp : comps)
//                    {
//                        if ( comp.getX() == x && comp.getY() == y - JUMP )
//                        {
//                            check = false;
//                            break;
//                        }
//                    }
//                    if (check)
//                    {
//                        y -= JUMP;
//                    }
//                    else
//                    {
//                        currentImage = rightStand;
//                    }
//                }
//                else
//                {
//                    currentImage = rightStand;
//                }
//            }
//            else if (key == KeyEvent.VK_DOWN)
//            {
//                currentImage = down;
//                if (y < HEIGHT-IMAGE_HEIGHT)
//                {
//                    for (int i = 0; i < points.size(); i++)
//                    {
//                        if ( ((int) points.get(i).getX() == x) && ((int) points.get(i).getY() == y + JUMP) )
//                        {
//                            check = false;
//                            break;
//                        }
//                    }
//                    if (check)
//                    {
//                        y += JUMP;
//                    }
//                    else
//                    {
//                        currentImage = rightStand;
//                    }
//                }
//                else
//                {
//                    currentImage = rightStand;
//                }
//            }
//            else if (key == KeyEvent.VK_LEFT)
//            {
//                currentImage = left;
//                if (x > 0)
//                {
//                    for (int i = 0; i < points.size(); i++)
//                    {
//                        if ( ((int) points.get(i).getX() == x - JUMP) && ((int) points.get(i).getY() == y) )
//                        {
//                            check = false;
//                            break;
//                        }
//                    }
//                    if (check)
//                    {
//                        x -= JUMP;
//                    }
//                    else
//                    {
//                        currentImage = leftStand;
//                    }
//                }
//                else
//                {
//                    currentImage = leftStand;
//                }
//            }
//            else if ( key == KeyEvent.VK_RIGHT)
//            {
//                currentImage = right;
//                if (x < WIDTH-IMAGE_WIDTH)
//                {
//                    for (int i = 0; i < points.size(); i++)
//                    {
//                        if ( ((int) points.get(i).getX() == x + JUMP) && ((int) points.get(i).getY() == y) )
//                        {
//                            check = false;
//                            break;
//                        }
//                    }
//                    if (check)
//                    {
//                        x += JUMP;
//                    }
//                    else
//                    {
//                        currentImage = rightStand;
//                    }
//                }
//                else
//                {
//                    currentImage = rightStand;
//                }
//            }
            repaint();
        }

        public void keyReleased(KeyEvent event)
        {

//            if (currentImage == right)
//            {
//                currentImage = rightStand;
//            }
//
//            else if (currentImage == left)
//            {
//                currentImage = leftStand;
//            }
//
//            else if (currentImage == up)
//            {
//                currentImage = rightStand;
//            }
//
//            else if (currentImage == down)
//            {
//                currentImage = rightStand;
//            }
            repaint();
        }

        public void keyTyped (KeyEvent event) {}
    }
}
