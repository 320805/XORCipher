import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main implements ActionListener {

	JFrame frame = new JFrame();

	
	JButton encryptButton = new JButton("Encrypt");
	JButton decryptButton = new JButton("Decrypt");
	
	JTextField findInputFile = new JTextField("Type in the input file name here.");
	JTextField inputDecryptNumber = new JTextField("Input the number attached to your decrypted files here");
	JTextField decryptNumber = new JTextField(); // number to use TO DECRYPT AN ECNRYPTED FILE
	JTextField encryptNumber = new JTextField(); // number added TO AN ENCRYPTED NORMAL FILE

	Container left = new Container();
	Container right = new Container();

	public Main() {
		frame.setSize(1000, 750);
		frame.setLayout(new GridLayout(1, 2));

		left.setLayout(new GridLayout(3, 1));
		right.setLayout(new GridLayout(3, 1));

		left.add(findInputFile);
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
		// file wont be overwritten over
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
		} //this is repeated 64 times to generate a 64 bit key
		return key;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
