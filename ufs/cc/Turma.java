package ufs.cc;

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

	static public Turma[] 
	getOfertas(int n_turmas, int turno) {
		Random rd = new Random();
		Turma[] turmas = new Turma[n_turmas];
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
			int hora = rd.nextInt(0,3);
			int cr = disciplinas[disc].getCreditos();
			Horario[] horarios = new Horario[cr/2];
			// Pode ser qualquer dia da semana
			if (cr == 2) { 
				int dia = rd.nextInt(5);
				horarios[0] = new Horario(turno, dia,hora);
			}
			// Deve ser ou seg-qua. ou ter-qui
			else if (cr == 4) { 
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
				horarios[0] = new Horario(turno, 0, hora);
				horarios[1] = new Horario(turno, 2, hora);
				horarios[2] = new Horario(turno, 4, hora);
			}
			// Deve segunda-qua-sex
			else if (cr == 8) { 
				horarios[0] = new Horario(turno, 0, hora);
				horarios[1] = new Horario(turno, 1, hora);
				horarios[2] = new Horario(turno, 2, hora);
				horarios[3] = new Horario(turno, 3, hora);
			}
			// Deve segunda-qua-sex
			else if (cr == 12) { 
				horarios[0] = new Horario(turno, 0, hora);
				horarios[1] = new Horario(turno, 1, hora);
				horarios[2] = new Horario(turno, 2, hora);
				horarios[3] = new Horario(turno, 3, hora);
				horarios[4] = new Horario(turno, 4, hora);
			}
			else {
				i = i-1;
				continue;
				// turmas[i] = turmas[i-1]; continue;
				// throw new UnsupportedOperationException("creditos = "+ String.valueOf(cr)+"\n Disciplina = " + disciplinas[disc] +"\nNão pensei sobre esse caso Ainda");
			} 

			for (Horario horario : horarios) {
				assert(horario != null);
			}
			turmas[i] = new Turma(disciplinas[disc],horarios, professores[prof]);
		}
		return turmas;
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
		for (Horario horario : this.getHorarios()) {
			for (Horario horario2 : t2.getHorarios()) {
				if (horario == null && horario2 == null) {
					System.out.println("NULL ALERT" + this.toString() + t2.toString());
        }
				// se pelo menos 1 horario conflitar então as duas turmas conflitam
				if (horario.equals(horario2)) {
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

		for(Horario h: this.horarios) {
			if (h == null){
				continue;
			}
			sb.append(h.toString() + " ");
		}
		sb.append(this.professor);
		return sb.toString();
	}

	
}