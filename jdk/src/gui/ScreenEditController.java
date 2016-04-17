package gui;

import javax.swing.*;
import gamemodel.*;
import java.awt.event.*;

/**
 * Panel containing ScreenView that allows to edit an individual screen.
 * Created by admin on 4/12/16.
 */
public class ScreenEditController extends JPanel implements ScreenController
{
    Game game;
    PlayableScreen screen;
    ScreenView screenView;
    // TODO also include a panel containing options for components to add to the screen

    public ScreenEditController(PlayableScreen screen)
    {
        this.screen = screen;
        game = screen.getParent();

        screenView = new ScreenView(this, screen, true);
        add(screenView);

        // have screenView at the center of the panel,
        // and a pane of components to add to the left
    }

    public GamePlayer getPlayer()
    {
        return null;
    }

    // perhaps put these in a component controller
//    private class DirectionListener implements KeyListener
//    {
//        public void keyPressed (KeyEvent event)
//        {
//            setFocusable(true);
//
//            screenView.movable.move(event.getKeyCode(), JUMP);
//
//            // replicate these in move() and setImage()
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
//            repaint();
//        }
//
//        public void keyReleased(KeyEvent event)
//        {
//
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
//            repaint();
//        }
//
//        public void keyTyped (KeyEvent event) {}
//    }
}
