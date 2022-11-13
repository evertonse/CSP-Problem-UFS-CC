package ufs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aima.core.search.api.Assignment;
import aima.core.search.api.Domain;
import aima.core.search.api.CSP;
import aima.core.search.api.Constraint;
import aima.core.search.basic.csp.AC3;
import aima.core.search.basic.csp.BacktrackingSearch;
import aima.core.search.basic.support.BasicCSP;
import aima.core.search.basic.support.BasicConstraint;

import ufs.cc.Disciplina;
import ufs.cc.Turma;
import ufs.cc.Estudante;
import ufs.cc.Horario;

public class TurmasCSP {

// assembly of a car
public static void run() {
	final int N_TURMAS_PARA_ESCOLHA	= 7;
	final int N_TURMAS_ORFERTADAS		= 45;

	final int CARGA_HORARIO_MAXIMA_PPC	= 480;
	final int CARGA_HORARIO_MINIMA_PPC	= 240;
	
	System.out.print(">>>>>>>>>>>>>>>>>>>>>> TurmaCSP <<<<<<<<<<<<<<<<<<<<<<<\n");
	
	Disciplina[] disciplinas_cursadas = new Disciplina[]{
		// Periodo 1
		Disciplina.disciplinaFromCode("MAT0151"),
		Disciplina.disciplinaFromCode("MAT0150"),
		Disciplina.disciplinaFromCode("MAT0057"),
		Disciplina.disciplinaFromCode("COMP0480"),
		Disciplina.disciplinaFromCode("COMP0393"),
		// Periodo 2
		Disciplina.disciplinaFromCode("MAT0152"),
		Disciplina.disciplinaFromCode("MAT0078"),
		Disciplina.disciplinaFromCode("FISI0260"),
		Disciplina.disciplinaFromCode("FISI0264"),
		Disciplina.disciplinaFromCode("COMP0410"),
		Disciplina.disciplinaFromCode("COMP0334"),
	};

	boolean 
	PIBIC 	= true,
	ESTAGIO = false,
	PIBITI 	= false;
	
	Estudante e = new Estudante(
		disciplinas_cursadas,
		Horario.Turno.Vespestino,
		PIBIC,PIBITI,ESTAGIO
	);
	
	// Meira hora extra de estudo a caga 15 horas de carga horaria
	e.setHorasSemanaisExtraPorCargaHoraria(0.5f,15.0f);
	e.setHorasDeViagemIda(1.f);
	e.setHorasDeViagemVolta(1.f);

	System.out.println(e + "\n");
	
	final int CARGA_HORARIO_MAXIMA_DO_ALUNO = e.cargaHorariaMax();
	
	final int CARGA_HORARIO_MAXIMA =   CARGA_HORARIO_MAXIMA_DO_ALUNO < CARGA_HORARIO_MAXIMA_PPC?  CARGA_HORARIO_MAXIMA_DO_ALUNO : CARGA_HORARIO_MAXIMA_PPC;
	
	System.out.println("CARGA_HORARIO_MAXIMA_DO_ALUNO = " + CARGA_HORARIO_MAXIMA_DO_ALUNO);
	System.out.println("CARGA_HORARIO_MAXIMA = " + CARGA_HORARIO_MAXIMA);
	
	String[] variables        = getVariables(N_TURMAS_PARA_ESCOLHA);
	// Cada dominio indica são as possiveis turmas que podem ser associadas a uma escolha de turma
	Object[][] domains        = getDomains(variables.length,N_TURMAS_ORFERTADAS,e);	
	Constraint[] restrictions = getConstraints(variables,e, CARGA_HORARIO_MAXIMA,CARGA_HORARIO_MINIMA_PPC);

	CSP csp = new BasicCSP( variables, domains, restrictions);
	printCSP(csp);
	AC3 ac3 = new AC3();
	ac3.test(csp);

	System.out.println("Após aplicas AC3.test()");
	printCSP(csp);
	System.out.println("=====================================================================");
	
	BacktrackingSearch search = new BacktrackingSearch();
	Assignment assignment = search.apply(csp);
	
	System.out.println("\nApós aplicar BacktrackingSearch.apply(csp)");
	
	if(assignment == null) {
		System.out.println("Assignment = " + assignment);
		System.out.println("Nao foi possivel encontrar atribuicao completa e consistente");
		return;
	}

	System.out.println("É Solução ? : " + (assignment.isSolution(csp) ? "Sim" : "Não"));
	//printCSP(csp);
	
	Map<String,Object> assignment_map = assignment.getAssignments();
	System.out.println("=====================================================================");
		for (Map.Entry<String, Object> entry : assignment_map.entrySet()) {
			System.out.println("\n >> " + entry.getKey() + " = " + (Turma)entry.getValue() + "\n");
	}
	//------------------------------------------------------------------------------------------
}

static public Object[][] getDomains(int variables_length,int n_turmas, Estudante e){
	
	Turma[] all_turmas = Turma.getOfertas(n_turmas,e.turno); // 25 turmas no horario 1 (vespertino)
	System.out.println("\n>> Turmas Ofertadas:");
	for(Turma t: all_turmas) {
		System.out.println(t);
	}

	// Cada dominio indica são as possiveis turmas que podem ser associadas a uma escolha de turma
	Disciplina[] cursadas = e.getDisciplinasCursadas();

	// Turmas devem ser restringidas para garantir um dominio viavel
	// Isto é, devemos tirar tomas as disciplians que o aluno ja cursou,
	// além das disciplinas que o aluno não tem pre-requisitos para cursar
	ArrayList<Turma> turmas_restringidas = new ArrayList<Turma>();

	for (int i = 0; i < all_turmas.length; i++) {
		boolean disciplina_permitida = true;
		for (int j = 0; j < cursadas.length; j++) {
			Turma t =  all_turmas[i];
			Disciplina d  = t.getDisciplina();
			// Checando se a turma é de alguma disciplina já cursada pelo discente
			boolean iguais = d.equals(cursadas[j]);
			boolean satisfaz_prerequisito = d.satisfazPreReq(cursadas);
			// Se as turmas são iguais a que ele cursou
			// Ou ele não satisfaz o prerequisito então 
			// a disciplinas nao é permitida
			if (iguais || ! satisfaz_prerequisito) {
				disciplina_permitida = false;
				break;
			}
		}

		if(disciplina_permitida) {
			turmas_restringidas.add(all_turmas[i]);
		}
	}
	turmas_restringidas.add(Turma.getTurmaVazia());

	Object[][] domains = new Object[variables_length][turmas_restringidas.size()];
	for (int i = 0; i < variables_length; i++) {
		domains[i] = turmas_restringidas.toArray().clone();
	}
	
	System.out.println("\n>> Turmas Possiveis para o aluno:  considerando (pre-requisito e nao repetir turma já cursada):");
	for(Turma t: turmas_restringidas) {
		System.out.println(t);
	}
	System.out.println("<<\n");
	
	System.out.println("\n>> Disciplinas Cursadas pelo estudante:");
	for(Disciplina d: e.getDisciplinasCursadas()) {
		System.out.println(d);
	}
	System.out.println("<<\n");
	return domains;
	
}

static public String[] getVariables(int n_turmas){
	String[] vars = new String[n_turmas];
	for (int i = 1; i <= n_turmas; i++) {
		vars[i-1] = "E" + String.valueOf(i);
	}
	return vars;
}


static public Constraint[] getConstraints(String[] variables, Estudante estudante, int ch_max, int ch_min){
	List<Constraint> restrictions = new ArrayList<>(25);

	Constraint min_max_carga_horario = new BasicConstraint(
		variables,
		(vals) -> {
			int sum = 0;
			
			for (Object val : vals) {
				sum += ((Turma) val).getDisciplina().getCargaHoraria();
			}
			
			if (sum >= 240 && ch_min <= ch_max){
				return true;
			} 
			else{
				return false;
			}

		}
	);

	restrictions.add(min_max_carga_horario);
	
	// each constraint is indexed, that means that vals[i] is
	// the value currently assigned to variables[i], i think ...
	for (int i = 0; i < variables.length; i++) {
		for (int j = 0; j < variables.length; j++) {
			if (i == j)
				continue;
			
			restrictions.add(
				new BasicConstraint(
					new String[]{variables[i], variables[j]},
					vals -> {
						Turma t1 = (Turma) vals[0];
						Turma t2 = (Turma) vals[1];

						boolean conflict = t1.conflita(t2);
						// Só permitimos se NÂO der conflito
						return conflict == false;
					}
				)
			);
		}
	}

	return restrictions.toArray(new Constraint[restrictions.size()]);
}

static public void printCSP(CSP csp) {
	
	System.out.println("\n---------------------------------------------------------------------");
	System.out.println( "Variaveis : " + csp.getVariables() );
	System.out.println( "|Dominios| = " + csp.getDomains().size() );
	int index = 0;
	for (Domain d : csp.getDomains()) {
		System.out.println( "|Dominios[" + String.valueOf(index) + "]| = " + d.size() );
		index += 1;
	}

	System.out.print( "Tuplas da Relacao de Restricao = {");
	for (Constraint constraint : csp.getConstraints()) {
		System.out.print( "(");
		for (String scope:constraint.getScope()) {
				System.out.print(scope + ',');
		}
		System.out.print( "),");
	}
	System.out.println( " }");

	System.out.println( "|Restricoes| = " + csp.getConstraints().size());
	System.out.println("---------------------------------------------------------------------");
}

} // END class
