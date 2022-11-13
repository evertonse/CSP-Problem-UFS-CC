package ufs.cc;

public class Estudante {
	
	Disciplina[] disciplinas_cursadas;
	
	boolean PIBIC,PIBITI,ESTAGIO;

	float horas_disponiveis_diarias;
	float horas_semanais_por_carga_horaria_total;
	
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
		this.horas_disponiveis_diarias = 14.0f; 
		if(PIBIC) {
			this.horas_disponiveis_diarias -= 2.0f;
		} 
		if(ESTAGIO) {
			this.horas_disponiveis_diarias -= 4.5f;
		} 
		if(PIBITI) {
			this.horas_disponiveis_diarias -= 1.0f;
		} 
		setHorasSemanaisExtraPorCargaHoraria(0.0f,15);
	}

	public int cargaHorariaMax() {
		float ch_max = (this.horas_disponiveis_diarias*5.0f)/this.horas_semanais_por_carga_horaria_total;
		return Math.round(ch_max);	
	}
	
	public void setHorasDeViagemIda(float horas) {
		this.horas_disponiveis_diarias -= horas;
	}
	
	public void setHorasDeViagemVolta(float horas) {
		this.horas_disponiveis_diarias -= horas;
	}

	public void setHorasSemanaisExtraPorCargaHoraria(float horas_extra,float carga_horaria) {
		this.horas_semanais_por_carga_horaria_total = (1.0f+horas_extra)/carga_horaria;
	}


	public Disciplina[] getDisciplinasCursadas(){
		return this.disciplinas_cursadas;
	}

	// retorna hora disponiveis por dia
	public float getHorasDisponiveis() {
		return this.horas_disponiveis_diarias;
	}

	@Override
	public String toString() {
		return String.format("\n>>Aluno: \nPIBIC:%b ESTAGIO:%b PIBITI:%b\n" +
		"Horas Disponiveis para Estudar = %f\n" + 
		"Horas de Estudo Diario (incluso horario de aula + estudo fora de aula) por Carga Horária = %f\n"+
		"<<", 
			this.PIBIC,this.ESTAGIO, this.PIBITI,
			this.getHorasDisponiveis(), this.horas_semanais_por_carga_horaria_total
		);
	}
}	
