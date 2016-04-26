package gui;

import gamemodel.Option;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Deniz Alkislar on 21.4.2016.
 */
public class EditScreenOptions extends JPanel
{
    ScreenEditController parent;
    boolean isDelete = false;

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
        // TODO should change the properties of the screen view
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

    private class OptionsList extends JPanel
    {
        public OptionsList(ArrayList<Option> options)
        {
            Object[] columnNames = {"Name", "Screen"};
            ArrayList<String[]> data = new ArrayList<String[]>();

            for (Option op : options)
                data.add(new String[] {op.getName(), op.getScreen().getName()});

            setLayout(new BorderLayout());

            JTable table = new JTable((Object[][]) data.toArray(), columnNames);
            JComboBox comboBox = new JComboBox<Object>(parent.screen.getParent().getScreens().toArray());

            table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));

            add(table);
        }
    }
}