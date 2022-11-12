package ufs.cc;

import java.util.ArrayList;
import java.util.Random;

public class Turma {
	static final String PERIODO = "2022.2";

	Disciplina disc;
	Horario[] horarios;
	String professor;

	public Turma(Disciplina disc, Horario[] horarios, String professor) {
    this.disc = disc;
    this.horarios = horarios;
    this.professor = professor;
	}
	
	
	static public Turma getTurmaVazia() {
		Disciplina d = new Disciplina("VAZIA","Disciplina Nula",0,0,true,-1,Disciplina.Perfil.Basico);
		return new Turma(d, null, "");
	}
	
	public boolean isTurmaVazia() {
		return this.disc.getCode().equals("VAZIA");
	}

	static public Turma[] 
	getOfertas(int n_turmas, Horario.Turno turno) {
		if (turno == Horario.Turno.Matunino) {
			return getOfertas(n_turmas, 0);
		}
		if (turno == Horario.Turno.Vespestino) {
			return getOfertas(n_turmas, 1);
		}
		else{
			return getOfertas(n_turmas, 2);
		}
	}

	static public Turma[] 
	getOfertas(int n_turmas, int turno) {
		Random rd = new Random();
		ArrayList<Turma> turmas = new ArrayList<Turma>(n_turmas);
		String[] professores = new String[]{
			"Leonardo Nogueira", 
			"Evilson", 
			"Estombelo", 
			"Rafael",
			"Alberto Costa",
			"Beatriz Trinchao",
			"Bruno Otavio",
			"Tarcisio Rocha",
			"Rene Pereira"
		};

		Disciplina[] disciplinas = Disciplina.getAll().toArray(new Disciplina[ Disciplina.getAll().size()]);
		
		for (int i = 0; i < n_turmas; i++) {
			int prof 	= rd.nextInt(0, professores.length);
			int disc 	= rd.nextInt(0, disciplinas.length);
			int hora 	= rd.nextInt(0,3);
			int cr 		= disciplinas[disc].getCreditos();
			Horario[] horarios;
			
			assert(cr > 0);
			
			// Pode ser qualquer dia da semana
			if (cr == 2) { 
				int dia = rd.nextInt(5);
				horarios = new Horario[1];
				horarios[0] = new Horario(turno, dia,hora);
			}
			// Deve ser ou seg-qua. ou ter-qui ou tudo na sex.
			else if (cr == 4) { 
				horarios = new Horario[2];
				
				int dia = rd.nextInt(0,3);
				// tudo na sexta
				if (dia == 2 ){
					horarios[0] = new Horario(turno, 4,1);
					horarios[1] = new Horario(turno, 4,2);
					
				}
				else{
					horarios[0] = new Horario(turno, dia,hora);
					horarios[1] = new Horario(turno, dia+2,hora);
				}
			}
			// Deve segunda-qua-sex
			else if (cr == 6) { 
				horarios = new Horario[3];
				horarios[0] = new Horario(turno, 0, hora);
				horarios[1] = new Horario(turno, 2, hora);
				horarios[2] = new Horario(turno, 4, hora);
			}
			// Deve segunda-qua-sex
			else if (cr == 8) { 
				horarios = new Horario[4];
				horarios[0] = new Horario(turno, 0, hora);
				horarios[1] = new Horario(turno, 1, hora);
				horarios[2] = new Horario(turno, 2, hora);
				horarios[3] = new Horario(turno, 3, hora);
			}
			// Deve segunda-qua-sex
			else if (cr == 12) { 
				horarios = new Horario[2];
				int dia = rd.nextInt(0,4);
				
				horarios[0] = new Horario(turno, dia, hora);
				horarios[1] = new Horario(turno, dia+1, hora);
			}
			else {
				i = i-1;
				continue;
				// turmas[i] = turmas[i-1]; continue;
				// throw new UnsupportedOperationException("creditos = "+ String.valueOf(cr)+"\n Disciplina = " + disciplinas[disc] +"\nNão pensei sobre esse caso Ainda");
			} 
			turmas.add(new Turma(disciplinas[disc],horarios, professores[prof]));
		}
		for (Turma t : turmas) {
			for (Horario horario : t.getHorarios()) {
				if(horario == null) {
					System.out.println("ALERT HORARIO is null quando em Ofertas de Turma");
				}
			}
		}
		return turmas.toArray(new Turma[turmas.size()]);
	}

	public Disciplina getDisciplina() {
		return this.disc;
	}
	
	public Horario[] getHorarios() {
		return this.horarios;
	}
	
	public boolean conflita(Turma t2) {
		if (t2 == null) {
      return false;
    }
		if (this.isTurmaVazia() || t2.isTurmaVazia()){
			return false;
		}
		
		if(this.getDisciplina().getCode().equals(t2.getDisciplina().getCode())){
			return true;
		}

		Horario[] h1 = this.getHorarios();
		Horario[] h2 = t2.getHorarios();

		if (h1 == null){
			System.out.println("h1 is null");
			return false;
		}
		if (h2 == null){
			System.out.println("h2 is null");
			return false;
		}

		for (int i = 0; i < h1.length; i++) {
			for (int j = 0; j < h2.length; j++) {
				if (h1[i] == null) {
					System.out.println("NULL ALERT h1[i] == null" + this.toString());
					continue;
				}
				if (h2[j] == null) {
					System.out.println("NULL ALERT h2[j] == null" + t2.toString());
					continue;
				}
				// se pelo menos 1 horario conflitar então as duas turmas conflitam
				if (h1[i].equals(h2[j])) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Turma.PERIODO + " ");
		sb.append(disc.toString() + " ");

		if (this.horarios != null) {
			for(Horario h: this.horarios) {
				if (h == null){
					continue;
				}
				sb.append(h.toString() + " ");
			}
		}
		sb.append(this.professor);
		return sb.toString();
	}

	
}