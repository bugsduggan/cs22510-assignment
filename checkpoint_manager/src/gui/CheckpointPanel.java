package gui;

import event.Event;

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

	private JButton submitButton;
	private JButton excludeButton;

	public CheckpointPanel(Event event) {
		// make components
		// north panel
		JPanel northPanel = new JPanel();

		entrantBox = new JComboBox<String>();
		northPanel.add(entrantBox);

		nodeBox = new JComboBox<String>();
		northPanel.add(nodeBox);

		add(northPanel, BorderLayout.NORTH);

		// centre panel
		JPanel centrePanel = new JPanel();

		hrsField = new JTextField();
		centrePanel.add(hrsField);

		minsField = new JTextField();
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
