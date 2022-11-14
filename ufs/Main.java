package ufs;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	// Deixe a variavel abaxio como 'true' para mandar printar em arquivo txt no lugar do console.
	static final boolean SEND_TO_OUTPUT_FILE = false; 
	public static void main(String[] args) {
		try {
			if(SEND_TO_OUTPUT_FILE)
				System.setOut(new PrintStream(new File("output-file.txt")));
		} 
		catch (Exception e) {
				e.printStackTrace();
		}

		// CSP das Turmas
		// NOTA AS TURMAS SÃO GERADAS DE COMBINAÇÃO ALEAOTIRAS DE MATERIAS E PROFESSORES E HORARIOS
		// Portanto é possivel não satisfazer todas as restrições é um periodo, mas basta rodar novamente
		// que é bem provavel que escontre uma solução.
		TurmasCSP.run();
		if (! SEND_TO_OUTPUT_FILE)
			new Scanner(System.in).nextLine();
	}
}