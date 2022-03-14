package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel implements ActionListener {

    public OptionPanel() {
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 600, 100);

        JButton saveButton = new JButton("Save");
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        this.add(saveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
