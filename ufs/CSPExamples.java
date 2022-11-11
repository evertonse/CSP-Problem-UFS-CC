package ufs;

//package csp;
//package excyber;
import aima.core.environment.support.CSPFactory;
import aima.core.search.api.CSP;
import aima.core.search.api.Constraint;
import aima.core.search.basic.support.BasicCSP;
import aima.core.search.basic.support.BasicConstraint;
import aima.core.search.basic.csp.AC3;

public class CSPExamples {

    public static void run() {
        Object[] digitos = new Object[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        String variaveis[] = new String[] {"X", "Y", "Z"};
        Object[][] dominios = new Object[][] { digitos, digitos, digitos };
        Constraint restricao = BasicConstraint.newTabularConstraint( variaveis, new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 4 }, { 3, 9 } } );

        CSP csp = new BasicCSP( variaveis, dominios, restricao );

        System.out.println( "Variáveis : " + csp.getVariables() );
        System.out.println( "Dominios  : " + csp.getDomains() );
        System.out.println( "DominioX  : " + csp.getDomains().get( csp.indexOf("X") ).getValues() );
        System.out.println( "DominioX-2: " + csp.getDomainValues("X") );
        System.out.println( "DominioY  : " + csp.getDomains().get( csp.indexOf("Y") ).getValues() );
        System.out.println( "DominioZ  : " + csp.getDomains().get( csp.indexOf("Z") ).getValues() );
        System.out.println( "RestricaoBinaria: " + csp.getConstraints().get(0).isBinary() );
        System.out.println( "csp       : " + csp );
        System.out.println("---------------------------------------------------------------------");
        System.out.println( "DominioX-2: " + csp.getDomainValues("X") );

        System.out.println("=====================================================================");
        //------------------------------------------------------------------------------------------

        CSP csp2 = CSPFactory.mapColoringTerritoriesOfAustraliaCSP();

        System.out.println( "Variáveis  : " + csp2.getVariables() );
        System.out.println( "Dominios   : " + csp2.getDomains() );
        System.out.println( "DominioWA  : " + csp2.getDomains().get( csp2.indexOf("WA") ).getValues() );
        System.out.println( "DominioWA-2: " + csp2.getDomains().get( csp2.indexOf("WA") ).getValues() );
        System.out.println( "DominioNT  : " + csp2.getDomains().get( csp2.indexOf("NT") ).getValues() );
        System.out.println( "DominioQ   : " + csp2.getDomains().get( csp2.indexOf("Q") ).getValues() );
        System.out.println( "RestricaoBinaria: " + csp2.getConstraints().get(8).isBinary() );
        System.out.println( "csp        : " + csp2 );
        System.out.println("---------------------------------------------------------------------");

        System.out.println( "DominioWA  : " + csp2.getDomains().get( csp2.indexOf("WA") ).getValues() );
        System.out.println("Reduzindo dominio de WA para red");
        csp2.getDomain("WA").reduceDomainTo( "red" );
        System.out.println( "DominioWA  : " + csp2.getDomains().get( csp2.indexOf("WA") ).getValues() );
        System.out.println("---------------------------------------------------------------------");
        System.out.println( "DominioSA  : " + csp2.getDomains().get( csp2.indexOf("SA") ).getValues() );
        System.out.println( "Removendo green em SA");
        csp2.getDomain("SA").delete("green");
        System.out.println( "DominioSA  : " + csp2.getDomains().get( csp2.indexOf("SA") ).getValues() );
        System.out.println( "Removendo blue em SA");
        csp2.getDomain("SA").delete("blue");
        //csp.getDomains().get(X.i).delete(x);

        System.out.println( "DominioSA  : " + csp2.getDomains().get( csp2.indexOf("SA") ).getValues() );

        System.out.println("=Consistencia de Arco====================================================================");

        String variaveis2[] = new String[] {"X", "Y"};
        Object[][] dominios2 = new Object[][] { digitos, digitos };
        Constraint restricao2 = new BasicConstraint( variaveis2,
                values -> ((Integer) values[1]) == ((Integer) values[0]) * ((Integer) values[0])  );
//                values -> ((Integer) values[0]) + ((Integer) values[1]) == 4 );

        CSP csp3 = new BasicCSP( variaveis2, dominios2, restricao2 );

        System.out.println( "Variáveis : " + csp3.getVariables() );
        System.out.println( "Dominios  : " + csp3.getDomains() );
        System.out.println( "DominioX  : " + csp3.getDomains().get( csp3.indexOf("X") ).getValues() );
        System.out.println( "DominioX-2: " + csp3.getDomainValues("X") );
        System.out.println( "DominioY  : " + csp3.getDomains().get( csp3.indexOf("Y") ).getValues() );
        System.out.println( "RestricaoBinaria: " + csp3.getConstraints().get(0).isBinary() );
        System.out.println( "csp       : " + csp3 );
        System.out.println("---------------------------------------------------------------------");
        System.out.println( "DominioX-2: " + csp3.getDomainValues("X") );
        System.out.println("---------------------------------------------------------------------");

        AC3 ac3 = new AC3();
        ac3.test(csp3);
        System.out.println("-Depois de ac3.test()--------------------------------------------------------------------");
        System.out.println( "Variáveis : " + csp3.getVariables() );
        System.out.println( "Dominios  : " + csp3.getDomains() );
        System.out.println( "DominioX  : " + csp3.getDomains().get( csp3.indexOf("X") ).getValues() );
        System.out.println( "DominioX-2: " + csp3.getDomainValues("X") );
        System.out.println( "DominioY  : " + csp3.getDomains().get( csp3.indexOf("Y") ).getValues() );
        System.out.println( "RestricaoBinaria: " + csp3.getConstraints().get(0).isBinary() );
        System.out.println( "csp       : " + csp3 );

        System.out.println("=====================================================================");

        //------------------------------------------------------------------------------------------
    }
}
