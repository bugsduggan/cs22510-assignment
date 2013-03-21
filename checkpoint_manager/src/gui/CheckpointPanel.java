package gui;

import event.Entrant;
import event.Event;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

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

	private JButton submitButton;
	private JButton excludeButton;

	public CheckpointPanel(Event event) {
		// make components
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
		centrePanel.add(currTimeBox);

		JLabel currTimeLabel = new JLabel("Use current time");
		centrePanel.add(currTimeLabel);

		add(centrePanel, BorderLayout.CENTER);

		// south panel
		JPanel southPanel = new JPanel();

		submitButton = new JButton("Submit");
		southPanel.add(submitButton);

		excludeButton = new JButton("Exclude");
		southPanel.add(excludeButton);

		add(southPanel, BorderLayout.NORTH);
	}

}
