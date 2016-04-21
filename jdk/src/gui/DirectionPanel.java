package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @Deniz Alkýþlar
 * @Program Name 1.
 * @Ankara - 00.00.2016
 */

public class DirectionPanel extends JPanel
{
    private final int WIDTH = 504, HEIGHT = 504;
    private final int IMAGE_HEIGHT = 24;
    private final int IMAGE_WIDTH = IMAGE_HEIGHT;
    private final int JUMP = IMAGE_HEIGHT;  // increment for image movement

    private ImageIcon up, down, right, left, leftStand, rightStand, currentImage, bg, rock, house;
    private int x, y;
    private ArrayList<Point> points;
    private JButton button;
    private JRadioButton button2;
    private boolean showGrid, deleteObject;
    private JPanel buttonHolder;

    public DirectionPanel ()
    {
        addKeyListener (new DirectionListener());
        addMouseListener (new MyMouseAdapter());

        x = 0;
        y = 0;

        buttonHolder = new JPanel();

        points = new ArrayList<Point>();

        button = new JButton ("Show grids");
        button.addActionListener (new ButtonListener());

        button2 = new JRadioButton ("Delete Object");
        button2.setSelected (false);
        button2.addActionListener (new DeleteListener());

        deleteObject = false;
        showGrid = true;

        up = new ImageIcon ("pics/manRight.gif");
        down = new ImageIcon ("pics/manRight.gif");
        left = new ImageIcon ("pics/manLeft.gif");
        right = new ImageIcon ("pics/manRight.gif");
        rightStand = new ImageIcon ("pics/manRightStand.gif");
        leftStand = new ImageIcon ("pics/manLeftStand.gif");
        bg = new ImageIcon ("pics/bg.png");
        rock = new ImageIcon ("pics/rock.png");
        house = new ImageIcon ("pics/house.png");
        currentImage = rightStand;

        setPreferredSize (new Dimension (WIDTH, HEIGHT));
        setLayout (new BorderLayout());
        buttonHolder.add (button2);
        buttonHolder.add (button);
        add(buttonHolder, BorderLayout.SOUTH);
        setFocusable (true);
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent (g);

        g.drawImage(bg.getImage(), 0, 0, null);
        currentImage.paintIcon (this, g, x, y);

        for (int i = 0; i < points.size(); i++)
        {
            rock.paintIcon (this, g, (int) points.get(i).getX(), (int) points.get(i).getY());
        }

        if (showGrid)
        {
            int xP = 0;
            int yP = 0;

            // draws horizontal lines
            for (int i = 0; i <= 21; i++)
            {
                g.drawLine (xP, yP, xP + (IMAGE_HEIGHT * 21), yP);
                yP += IMAGE_HEIGHT;
            }
            yP = 0;

            // draws vertical lines
            for (int i = 0; i <= 21; i++)
            {
                g.drawLine (xP, yP, xP, yP + (IMAGE_HEIGHT * 21));
                xP += IMAGE_HEIGHT;
            }
        }
    }

    private class DirectionListener implements KeyListener
    {
        public void keyPressed (KeyEvent event)
        {
            setFocusable(true);
            int key = event.getKeyCode();
            boolean check = true;

            if (key == KeyEvent.VK_UP)
            {
                currentImage = up;
                if (y > 0)
                {
                    for (int i = 0; i < points.size(); i++)
                    {
                        if ( ((int) points.get(i).getX() == x) && ((int) points.get(i).getY() == y - JUMP) )
                        {
                            check = false;
                            break;
                        }
                    }
                    if (check)
                    {
                        y -= JUMP;
                    }
                    else
                    {
                        currentImage = rightStand;
                    }
                }
                else
                {
                    currentImage = rightStand;
                }
            }
            else if (key == KeyEvent.VK_DOWN)
            {
                currentImage = down;
                if (y < HEIGHT-IMAGE_HEIGHT)
                {
                    for (int i = 0; i < points.size(); i++)
                    {
                        if ( ((int) points.get(i).getX() == x) && ((int) points.get(i).getY() == y + JUMP) )
                        {
                            check = false;
                            break;
                        }
                    }
                    if (check)
                    {
                        y += JUMP;
                    }
                    else
                    {
                        currentImage = rightStand;
                    }
                }
                else
                {
                    currentImage = rightStand;
                }
            }
            else if (key == KeyEvent.VK_LEFT)
            {
                currentImage = left;
                if (x > 0)
                {
                    for (int i = 0; i < points.size(); i++)
                    {
                        if ( ((int) points.get(i).getX() == x - JUMP) && ((int) points.get(i).getY() == y) )
                        {
                            check = false;
                            break;
                        }
                    }
                    if (check)
                    {
                        x -= JUMP;
                    }
                    else
                    {
                        currentImage = leftStand;
                    }
                }
                else
                {
                    currentImage = leftStand;
                }
            }
            else if ( key == KeyEvent.VK_RIGHT)
            {
                currentImage = right;
                if (x < WIDTH-IMAGE_WIDTH)
                {
                    for (int i = 0; i < points.size(); i++)
                    {
                        if ( ((int) points.get(i).getX() == x + JUMP) && ((int) points.get(i).getY() == y) )
                        {
                            check = false;
                            break;
                        }
                    }
                    if (check)
                    {
                        x += JUMP;
                    }
                    else
                    {
                        currentImage = rightStand;
                    }
                }
                else
                {
                    currentImage = rightStand;
                }
            }
            repaint();
        }

        public void keyReleased(KeyEvent event)
        {
            if (currentImage == right)
            {
                currentImage = rightStand;
            }

            else if (currentImage == left)
            {
                currentImage = leftStand;
            }

            else if (currentImage == up)
            {
                currentImage = rightStand;
            }

            else if (currentImage == down)
            {
                currentImage = rightStand;
            }
            repaint();
        }

        public void keyTyped (KeyEvent event) {}
    }

    class MyMouseAdapter extends MouseAdapter
    {
        public void mousePressed (MouseEvent e)
        {
            int rX = 0;
            int rY = 0;
            Point p = e.getPoint();
            rX = ((int) p.getX()) - ((int) p.getX() % IMAGE_HEIGHT);
            rY = ((int) p.getY()) - ((int) p.getY() % IMAGE_HEIGHT);

            if (deleteObject == false)
            {
                points.add (new Point (rX, rY));
            }
            else
            {
                for (int i = 0; i < points.size(); i++)
                {
                    if ((int) points.get(i).getX() == rX && (int) points.get(i).getY() == rY)
                    {
                        points.remove(i);
                        break;
                    }
                }
            }
            repaint();
        }
    }

    class ButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            setFocusable(true);
            if (showGrid == true)
            {
                showGrid = false;
            }
            else
            {
                showGrid = true;
            }
            button.setFocusable (false);
        }
    }

    class DeleteListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            setFocusable(true);
            if (deleteObject == true)
            {
                deleteObject = false;
            }
            else
            {
                deleteObject = true;
            }
            button2.setFocusable (false);
        }
    }
}
