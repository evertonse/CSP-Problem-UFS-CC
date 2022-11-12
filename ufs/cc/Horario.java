package ufs.cc;

public class Horario {
		public enum Dia {
			Seg,
			Ter,
			Qua,
      Qui,
      Sex,
		}
		public enum Turno {
			Vespestino,
			Matunino,
			Noturno,
		}

		Turno turno;
		Dia dia;
    int hora; // 1-3
		
		public Horario(Turno turno, Dia dia, int horario){
			this.turno = turno;
      this.dia = dia;
			if (turno == Turno.Noturno && hora > 1) {
				throw new IllegalArgumentException("Turno Noturno só possue horario 1 e 2");
			}
			if (hora > 2){
				throw new IllegalArgumentException("Horario deve ser entre 1 e 3");
			}
      this.hora = horario;
		}
		public Horario(int turno, int dia, int horario) {
			this.turno = Turno.values()[turno];
      this.dia = Dia.values()[dia];
			if (this.turno == Turno.Noturno && hora > 1) {
				throw new IllegalArgumentException("Turno Noturno só possue horario 0 e 1");
			}
			if (hora > 2){
				throw new IllegalArgumentException("Horario deve ser entre 0 e 2");
			}
      this.hora = horario;
		}

		@Override
		public String toString() {
			int start_h = + 2*this.hora,start_min = 0, end_h = 0,end_min = 0;
			switch (this.turno) {
				case Noturno:
          start_h += 18;
          start_min = 50;
          break;
				case Vespestino:
					start_h += 13;
          start_min = 0 ;
					break;
				case Matunino:
					start_h += 7;
          start_min = 0;
					break;
			}
			end_h =start_h + 1; end_min = 50;
			if(this.turno == Turno.Noturno){
				end_h =start_h + 2; end_min = 30;

			}
			String start_horario = String.format(
				"%2s:%2s", 
				String.valueOf(start_h),String.valueOf(start_min)
			).replace(" ", "0");;
			
			String end_horario = String.format(
				"%2s:%2s", 
				String.valueOf(end_h),String.valueOf(end_min)
			).replace(" ", "0");
			
			return diaToString(this.dia) + " " + start_horario + "-" + end_horario;

	}
	static public String diaToString(Dia dia){
		switch (dia) {
			case Seg:
				return "Seg";
			case Ter:
				return "Ter";
			case Qua:
				return "Qua";
			case Qui:
				return "Qui";
			case Sex:
				return "Sex";
			default:
				return "Dia Invalido";
		}	
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		
		if( 
				 ((Horario)obj).dia 	== this.dia 
			&& ((Horario)obj).turno == this.turno
			&& ((Horario)obj).hora 	== this.hora
		) {
			return true;
		}
		return false;
	}
}