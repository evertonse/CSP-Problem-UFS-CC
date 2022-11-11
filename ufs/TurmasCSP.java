package ufs;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import javax.swing.text.html.FormView;

import aima.core.environment.support.CSPFactory;
import aima.core.search.api.Assignment;
import aima.core.search.api.CSP;
import aima.core.search.api.Constraint;
import aima.core.search.basic.csp.AC3;
import aima.core.search.basic.csp.BacktrackingSearch;
import aima.core.search.basic.support.BasicCSP;
import aima.core.search.basic.support.BasicConstraint;

import ufs.cc.Disciplina;
import ufs.cc.Turma;
import ufs.cc.Estudante;

public class TurmasCSP {

// assembly of a car
public static void run() {
	final int MAX_MINUTES_ALLOWED = 30;
	final int N_TURMAS = 9;
	
	String[] variables = getVariables(N_TURMAS);

	Object[] all_disciplinas = Disciplina.getAll().toArray();
	
	// Cada dominio indica são as possiveis turmas que podem ser associadas a uma escolha de turma
	Object[][] domains = new Object[variables.length][MAX_MINUTES_ALLOWED + 1];

	for (int i = 0; i < variables.length; i++) {
		domains[i] = all_disciplinas.clone();
	}
	
	Disciplina[] disciplinas_cursadas = new Disciplina[]{
		Disciplina.disciplinaFromCode(""),

	};
	boolean 
		PIBIC 	= true,
		ESTAGIO = false,
		PIBITI 	= false;

	Estudante e = new Estudante(
		disciplinas_cursadas,
		PIBIC,PIBITI,ESTAGIO
	);

	Constraint[] restrictions = getConstraints(variables,e);

	CSP csp = new BasicCSP( variables, domains, restrictions);
	printCSP(csp);


	AC3 ac3 = new AC3();
	ac3.test(csp);

	System.out.println("Após aplicas AC3.test()--------------------------------------------------------------------");
	printCSP(csp );
	System.out.println("=====================================================================");

	BacktrackingSearch search = new BacktrackingSearch();
	Assignment assignment = search.apply(csp);
	Map<String,Object> assignment_map = assignment.getAssignments();

	System.out.println("\nApós aplicar BacktrackingSearch.apply(csp)");
	System.out.println(assignment);
	System.out.println("Solução ? : " + (assignment.isSolution(csp) ? "True": "False"));
	printCSP(csp);
	for (Map.Entry<String, Object> entry : assignment_map.entrySet()) {
			System.out.println(entry.getKey() + " = " + (Integer)entry.getValue());
	}
	//------------------------------------------------------------------------------------------
}

static public String[] getVariables(int n_turmas){
	String[] vars = new String[n_turmas];
	for (int i = 1; i <= n_turmas; i++) {
		vars[i] = "E" + String.valueOf(i);
	}
	return vars;
}


static public Constraint[] getConstraints(String[] variables, Estudante estudante){
	List<Constraint> restrictions = new ArrayList<>(25);
	// each constraint is indexed, that means that vals[i] is
	// the value currently assigned to variables[i], i think ...
	for (int i = 1; i <= variables.length; i++) {
		for (int j = 0; j < variables.length; j++) {
			if (i == j)
				continue;
			
			restrictions.add(
				new BasicConstraint(
					new String[]{variables[i], variables[j]},
					vals -> ((Turma) vals[0]).conflita(((Turma)vals[1])) == false)
			);
		}
	}

	for (int i = 1; i <= variables.length; i++) {
		
	}
	return restrictions.toArray(new Constraint[restrictions.size()]);
}

static public void printCSP(CSP csp) {

	System.out.println( "Variaveis : " + csp.getVariables() );
	System.out.println( "Dominios  : " + csp.getDomains() );
	for (String x : csp.getVariables()) {
			System.out.println(
					"Dominio de " + x + " : "
					+ csp.getDomains().get( csp.indexOf(x) ).getValues()
					//+ csp.getDomainValues(x)
			);
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

	System.out.println( "Restricoes : " + csp.getConstraints());
	System.out.println( "RestricaoBinaria: " + csp.getConstraints().get(0).isBinary() );
	System.out.println( "csp       : " + csp );
	System.out.println("---------------------------------------------------------------------");
}

} // END class
