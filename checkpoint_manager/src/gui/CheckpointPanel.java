package gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import event.Event;

public class CheckpointPanel extends JPanel {
  
  public CheckpointPanel(Event event) {
    JLabel label = new JLabel("foobar");
    this.add(label, BorderLayout.CENTER);
    label.setVisible(true);
  }

}
