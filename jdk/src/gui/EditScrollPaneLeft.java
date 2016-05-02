package gui;

import gamemodel.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Scrollable pane containing movable characters and other components to add to a screen.
 * @author  Deniz Alkislar
 * @author  Ata Deniz Aydin
 * @version 21/04/16
 */
public class EditScrollPaneLeft extends JPanel
{
    ScreenEditController parent;
    String selectedComponent;

    public EditScrollPaneLeft(ScreenEditController parent)
    {
        ButtonGroup group;
        JScrollPane scrollPane;
        JRadioButton iconButton;
        JRadioButton addButton;
        JRadioButton addTextBox;
        JRadioButton addLabel;
        JPanel scrollPanel;

        this.parent = parent;

        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(150, 504));

        //Add buttons to group and panel

        group = new ButtonGroup();
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

        // add icon buttons
        for (ObjectIconView icon : ObjectIconView.values())
        {
            if (icon.movable)
            {
                iconButton = new JRadioButton(icon.toString(), icon.defaultImage);
                iconButton.setName(icon.toString());
                iconButton.addActionListener(new IconListener(icon.icon));
                group.add(iconButton);
                scrollPanel.add(iconButton);
            }
        }

        // add buttons for other objects
        addButton = new JRadioButton("Button");
        addTextBox = new JRadioButton("Text Box");
        addLabel = new JRadioButton("Label");

        addButton.addActionListener(new ButtonListener());
        addTextBox.addActionListener(new TextBoxListener());
        addLabel.addActionListener(new LabelListener());

        group.add(addButton);
        group.add(addTextBox);
        group.add(addLabel);
        scrollPanel.add(addButton);
        scrollPanel.add(addTextBox);
        scrollPanel.add(addLabel);

        // create scroll pane for the panel of buttons
        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(150, 250));

        add(scrollPane);

        add(new OptionsList(parent.screen.getOptions()));

    }

    public String getSelectedComponent()
    {
        return selectedComponent;
    }

    class IconListener implements ActionListener
    {
        ObjectIcon icon;

        public IconListener(ObjectIcon icon)
        {
            this.icon = icon;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            parent.screenView.setMovable(icon);
            parent.comp = null;

            parent.repaint();
        }
    }

    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel panel = new JPanel();

            JComboBox comboBox = new JComboBox<>(parent.screen.getOptions().toArray());
            JTextField textField = new JTextField();
            JCheckBox visible = new JCheckBox();

            panel.setLayout(new GridLayout(3, 2));

            panel.add(new JLabel("Name: "));
            panel.add(textField);
            panel.add(new JLabel("Options: "));
            panel.add(comboBox);
            panel.add(new JLabel("Visible: "));
            panel.add(visible);

            if (JOptionPane.showConfirmDialog(null, panel, "Create button", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                ScreenButton button = new ScreenButton(parent.screen, textField.getText());
                button.setVisible(visible.isSelected());
                button.setOption(comboBox.getSelectedItem().toString());
                parent.setSelectedComponent(button);
            }
        }
    }

    class TextBoxListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel panel = new JPanel();

            JComboBox comboBox = new JComboBox<>(parent.screen.getParent().getVariables().toArray());
            JTextField textField = new JTextField();

            panel.setLayout(new GridLayout(2, 2));

            panel.add(new JLabel("Name: "));
            panel.add(textField);
            panel.add(new JLabel("Variable: "));
            panel.add(comboBox);

            if (JOptionPane.showConfirmDialog(null, panel, "Create text box", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                ScreenTextBox textBox = new ScreenTextBox(parent.screen, textField.getText());
                textBox.setVariable(comboBox.getSelectedItem().toString());
                parent.setSelectedComponent(textBox);
            }
        }
    }

    class LabelListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel panel = new JPanel();

            JTextField textField = new JTextField();
            JTextField textField2 = new JTextField();

            panel.setLayout(new GridLayout(2, 2));

            panel.add(new JLabel("Name: "));
            panel.add(textField);
            panel.add(new JLabel("Text: "));
            panel.add(textField2);

            if (JOptionPane.showConfirmDialog(null, panel, "Create label", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                ScreenLabel label = new ScreenLabel(parent.screen, textField.getText(), textField2.getText());
                if (label.valid())
                    parent.setSelectedComponent(label);
            }
        }
    }

    class OptionsList extends JPanel
    {
        AbstractTableModel model;
        JTable table;
        JTextField nameField;
        JComboBox<String> screenField;

        public OptionsList(ArrayList<Option> options)
        {
            setLayout(new BorderLayout());

            add(new JLabel("Options"), BorderLayout.NORTH);

            model = new MyTableModel();
            table = new JTable(model);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setPreferredSize(new Dimension(150, 200));

            add(scrollPane);

            JPanel panel = new JPanel(new GridLayout(3, 2));

            panel.setPreferredSize(new Dimension(150, 50));

            nameField = new JTextField();
            // System.out.println(parent.screen.getParent().getScreens());
            screenField = new JComboBox<>(new MyComboBoxModel());

            panel.add(new JLabel("Name: "));
            panel.add(nameField);
            panel.add(new JLabel("Screen: "));
            panel.add(screenField);

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
                if (parent.screen.addOption(nameField.getText(),
                                            parent.screen.getParent().getScreen((String) screenField.getSelectedItem())))
                {
                    model.fireTableStructureChanged();

                    nameField.setText("");
                    screenField.setSelectedIndex(0);
                }
            }
        }

        // delete selected screen
        class DeleteListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (table.getSelectedRow() >= 0)
                {
                    parent.screen.removeOption((String) model.getValueAt(table.getSelectedRow(), 0));

                    model.fireTableStructureChanged();
                }
            }
        }

        class MyTableModel extends AbstractTableModel
        {
            @Override
            public int getRowCount()
            {
                return parent.screen.getOptions().size();
            }

            @Override
            public int getColumnCount()
            {
                return 2;
            }

            @Override
            public String getColumnName(int index)
            {
                final String[] columns = {"Name", "Screen"};

                if (index >= 2)
                    return "";

                return columns[index];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                Option option = parent.screen.getOptions().get(rowIndex);

                if (option == null)
                    return null;

                if (columnIndex == 0)
                    return option.getName();

                if (columnIndex == 1)
                    return parent.screen.getParent().getScreenName(option.getScreen());

                return null;
            }
        }

        class MyComboBoxModel extends DefaultComboBoxModel<String>
        {
            public String getElementAt(int index)
            {
                try
                {
                    return parent.screen.getParent().getScreenNames().get(index);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    return null;
                }
            }

            public int getSize()
            {
                return parent.screen.getParent().getScreenNames().size();
            }

            public int getIndexOf(Object anObject)
            {
                return parent.screen.getParent().getScreenNames().indexOf(anObject);
            }
        }
    }

}
