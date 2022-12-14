package ufs.cc;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Disciplina {
	public enum Perfil { 
		Basico, 
		InteligenciaArtificial,
		LinguagensDeProgramacao,
		Algoritmos,
		ProcessamentodeImagens,
		EngSoftware
	}
	
	static private List<Disciplina> all_disciplinas = null;
	static private Map<String,Disciplina> code_to_disc = new HashMap<>();
	
	String code,name;
	Set<String> pre_req_codes;

	int  carga_horaria_total,carga_horaria_teorica,carga_horaria_pratica;
	// 1º, 2º, ..., periodo
	int periodo;
	// Tecnológica, Basica
	String departametno, area_de_formacao;
	//Perfil de Inteligência Artificial
	public Perfil perfil;
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
	
	public Disciplina(String code) {	
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
			Disciplina new_disc = disciplinaFromString(disc,periodo,opcional,Perfil.LinguagensDeProgramacao);
			all_disciplinas.add(new_disc);
		}
		disciplinas = DisciplinaRawData.getRawComputacaoTeoricaAlgoritmos();
		for (String disc : disciplinas) {
			Disciplina new_disc = disciplinaFromString(disc,periodo,opcional,Perfil.Algoritmos);
			all_disciplinas.add(new_disc);
		}
		disciplinas = DisciplinaRawData.getRawProcessamentoDeImagensComputacaoGrafica();
		for (String disc : disciplinas) {
			Disciplina new_disc = disciplinaFromString(disc,periodo,opcional,Perfil.ProcessamentodeImagens);
			all_disciplinas.add(new_disc);
		}

		disciplinas = DisciplinaRawData.getRawEngenhariaDeSoftware();
		for (String disc : disciplinas) {
			Disciplina new_disc = disciplinaFromString(disc,periodo,opcional,Perfil.EngSoftware);
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
			//System.out.println(data[4]);
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
		Disciplina.code_to_disc.put(disc.code, disc);
		return disc;
	}

	public boolean satisfazPreReq(Disciplina[] cursadas) { 
		
		if(this.pre_req_codes == null){
			return true;
		}

		if(this.pre_req_codes.size() == 0){
			return true;
		}
		
		Set<String> cursadas_code = new HashSet<String>(10);
		for (int i = 0; i < cursadas.length; i++) {
			cursadas_code.add(cursadas[i].getCode());
		}

		for (String req_code : this.pre_req_codes) {
			if(cursadas_code.contains(req_code) == false){
				// Se não existe o pre requisito entre as cursadas
				// então ele não satisfaz pre requisito
				return false;
			}
		}
		// Se chegou até aqui então cada prerequisito
		// estava entre as cursadas e portanto, satisfaz
		return true;
	}

	public int getCargaHoraria() {
		return carga_horaria_pratica + carga_horaria_teorica;
	}
	
	public void setPreReq(List<String> codes) {
		this.pre_req_codes = new HashSet<String>(codes);
	}

	public void addPreReq(String code) {
		if (pre_req_codes == null)
			pre_req_codes = new HashSet<>(3);
		this.pre_req_codes.add(code);
	}

	static public Disciplina disciplinaFromCode(String code) {
		Disciplina d = Disciplina.code_to_disc.get(code);
		if (d == null) {
			Disciplina.getAll();
		}
		d = Disciplina.code_to_disc.get(code);
		return d;
	}

	@Override
	public String toString() {
		return String.format("%-10s %1s %-3s %1s %-50s", this.code,"", Integer.toString(this.getCargaHoraria()),"", this.name);
	}
	
	public String getCode() {
		return this.code;
	}

	public String getDepartamentoCode() {
		String dept_code = this.code.replaceAll("\\d", "");
		switch (dept_code) {
			case "COMP":
				return "DCOMP";
			case "MAT":
				return "DMA";
			case "FISI":
				return "DFI";
			case "ESTAT":
				return "DECAT";
			default:
				return "";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.code.equals( ((Disciplina)obj).getCode() );
	}
}
