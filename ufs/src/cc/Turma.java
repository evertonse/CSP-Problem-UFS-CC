package cc;
import cc.Horario;

public class Turma {
	Disciplina disc;
	Horario[] horarios;
	String professor;

	public Turma(Disciplina disc, Horario[] horarios, String professor) {
    this.disc = disc;
    this.horarios = horarios;
    this.professor = professor;
	}
	static public Turma[] offertas() {

	}
}