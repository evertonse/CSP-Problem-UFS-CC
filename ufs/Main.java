package ufs;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	// Deixe a variavel abaxio como 'true' para mandar printar em arquivo txt no lugar do console.
	static final boolean SEND_TO_OUTPUT_FILE = true; 
	public static void main(String[] args) {
		try {
			if(SEND_TO_OUTPUT_FILE)
				System.setOut(new PrintStream(new File("output-file.txt")));
		} 
		catch (Exception e) {
				e.printStackTrace();
		}

		// CSP das Turmas
		TurmasCSP.run();
		if (! SEND_TO_OUTPUT_FILE)
			new Scanner(System.in).nextLine();
	}
}