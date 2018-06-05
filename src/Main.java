import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main implements ActionListener {

	JFrame frame = new JFrame();

	JButton encryptButton = new JButton("Encrypt");
	JButton decryptButton = new JButton("Decrypt");

	JTextField findFile = new JTextField("Type in the input file name here.");
	JTextField inputDecryptNumber = new JTextField("Input the number attached to your decrypted files here");
	JTextField decryptNumber = new JTextField(); // number to use TO DECRYPT AN ECNRYPTED FILE
	JTextField encryptNumber = new JTextField(); // number added TO AN ENCRYPTED NORMAL FILE

	Container left = new Container();
	Container right = new Container();

	Scanner fileInput;

	char[] inputStringArray;
	String[] binaryStringArray;

	public Main() {
		frame.setSize(1000, 750);
		frame.setLayout(new GridLayout(1, 2));

		left.setLayout(new GridLayout(3, 1));
		right.setLayout(new GridLayout(3, 1));

		left.add(findFile);
		left.add(encryptButton);
		encryptButton.addActionListener(this);
		left.add(encryptNumber);

		right.add(inputDecryptNumber);
		right.add(decryptButton);
		decryptButton.addActionListener(this);
		right.add(decryptNumber);

		encryptNumber.setText("1");
		decryptNumber.setText("2");

		encryptNumber.setEditable(false);
		decryptNumber.setEditable(false);

		frame.add(left);
		frame.add(right);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.repaint();

		int randomNum = (int) (Math.random() * 1000); // a random number is used to name the sort file so that a single
														// file wont be written over when a new file is written to
		PrintWriter writer; // sourced from
							// https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
		try {
			writer = new PrintWriter(randomNum + "output.txt", "UTF-8");
			writer.write("encrypted text");
			writer.write("\r\n");
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("Sort printed to file #" + randomNum); // prints out to the console what the file name is

	}

	public static void main(String[] args) {
		new Main();
	}

	public void charToBinary(char[] charArray) {
		int converterInt;
		String converterString; // string and int used to convert the char array into binary
		binaryStringArray = new String[charArray.length]; // sets the binary string array to the same size as the string array
															
		for (int i = 0; i < charArray.length; i++) { // runs through the array
			converterInt = charArray[i]; // takes the int value of the char in the array and sets the converterInt equal
											// to it
			converterString = Integer.toBinaryString(converterInt); // turns the converter int

			while (converterString.length() < 8) {
				converterString = "0" + converterString; // makes sure every string in the array is 8 characters long,
															// without changing the final binary value
			}

			binaryStringArray[i] = converterString; // sets the converted binary value into an array
			System.out.print(binaryStringArray[i] + ", ");
		}
	}

	public void getFileInput() {
		try {
			fileInput = new Scanner(new File(findFile.getText() + ".txt"));
		}
		// If the file is not found, then print a statement saying that the file is not
		// found
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		// checks the input for the name of the text file
		String infile = fileInput.nextLine();
		// Get the input which is on the next line of the file
		inputStringArray = new char[infile.length()];
		// creates a char array which contains all the characters from the file
		for (int i = 0; i < infile.length(); i++) {
			// goes through the text in the file
			inputStringArray[i] = infile.charAt(i);
		}
		// ***********printing is just for testing ********
		System.out.println(inputStringArray);
		// ***********printing is just for testing ********

	}

	// This is a method to generate a random 64 bit binary key
	public String GenerateKey() {
		String key = ""; // First, an empty string is defined
		for (int i = 0; i < 64; i++) {
			double check = Math.random() * 100; // Then, a random number between 0 and 100 is generated
			if (check > 50) { // if the number is over 50, then a 1 is added to the string
				key += "1";
			} else { // otherwise, a 0 is added to the string
				key += "0";
			}
		} // this is repeated 64 times to generate a 64 bit key
		return key;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(encryptButton)) { // when the encryption button is pressed
			String key = GenerateKey();
			getFileInput();
			charToBinary(inputStringArray);
			System.out.println("length is " + binaryStringArray.length);

		}

	}

}
