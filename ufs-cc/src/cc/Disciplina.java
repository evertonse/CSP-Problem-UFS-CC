package cc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class Disciplina {
    static private String path_todas_disciplinas = "";
    String name;
    char[] code;
    int carga_horaria,creditos;
    int carga_horaria_pratica, carga_horaria_teorica;
    // 1º, 2º, ..., periodo
    int periodo;
    // Tecnológica, Basica
    String departametno, area_de_formacao;
    //Perfil de Inteligência Artificial
    String perfil;
    boolean optional;

    public Disciplina(
        String code,  String name,
        int carga_horaria_pratica, int carga_horaria_teorica,
        boolean optional
    ) {

        this.carga_horaria = carga_horaria_pratica +  carga_horaria_teorica;
        this.carga_horaria_teorica = carga_horaria_teorica;
        this.carga_horaria_pratica = carga_horaria_pratica;
        this.optional = optional;
    }

    public List<Disciplina> loadAll() {
        List<Disciplina> disciplinas = new ArrayList<>(25);
        BufferedReader reader;
		int periodo = -1;
		String perfil = "Básico";
		try {
			reader = new BufferedReader(
			    new FileReader(Disciplina.path_todas_disciplinas));

			String line = reader.readLine();

			while (line != null) {
                if( line.charAt(0) == '#')
                    continue;
				if (line.contains(">>")){
                    String[] kv = line.substring(3).split(":");
                    if (kv[0] == "Perfil"){
                        perfil = kv[1];
                    }
                    else if (kv[0] == "Periodo"){
                        periodo =  Integer.parseInt(kv[1]);
                    }
                }

				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return disciplinas;
    }
}
