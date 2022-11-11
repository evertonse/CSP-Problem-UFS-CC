package ufs;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import aima.core.environment.support.CSPFactory;
import aima.core.search.api.Assignment;
import aima.core.search.api.CSP;
import aima.core.search.api.Constraint;
import aima.core.search.basic.csp.AC3;
import aima.core.search.basic.csp.BacktrackingSearch;
import aima.core.search.basic.support.BasicCSP;
import aima.core.search.basic.support.BasicConstraint;
class Task {
        final String name;
        final  int duration;
        public Task(String name, int duration){
            this.duration = duration;
            this.name = name;
        }
        public boolean isType(String type) {
            return name.toLowerCase().startsWith(type.toLowerCase());
        }

    }
public class JobshopSchedulingCSP {

    // assembly of a car
    public static void run() {
        final int MAX_MINUTES_ALLOWED = 30;
        Task[] tasks = new Task[]{
            new Task("AxleF",10) ,
            new Task("AxleB",10) ,
            new Task("WheelRF",1),
            new Task("WheelLF",1),
            new Task("WheelRB",1),
            new Task("WheelLB",1),

            new Task("NutsRF",2) ,
            new Task("NutsLF",2) ,
            new Task("NutsRB",2) ,
            new Task("NutsLB",2) ,

            new Task("CapRF",1)  ,
            new Task("CapLF",1)  ,
            new Task("CapRB",1)  ,
            new Task("CapLB",1)  ,

            new Task("Inspect",3)
        };

        String[] variables = getVariables(tasks);

        // Cada dominio indica o minuto de COMEÇO do serviço
        Object[][] domains = new Object[variables.length][MAX_MINUTES_ALLOWED + 1];
        for (int i = 0; i < variables.length; i++) {
            for (int j = 0; j <= MAX_MINUTES_ALLOWED; j++) {
                domains[i][j] = j;
            }
        }

        Constraint[] restrictions = getConstraints(tasks);

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

    static public String[] getVariables(Task[] tasks){
        String[] vars = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            vars[i] = tasks[i].name;
        }
        return vars;
    }


    static public Constraint[] getConstraints(Task[] tasks){
    List<Constraint> restrictions = new ArrayList<>(Arrays.asList(new Constraint[]{
            // each constraint is indexed, that means that vals[i] is
            // the value currently assigned to variables[i], i think ...

            //AxleF + 10 ≤ WheelRF;
            new BasicConstraint(
                new String[]{"AxleF", "WheelRF"},
                vals -> ((Integer) vals[0]) + 10 <= ((Integer) vals[1])
            ),
            // AxleF + 10 ≤ WheelLF;
            new BasicConstraint(
                new String[]{"AxleF", "WheelLF"},
                vals -> ((Integer) vals[0]) + 10 <= ((Integer) vals[1])
            ),
            // AxleB + 10 ≤ WheelRB;
            new BasicConstraint(
                new String[]{"AxleB", "WheelRB"},
                vals -> ((Integer) vals[0]) + 10 <= ((Integer) vals[1])
            ),
            //AxleB + 10 ≤ WheelLB;
            new BasicConstraint(
                new String[]{"AxleB", "WheelLB"},
                vals -> ((Integer) vals[0]) + 10 <= ((Integer) vals[1])
            ),

            // WheelRF + 1 ≤ NutsRF;
            new BasicConstraint(
                new String[]{"WheelRF","NutsRF"},
                vals -> ((int) vals[0] + 1 <= (int) vals[1])
            ),
            // WheelLF + 1 ≤ NutsLF;
            new BasicConstraint(
                new String[]{"WheelLF","NutsLF"},
                vals -> ((int) vals[0] + 1 <= (int) vals[1])
            ),
            // WheelRB + 1 ≤ NutsRB;
            new BasicConstraint(
                new String[]{"WheelRB","NutsRB"},
                vals -> ((int) vals[0] + 1 <= (int) vals[1])
            ),
            // WheelLB + 1 ≤ NutsLB;
            new BasicConstraint(
                new String[]{"WheelLB","NutsLB"},
                vals -> ((int) vals[0] + 1 <= (int) vals[1])
            ),


            //NutsRF + 2 ≤ CapRF;
            new BasicConstraint(
                new String[]{"NutsRF","CapRF"},
                vals -> ((int) vals[0] + 2 <= (int) vals[1])
            ),
            //NutsLF + 2 ≤ CapLF;
            new BasicConstraint(
                new String[]{"NutsLF","CapLF"},
                vals -> ((int) vals[0] + 2 <= (int) vals[1])
            ),
            //NutsRB + 2 ≤ CapRB;
            new BasicConstraint(
                new String[]{"NutsRB","CapRB"},
                vals -> ((int) vals[0] + 2 <= (int) vals[1])
            ),
            //NutsLB + 2 ≤ CapLB.
            new BasicConstraint(
                new String[]{"NutsLB","CapLB"},
                vals -> ((int) vals[0] + 2 <= (int) vals[1])
            ),


            // (AxleF + 10 ≤ AxleB) or (AxleB + 10 ≤ AxleF)
            new BasicConstraint(
                new String[]{"AxleF","AxleB"},
                vals ->
                   ((int) vals[0] + 10 <= (int) vals[1])
                || ((int) vals[1] + 10 <= (int) vals[0])
            ),
            /**/

        }));

        for (Task t: tasks) {
            if (t.isType("Inspect"))
                continue;
            restrictions.add(
                new BasicConstraint(
                    new String[]{t.name, "Inspect"},
                    vals -> ((int) vals[0] + t.duration <= (int) vals[1])
                )
            );
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
}
