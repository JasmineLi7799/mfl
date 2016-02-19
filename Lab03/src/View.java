import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * This is the GUI for the program. It still needs to be cleaned up a bit.
 * 
 * @author meganmayfield
 *
 */

public class View {
	int maxSize; //will be used to know how many letters to ask the user for
	int sideLength; //likewise
	int targetLength; //will be used to know how long the words will be
	JFrame frame;
	Puzzle puzzle;
	JPanel puzzlePanel;
	JComboBox sizeOptions;
	JComboBox lengthOptions;
	ComboBoxModel[] models; //used to make a combo box change dynamically
	String[] sizes; //necessary to keep track of possibilities
	RecursiveSearch search;
	
	public View() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(300,200));
		maxSize = 4; //DEFAULT, the lowest possible amount
		sideLength = 2; //DEFAULT, the lowest possible amount
		targetLength = 2; //DEFAULT, the lowest possible amount
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane();
		
		/**
		 * Top panel for the two numerical prompts. It has subpanels for the prompts.
		 */
		JPanel topPanel = new JPanel();
		JPanel sizePanel = new JPanel(); 
		JPanel targetPanel = new JPanel(); 
		
		//set up the main and sub panels with their layouts/colors
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		sizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		sizePanel.setBackground(new Color(255,255,255));
		targetPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		targetPanel.setBackground(new Color(255,255,255));
		
		//add a title bar
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(255,255,255));
		JLabel titleLabel = new JLabel("AUTOMATED WORD FINDER!");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(titleLabel);
		
		//components for the editable size options
		JLabel sizePrompt = new JLabel("Side Length: ");
		sizes = new String[] {"2", "3", "4"};
		sizeOptions = new JComboBox(sizes);
		
		sizeOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //set the model for the other combo box
				lengthOptions.setModel(models[sizeOptions.getSelectedIndex()]);
				sideLength = Integer.valueOf(sizes[sizeOptions.getSelectedIndex()]);
				maxSize = sideLength*sideLength; //update size info
			}
		});
		
		sizePanel.add(sizePrompt);
		sizePanel.add(sizeOptions);
		
		//prompt for the target option
		JLabel targetPrompt = new JLabel("Target Word Length: ");
		
		//options for the target length; see the model method, again
		lengthOptions = new JComboBox();
		models = new ComboBoxModel[3];
		configureModels();
		lengthOptions.setModel(models[0]);
		
		lengthOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //just update the variable 
				targetLength = Integer.valueOf(lengthOptions.getSelectedIndex());
			}
		});
		
		targetPanel.add(targetPrompt);
		targetPanel.add(lengthOptions);
		
		//add the subpanels in the top section of the gui
		topPanel.add(titlePanel);
		topPanel.add(sizePanel);
		topPanel.add(targetPanel);
		
		/**
		 * Middle panel for the input prompt.
		 */
		JPanel middlePanel = new JPanel();
		JPanel promptPanel = new JPanel();
		
		//set up the main and sub panels with their layouts
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		promptPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//prompt to start the process of filling in the matrix
		JButton inputPrompt = new JButton("Click Here to Enter Letters.");
		inputPrompt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] letters = askForLetters();
				puzzle = new Puzzle(sideLength, targetLength, letters);
				configurePuzzle();
				//CALL THE SEARCHING ALGORITHM HERE
			}
		});
		promptPanel.add(inputPrompt);
		
		//add the subpanel into the middle section of the gui
		middlePanel.add(promptPanel);
		
		//finally put the main sections together
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		frame.getContentPane().add(middlePanel, BorderLayout.CENTER);
	
		frame.pack();
	}
	
	/**
	 * These "models" are used to make the second combo box dynamic by providing different content.
	 */
	public void configureModels() {
		models[0] = new DefaultComboBoxModel(determineSizes(2));
		models[1] = new DefaultComboBoxModel(determineSizes(3));
		models[2] = new DefaultComboBoxModel(determineSizes(4));
	}
	
	/**
	 * This short algorithm determines how to fill the models' lists of options. 
	 * It is its own method to maintain a single point of reference.
	 */
	public String[] determineSizes(int size) {
		String[] options = new String[size*size-1];
		int last = 2;
		for(int i=0; i<options.length; i++) {
			options[i] = String.valueOf(last);
			last++;
		}
		
		return options;
	}
	
	/**
	 * This is the process by which the user enters data into the matrix... a series of 
	 * dialogue pop-ups that allow the user to input a set number of characters.
	 * There were issues with making text fields work with limitations, so this works instead.
	 */
	public String[][] askForLetters() {
		String[][] letters = new String[sideLength][sideLength];
		int row = 0;
		int col = 0;
		boolean validity = true; //we will check the validity of the input as we go
		
		//same # of dialog boxes as there are boxes in the puzzle
		for(int i=0; i<maxSize; i++) {
			String s = "";
			
			if(validity) { //two dialog options depending on previous input
				s = (String)JOptionPane.showInputDialog(frame, "Enter a single letter: ", i + "/" + maxSize, JOptionPane.PLAIN_MESSAGE, null, null, "");
			}else{
				s = (String)JOptionPane.showInputDialog(frame, "That wasn't valid. Enter a single letter: ", i + "/" + maxSize, JOptionPane.PLAIN_MESSAGE, null, null, "");
			}
			
			//if the user presses cancel... 
			if(s == null) {
				System.exit(0); 
				
			} else {
				//check if valid size
				if(s.length() != 1) {
					validity = false;
					i--;
				} else {
					//go on to check if char or int
					Character letter = s.charAt(0);
					if(Character.isDigit(letter)) {
						validity = false;
						i--;
					} else {
						//append to matrix if valid
						validity = true;
						letters[row][col] = s;
						if(col==sideLength-1) {
							row++;
							col = -1;
						} 
						col++;
					}
				}
			}
		}
		
		return letters;
	}
	
	/**
	 * This uses the puzzle object to create a new object in the frame.
	 */
	public void configurePuzzle() {
		
		/**
		 * Final panel for displaying the list of possible words
		 */
		JPanel bottomPanel = new JPanel();
		JPanel labelPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		puzzlePanel = new JPanel();
		
		//set up the main and sub panels with their layouts
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		puzzlePanel.setLayout(new GridLayout(puzzle.side, puzzle.side, 0, 0));
		
		renderBoard();
		
		//label for the results
		JLabel resultsLabel = new JLabel("Discovered Words: ");
		labelPanel.add(resultsLabel);
		
		//make a non-editable text area for the results to be displayed
		JTextArea results = new JTextArea();
		results.setLineWrap(true);
		results.setEditable(false);
		results.setPreferredSize(new Dimension(300,100));
		
		//this should scroll to account for large amounts of data
		JScrollPane scrolling = new JScrollPane(results);
		scrolling.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//fill in with results
		search = new RecursiveSearch();
		ArrayList<String> words = search.search(targetLength, 0, 0, new ArrayList<int[]>(), puzzle);
		
		if(words.isEmpty()) {
			results.setText("No valid words found.");
		} else {
			for(int i=0; i<words.size(); i++) {
				results.append(words.get(i));
			}
		}
		
		displayPanel.add(results);
		
		//add the subpanels into the bottom section of the gui 
		bottomPanel.add(puzzlePanel);
		bottomPanel.add(labelPanel);
		bottomPanel.add(displayPanel);
		
		//finish off. these new panels replace the old panel
		frame.setContentPane(bottomPanel);
		frame.pack();
		frame.validate();
	}
	
	/**
	 * Algorithm that renders the squares for the puzzle.
	 */
	public void renderBoard() {
		for(int row=0; row<puzzle.side; row++) {
			for(int col=0; col<puzzle.side; col++) {
				JLabel box = new JLabel(puzzle.letters[row][col], SwingConstants.CENTER);
				box.setPreferredSize(new Dimension(20,20));
				if((row%2) == (col%2)) {
					box.setOpaque(true);
					box.setBackground(Color.WHITE);
				} else {
					box.setOpaque(true);
					box.setBackground(Color.PINK);
				}
				puzzlePanel.add(box);
			}
		}
	}

}
