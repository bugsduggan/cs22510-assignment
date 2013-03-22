package gui;

import event.node.CheckpointNode;
import event.node.MedicalCheckpointNode;
import event.node.Node;
import event.Entrant;
import event.Event;
import event.update.*;
import util.FileIO;
import util.Time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Tom Leaman (thl5@aber.ac.uk)
 *
 */
public class CheckpointPanel extends JPanel {

	private static final long serialVersionUID = 7212846449217761749L;

	private Event event;

	private JComboBox<String> entrantBox;
	private JComboBox<String> nodeBox;

	private JTextField hrsField;
	private JTextField minsField;
	private JCheckBox currTimeBox;

	private JButton arriveButton;
	private JButton departButton;
	private JButton submitButton;
	private JButton excludeButton;

	public CheckpointPanel(Event event, String timesFile, String logFile) {
		this.event = event;
		// make components
		ActionListener listener = new Listener(timesFile, logFile);

		// north panel
		JPanel northPanel = new JPanel();

		String[] entrants = new String[event.getEntrants().size()];
		for (int i = 0; i < event.getEntrants().size(); i++) {
			entrants[i] = event.getEntrants().get(i).getName();
		}
		entrantBox = new JComboBox<String>(entrants);
		entrantBox.addActionListener(listener);
		northPanel.add(entrantBox);

		String[] nodes = new String[event.getNodes().size()];
		for (int i = 0; i < event.getNodes().size(); i++) {
			nodes[i] = Integer.toString(event.getNodes().get(i).getId());
		}
		nodeBox = new JComboBox<String>(nodes);
		nodeBox.addActionListener(listener);
		northPanel.add(nodeBox);

		add(northPanel, BorderLayout.NORTH);

		// centre panel
		JPanel centrePanel = new JPanel();

		hrsField = new JTextField();
		hrsField.setColumns(2);
		hrsField.setText("00");
		centrePanel.add(hrsField);

		minsField = new JTextField();
		minsField.setColumns(2);
		minsField.setText("00");
		centrePanel.add(minsField);

		currTimeBox = new JCheckBox("Use current time");
		currTimeBox.addActionListener(listener);
		centrePanel.add(currTimeBox);

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

		updateTime();
		updateButtons();
	}

	private Entrant getSelectedEntrant() {
		String selected = (String)entrantBox.getSelectedItem();
		for (Entrant e : event.getEntrants()) {
			if (e.getName().equals(selected)) return e;
		}
		return null;
	}

	private Node getSelectedNode() {
		String selected = (String)nodeBox.getSelectedItem();
		for (Node n : event.getNodes()) {
			if (Integer.toString(n.getId()).equals(selected)) return n;
		}
		return null;
	}

	private Time getSelectedTime() {
		int hours = Integer.parseInt(hrsField.getText());
		int minutes = Integer.parseInt(minsField.getText());
		return new Time(hours, minutes);
	}

	private boolean correctNode() {
		return getSelectedEntrant().getNextCheckpoint() == getSelectedNode();
	}

	private void updateButtons() {
		Node n = getSelectedNode();

		if (n instanceof CheckpointNode) {
			// enable submit, disable the rest
			submitButton.setEnabled(true);
			arriveButton.setEnabled(false);
			departButton.setEnabled(false);
			excludeButton.setEnabled(false);
		} else if (n instanceof MedicalCheckpointNode) {
			// disable submit, enable the rest
			submitButton.setEnabled(false);
			if (getSelectedEntrant().getStatus() == Entrant.STOPPED) {
				arriveButton.setEnabled(false);
				departButton.setEnabled(true);
				excludeButton.setEnabled(true);
			} else {
				arriveButton.setEnabled(true);
				departButton.setEnabled(false);
				excludeButton.setEnabled(false);
			}
		} else {
			// disable them all
			submitButton.setEnabled(false);
			arriveButton.setEnabled(false);
			departButton.setEnabled(false);
			excludeButton.setEnabled(false);
		}
	}

	private void updateTime() {
		Calendar cal = Calendar.getInstance();
		hrsField.setText(Integer.toString(
					cal.get(Calendar.HOUR_OF_DAY)));
		minsField.setText(Integer.toString(
					cal.get(Calendar.MINUTE)));
	}

	private class Listener implements ActionListener {

		private String timesFile;
		private String logFile;

		public Listener(String timesFile, String logFile) {
			this.timesFile = timesFile;
			this.logFile = logFile;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource() == entrantBox || evt.getSource() == nodeBox) {
				updateButtons();
			} else if (evt.getSource() == currTimeBox) {
				if (currTimeBox.isSelected()) {
					// set time boxes
					updateTime();
					// disable them
					hrsField.setEnabled(false);
					minsField.setEnabled(false);
				} else {
					// enable boxes
					hrsField.setEnabled(true);
					minsField.setEnabled(true);
				}
			} else if (evt.getSource() == arriveButton) {
				Update update = new ArrivalUpdate(
					getSelectedNode(), getSelectedEntrant(), getSelectedTime());
				update.execute();
				writeUpdate(update);
				FileIO.appendToFile(logFile, "CM: A type event recorded");
			} else if (evt.getSource() == departButton) {
				Update update = new DepartureUpdate(
					getSelectedNode(), getSelectedEntrant(), getSelectedTime());
				update.execute();
				writeUpdate(update);
				FileIO.appendToFile(logFile, "CM: D type event recorded");
		  } else if (evt.getSource() == submitButton) {
				if (correctNode()) {
					Update update = new TimeUpdate(
							getSelectedNode(), getSelectedEntrant(), getSelectedTime());
					update.execute();
					writeUpdate(update);
					FileIO.appendToFile(logFile, "CM: T type event recorded");
				} else {
					Update update = new InvalidUpdate(
							getSelectedNode(), getSelectedEntrant(), getSelectedTime());
					update.execute();
					writeUpdate(update);
					FileIO.appendToFile(logFile, "CM: I type event recorded");
				}
			} else if (evt.getSource() == excludeButton) {
				Update update = new ExcludedUpdate(
						getSelectedNode(), getSelectedEntrant(), getSelectedTime());
				update.execute();
				writeUpdate(update);
				FileIO.appendToFile(logFile, "CM: E type event recorded");
			}

			// we'll want to keep current time updated if necessary
			// (but without changing what the user thinks they're using for
			// the time) so update it here
			if (currTimeBox.isSelected())
				updateTime();
		}

		private void writeUpdate(Update update) {
			FileIO.appendToFile(timesFile, update.getType() + " " +
					update.getNode().getId() + " " +
					update.getEntrant().getId() + " " +
					update.getTime());
		}

	}

}
