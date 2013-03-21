package gui;

import event.Event;
import util.FileIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckpointPanel extends JPanel {

	private JComboBox<String> entrantBox;
	private JComboBox<String> nodeBox;

	private JTextField hrsField;
	private JTextField minsField;
	private JCheckBox currTimeBox;

	private JButton arriveButton;
	private JButton departButton;
	private JButton submitButton;
	private JButton excludeButton;

	public CheckpointPanel(Event event, String logFile) {
		// make components
		ActionListener listener = new Listener(event, logFile);

		// north panel
		JPanel northPanel = new JPanel();

		String[] entrants = new String[event.getEntrants().size()];
		for (int i = 0; i < event.getEntrants().size(); i++) {
			entrants[i] = event.getEntrants().get(i).getName();
		}
		entrantBox = new JComboBox<String>(entrants);
		northPanel.add(entrantBox);

		String[] nodes = new String[event.getNodes().size()];
		for (int i = 0; i < event.getNodes().size(); i++) {
			nodes[i] = Integer.toString(event.getNodes().get(i).getId());
		}
		nodeBox = new JComboBox<String>(nodes);
		northPanel.add(nodeBox);

		add(northPanel, BorderLayout.NORTH);

		// centre panel
		JPanel centrePanel = new JPanel();

		hrsField = new JTextField();
		hrsField.setColumns(2);
		centrePanel.add(hrsField);

		minsField = new JTextField();
		minsField.setColumns(2);
		centrePanel.add(minsField);

		currTimeBox = new JCheckBox();
		currTimeBox.addActionListener(listener);
		centrePanel.add(currTimeBox);

		JLabel currTimeLabel = new JLabel("Use current time");
		centrePanel.add(currTimeLabel);

		add(centrePanel, BorderLayout.CENTER);

		// south panel
		JPanel southPanel = new JPanel();

		arriveButton = new JButton("Arrive");
		arriveButton.addActionListener(listener);
		southPanel.add(arriveButton);

		departButton = new JButton("Depart");
		departButton.addActionListener(listener);
		southPanel.add(departButton);

		submitButton = new JButton("Submit");
		submitButton.addActionListener(listener);
		southPanel.add(submitButton);

		excludeButton = new JButton("Exclude");
		excludeButton.addActionListener(listener);
		southPanel.add(excludeButton);

		add(southPanel, BorderLayout.NORTH);
	}

	private class Listener implements ActionListener {

		private Event event;
		private String logFile;

		public Listener(Event event, String logFile) {
			this.event = event;
			this.logFile = logFile;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource() == currTimeBox) {
				// TODO	
			} else if (evt.getSource() == arriveButton) {
				// TODO
				FileIO.appendToFile(logFile, "CM: A type event recorded");
			} else if (evt.getSource() == departButton) {
				// TODO
				FileIO.appendToFile(logFile, "CM: D type event recorded");
		  } else if (evt.getSource() == submitButton) {
				// TODO
				FileIO.appendToFile(logFile, "CM: T type event recorded");
				FileIO.appendToFile(logFile, "CM: I type event recorded");
			} else if (evt.getSource() == excludeButton) {
				// TODO
				FileIO.appendToFile(logFile, "CM: E type event recorded");
			}
		}

	}

}
