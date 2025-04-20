package gameOfLife;

import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GridPanel gridPanel;
	private JPanel controlPanel;
	
	private JButton restartButton;
	
	private JLabel generationLabel;
	private JLabel geracoesLabel;
	
	private JLabel cellLabel;
	private JLabel aliveCellLabel;
	
	private JLabel idadeLabel;	
	private JTextField maxAgeField;
	
	private JLabel timerLabel;	
	private JTextField delayField;

	
	public GameScreen() {
		super("Game of Life");

		gridPanel = new GridPanel();

		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setPreferredSize(new Dimension(160, 600));

	
		controlPanel.add(Box.createRigidArea(new Dimension(0, 300)));

		geracoesLabel = new JLabel("Geracões");
		geracoesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		geracoesLabel.setFont(new Font("Courier", Font.PLAIN, 18));
		geracoesLabel.setPreferredSize(new Dimension(120, 20));
		controlPanel.add(geracoesLabel);

		generationLabel = new JLabel("000000");
		generationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		generationLabel.setFont(new Font("Courier", Font.PLAIN, 20));
		generationLabel.setPreferredSize(new Dimension(120, 20));
		controlPanel.add(generationLabel);

		controlPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		
		cellLabel = new JLabel("Celulas Vivas");
		cellLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cellLabel.setFont(new Font("Courier", Font.PLAIN, 18));
		cellLabel.setPreferredSize(new Dimension(120, 20));
		controlPanel.add(cellLabel);

		aliveCellLabel = new JLabel("000000");
		aliveCellLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		aliveCellLabel.setFont(new Font("Courier", Font.PLAIN, 20));
		aliveCellLabel.setPreferredSize(new Dimension(120, 20));
		controlPanel.add(aliveCellLabel);

		controlPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		idadeLabel = new JLabel("Idade Máxima");
		idadeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		idadeLabel.setFont(new Font("Courier", Font.PLAIN, 14));
		idadeLabel.setPreferredSize(new Dimension(120, 20));
		controlPanel.add(idadeLabel);
		maxAgeField = new JTextField("10", 5);
		maxAgeField.setMaximumSize(new Dimension(100, 25));
		maxAgeField.setAlignmentX(Component.CENTER_ALIGNMENT);
		maxAgeField.setHorizontalAlignment(JTextField.RIGHT);
		controlPanel.add(maxAgeField);

		controlPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		
		timerLabel = new JLabel("Tempo (ms)");
		timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		timerLabel.setFont(new Font("Courier", Font.PLAIN, 14));
		timerLabel.setPreferredSize(new Dimension(120, 20));
		controlPanel.add(timerLabel);
		delayField = new JTextField("100", 5);
		delayField.setMaximumSize(new Dimension(100, 25));
		delayField.setAlignmentX(Component.CENTER_ALIGNMENT);
		delayField.setHorizontalAlignment(JTextField.RIGHT);
		controlPanel.add(delayField);

		controlPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		restartButton = new JButton("Reiniciar");
		restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		restartButton.setFont(new Font("Arial", Font.BOLD, 16));
		controlPanel.add(restartButton);
		

		setLayout(new BorderLayout());
		add(gridPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.EAST);

		gridPanel.setGridListener(() -> {

		});



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setResizable(false);
		setVisible(true);

	}

	public GridPanel getGridPanel() {
		return gridPanel;
	}

	public JButton getPlayPauseButton() {
		return restartButton;
	}

	public JLabel getGenerationLabel() {
		return generationLabel;
	}
	
	public JLabel getaliveCellLabel() {
		return aliveCellLabel;
	}
	

	public int getMaxAge() {
		try {
			return Integer.parseInt(maxAgeField.getText());
		} catch (NumberFormatException e) {
			return 10;
		}
	}
	
	public int getDelay() {
		try {
			return Integer.parseInt(delayField.getText());
		} catch (NumberFormatException e) {
			return 100;
		}
	}

}
