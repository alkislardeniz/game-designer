package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Deniz Alkislar on 21.4.2016.
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

        this.parent = parent;

        group = new ButtonGroup();
        setLayout(new GridLayout(ObjectIconView.values().length, 1));

        //Add buttons to group and panel

        for (ObjectIconView icon : ObjectIconView.values())
        {
            if (icon.movable) {
                iconButton = new JRadioButton(icon.toString());
                iconButton.setName(icon.toString());
                iconButton.addActionListener(new IconListener(icon.icon));
                group.add(iconButton);
                add(iconButton);
            }
        }
        addButton = new JRadioButton("Button");
        addTextBox = new JRadioButton("Text Box");
        addLabel = new JRadioButton("Label");

        addButton.addActionListener(new ButtonListener());
        addTextBox.addActionListener(new TextBoxListener());
        addLabel.addActionListener(new LabelListener());

        group.add(addButton);
        add(addButton);
        group.add(addTextBox);
        add(addTextBox);
        group.add(addLabel);
        add(addLabel);

        add(new OptionsList(parent.screen.getOptions()));

        //Add scroll bar to this panel
        scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 504));
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

            parent.repaint();
        }
    }

    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel panel = new JPanel();

            JComboBox comboBox = new JComboBox<Object>(parent.screen.getOptions().toArray());
            JTextField textField = new JTextField();

            panel.add(new JLabel("Name: "));
            panel.add(textField);
            panel.add(new JLabel("Options: "));
            panel.add(comboBox);

            if (JOptionPane.showConfirmDialog(null, panel, "Create button", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                ScreenButton button = new ScreenButton(parent.screen, textField.getText());
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

            JComboBox comboBox = new JComboBox<Object>(parent.screen.getParent().getVariables().toArray());
            JTextField textField = new JTextField();

            panel.add(new JLabel("Name: "));
            panel.add(textField);
            panel.add(new JLabel("Variables: "));
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

            panel.add(new JLabel("Name: "));
            panel.add(textField);
            panel.add(new JLabel("Text: "));
            panel.add(textField2);

            if (JOptionPane.showConfirmDialog(null, panel, "Create label", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION)
            {
                ScreenLabel label = new ScreenLabel(parent.screen, textField.getText(), textField2.getText());
                // TODO check if text of label is valid first
                parent.setSelectedComponent(label);
            }
        }
    }

    private class OptionsList extends JPanel
    {
        public OptionsList(ArrayList<Option> options)
        {
            Object[] columnNames = {"Name", "Screen"};
            ArrayList<String[]> data = new ArrayList<>();

            for (Option op : options)
                data.add(new String[] {op.getName(), op.getScreen().getName()});

            setLayout(new BorderLayout());

            JTable table = new JTable(data.toArray(new String[][] {}), columnNames);

            JComboBox comboBox = new JComboBox<>(parent.screen.getParent().getScreens().toArray());
            table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));

            add(table);
        }
    }

}
