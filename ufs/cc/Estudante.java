package ufs.cc;

public class Estudante {
	
	Disciplina[] disciplinas_cursadas;
	
	boolean PIBIC,PIBITI,ESTAGIO;

	float HORAS_POR_CR;
	
	public Horario.Turno turno;

	public Estudante(
		Disciplina[] disciplinas_cursadas,
		Horario.Turno turno,
		boolean PIBIC,
    boolean PIBITI,
    boolean ESTAGIO
	)
	{
		this.disciplinas_cursadas = disciplinas_cursadas;
		this.turno = turno;
		this.PIBIC = PIBIC;
		this.PIBITI = PIBITI;
		this.ESTAGIO =  ESTAGIO;
	}
		
	public void setHorasPorCredito(int horas_de_estudo, int credito) {
		HORAS_POR_CR = horas_de_estudo/credito;
	}


	public Disciplina[] getDisciplinasCursadas() {
		return this.disciplinas_cursadas;
	}
}	
