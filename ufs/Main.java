import aima.core.search.basic.uninformed.GraphSearch;
import cc.Disciplina;
import cc.Horario;
import cc.Turma;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
			Random rn = new Random();
			// CSPExamples.run();
			// JobshopSchedulingCSP.run();
			List<Disciplina> all = Disciplina.getAll();
			System.out.println(String.format("%-7s %1s %-3s %1s %s","CODIGO","|","CARGA HORARIA","|","NOME"));
			for (Disciplina disc : all) {
				System.out.println(disc);
			}
			for (int i = 0; i < 10; i++) {
				int hora = rn.nextInt(0,4);
				int turno = rn.nextInt(0,3);
				int dia = rn.nextInt(0,5);
				if (turno == 2){
					hora = rn.nextInt(0,3);
				}

				Horario horario = new Horario(turno,dia,hora);
				System.out.println(horario);
				
			}
			
			for(Turma t: Turma.getOfertas(10,1)) {
				System.out.println(t);
			}
			new Scanner(System.in).nextLine();
    }
}