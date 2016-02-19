import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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


/**
 * Word Find Case Study 
 * 	1. Utilize the cache file with combined dictionaries.
 *  2. Prompt the user to create a word puzzle.
 *  3. Recursively find all solutions to the puzzle.
 *  
 *  Team Members: Jasmine Li, Jackson Farnsworth, & Megan Mayfield
 * 
 * @author Jasmine
 *
 */

public class main {
	static View frame;
	
	public static void main(String[] args) {
		//first check update
		manager mana = new manager();
		mana.cache();
		
		//initialize the frame
		frame = new View();
		
	}
}