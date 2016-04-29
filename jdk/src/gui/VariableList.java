package gui;

import gamemodel.*;
import expr.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by admin on 4/28/16.
 */
public class VariableList extends JPanel
{
    AbstractTableModel model;
    JTable table;
    JTextField nameField;
    JTextField valueField;
    Game game;

    public VariableList(Game game)
    {
        this.game = game;

        setLayout(new BorderLayout());

        add(new JLabel("Variables"), BorderLayout.NORTH);

        model = new MyTableModel();
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(150, 350));

        add(scrollPane);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.setPreferredSize(new Dimension(150, 50));

        nameField  = new JTextField();
        valueField = new JTextField();

        panel.add(new JLabel("Variable: "));
        panel.add(nameField);
        panel.add(new JLabel("Value: "));
        panel.add(valueField);

        JButton button = new JButton("Add");
        button.addActionListener(new AddListener());
        panel.add(button);

        button = new JButton("Delete");
        button.addActionListener(new DeleteListener());
        panel.add(button);

        add(panel, BorderLayout.SOUTH);
    }

    class AddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // add option to screen and update combo box
            String varName = nameField.getText();

            if (!game.hasBinding(varName))
                game.addVariable(varName);

            game.setVariable(varName, valueField.getText());

            repaint();
        }
    }

    // delete selected screen
    class DeleteListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            game.removeVariable((String) model.getValueAt(table.getSelectedRow(), 0));

            repaint();
        }
    }

    class MyTableModel extends AbstractTableModel
    {
        @Override
        public int getRowCount()
        {
            return game.getVariables().size();
        }

        @Override
        public int getColumnCount()
        {
            return 2;
        }

        @Override
        public String getColumnName(int index)
        {
            final String[] columns = {"Variable", "Value"};

            if (index >= 2)
                return "";

            return columns[index];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            Binding bind = game.getVariables().get(rowIndex);

            if (bind == null)
                return null;

            if (columnIndex == 0)
                return bind.getVar();

            if (columnIndex == 1)
                return bind.getValue();

            return null;
        }
    }
}
