import aima.core.search.basic.uninformed.GraphSearch;
import cc.Disciplina;

import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
			
			// CSPExamples.run();
			// JobshopSchedulingCSP.run();
			List<Disciplina> all = Disciplina.getAll();
			System.out.println(String.format("%-7s %1s %-3s %1s %s","CODIGO","|","CARGA HORARIA","|","NOME"));
			for (Disciplina disc : all) {
				System.out.println(disc);
			}
			new Scanner(System.in).nextLine();
    }
}