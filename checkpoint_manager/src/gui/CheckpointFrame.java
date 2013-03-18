package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckpointFrame extends JFrame {

	public CheckpointFrame() {
		this.setTitle("Checkpoint Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = new Dimension(800, 600);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
		this.setMinimumSize(dim);

		JLabel fooLabel = new JLabel("Foobar");
		this.getContentPane().add(fooLabel);
		fooLabel.setVisible(true);

		this.pack();
	}

}
