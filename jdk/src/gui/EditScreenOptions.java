package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by Deniz Alkislar on 21.4.2016.
 */
public class EditScreenOptions extends JPanel
{
    ScreenEditController parent;
    boolean isDelete  = false;

    public EditScreenOptions(ScreenEditController parentController)
    {
        ButtonGroup group;
        JRadioButton showgrid, delete, add;

        this.parent = parentController;

        delete = new JRadioButton("Delete");
        add = new JRadioButton("Add");
        showgrid = new JRadioButton("Show Grids");
        group = new ButtonGroup();

        // add action listeners
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isDelete = true;
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isDelete = false;
            }
        });
        showgrid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                parent.updateShowGrid(true); // TODO should also be able to make it false
            }
        });

        // add grouped buttons
        group.add(add);
        group.add(delete);
        setLayout(new GridLayout(1,3));

        // add buttons to panel
        add(showgrid);
        add(add);
        add(delete);
    }

    //getters and setters
    public boolean shouldDelete()
    {
        return isDelete;
    }
}