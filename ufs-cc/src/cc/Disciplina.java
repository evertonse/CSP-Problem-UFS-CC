package cc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Period;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

import cc.DisciplinaRawData;

public class Disciplina {
	enum Perfil { 
		Basico, 
		InteligenciaArtificial,
		LinguagensDeProgramacao
	}
	
	static private List<Disciplina> all_disciplinas = null;
	static private Map<String,Disciplina> code_to_disc = new HashMap<>();
	
	String code,name;
	List<String> pre_req_codes;

	int  carga_horaria_total,carga_horaria_teorica,carga_horaria_pratica;
	// 1º, 2º, ..., periodo
	int periodo;
	// Tecnológica, Basica
	String departametno, area_de_formacao;
	//Perfil de Inteligência Artificial
	Perfil perfil;
	boolean optional;

	public Disciplina(
			String code,  String name,
			int carga_horaria_teorica,
			int carga_horaria_pratica, 
			boolean optional,
			int periodo,
			Perfil perfil
	) {
		this.name = name;
		this.code = code;

		
		this.periodo = periodo;
		this.carga_horaria_teorica = carga_horaria_teorica;
		this.carga_horaria_pratica = carga_horaria_pratica;
		this.optional = optional;
		this.perfil = perfil;
		
		Disciplina.code_to_disc.put(this.code, this);
	}

	static public List<Disciplina> getAll() {
		if (all_disciplinas !=  null) 
			return all_disciplinas;
		
		Disciplina.all_disciplinas = new ArrayList<Disciplina>();

		for (int i = 1; i <= 9; i++) {
			int periodo = i;
			String[] disciplinas = DisciplinaRawData.getRawPeriodo(periodo);
			if (disciplinas == null)
			continue;
			for (String disc : disciplinas) {
				Disciplina new_disc = disciplinaFromString(disc,periodo,false, Perfil.Basico);
				all_disciplinas.add(new_disc);
			}
		}
		
		
		int periodo = -1;
		boolean opcional = true;
		String[] disciplinas = DisciplinaRawData.getRawInteligenciaArtificial();
		for (String disc : disciplinas) {
			Disciplina new_disc = disciplinaFromString(disc,periodo,opcional,Perfil.InteligenciaArtificial);
			all_disciplinas.add(new_disc);
		}

		disciplinas = DisciplinaRawData.getRawLinguagensDeProgramacao();
		for (String disc : disciplinas) {
			Disciplina new_disc = disciplinaFromString(disc,periodo,opcional,Perfil.InteligenciaArtificial);
			all_disciplinas.add(new_disc);
		}

		return Disciplina.all_disciplinas;
	}

	public int getCreditos(){
		return getCargaHoraria()/15;
	}
	
	static public Disciplina disciplinaFromString(
		String disciplina,
		int periodo,boolean opcional,
		Perfil perfil) 
	{

		String[] data = disciplina.split(",");
		
		String disc_code = data[0];
		String disc_name = data[1];
		
		int disc_ch_teorica = data[5].equals("-") ? 0 : Integer.parseInt(data[5]);
		int disc_ch_pratica = data[6].equals("-") ? 0 : Integer.parseInt(data[6]);
		
		if (disc_ch_teorica + disc_ch_teorica == 0) {
			System.out.println(data[4]);
			disc_ch_teorica = data[4] == "-" ? 0 : Integer.parseInt(data[4]);
		}
		
		String[] pre_reqs_raw = data[data.length-1].split(";");
		
		Disciplina disc = new Disciplina(
				disc_code, disc_name, 
				disc_ch_teorica, disc_ch_pratica, 
				opcional,periodo,perfil
			);
		
		for (String pr : pre_reqs_raw) {
			if (pr.contains("PRO")) {
				disc.addPreReq(pr.replace("(PRO)", ""));
			}
		}
		return disc;
	}

	public int getCargaHoraria() {
		return carga_horaria_pratica + carga_horaria_teorica;
	}
	
	public void setPreReq(List<String> codes) {
		this.pre_req_codes = new ArrayList<String>(codes);
	}

	public void addPreReq(String code) {
		if (pre_req_codes == null)
			pre_req_codes = new ArrayList<>(3);
		this.pre_req_codes.add(code);
	}

	@Override
	public String toString() {
		return String.format("%-10s %1s %-3s %1s %s", this.code,"", Integer.toString(this.getCargaHoraria()),"", this.name);
	}
}
