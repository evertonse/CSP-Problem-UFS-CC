package ufs.cc;

public class Estudante {
	
	Disciplina[] disciplinas_cursadas;
	
	boolean PIBIC,PIBITI,ESTAGIO;

	float HORAS_POR_CR;
	
	public Estudante(
		Disciplina[] disciplinas_cursadas,
		boolean PIBIC,
    boolean PIBITI,
    boolean ESTAGIO
	)
	{
		this.disciplinas_cursadas = disciplinas_cursadas;
		this.PIBIC = PIBIC;
		this.PIBITI = PIBITI;
		this.ESTAGIO =  ESTAGIO;
	}
		
	public void setHorasPorCredito(int horas_de_estudo, int credito) {
		HORAS_POR_CR = horas_de_estudo/credito;
	}

}	
