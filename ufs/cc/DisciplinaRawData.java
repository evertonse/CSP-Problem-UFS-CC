package ufs.cc;


public class  DisciplinaRawData {
	
	static public char getSeparatorChar() { return ','; };

	static public String getLayout(int periodo) { 
		return "Código,Componente Curricular,Tipo,CR,CH Total,CH Teórico,CH Prática,Pré-Requisito";
	}
	
	static public String[] getRawPeriodo(int periodo) {
		if (periodo == 1)
		return new String[]
		{
			"MAT0151,Cálculo A,Disc.,04,60,60,0,-",
			"MAT0150,Vetores e Geometria Analítica,Disc,04,60,60,0,-",
			"MAT0057,Fundamentos Elementares dade Matemática,Disc.,04,60,60,0,-",
			"COMP0480,Seminários em Computação,Disc.,02,30,30,0,- ",
			"COMP0393,Programação Funcional**,Disc.,04,60,30,30,-"
		};
		else if (periodo == 2)
		return new String[]
		{
			"MAT0152,Cálculo B,Disc.,04,60,60,0,MAT0151(PRO)",
			"MAT0078,Álgebra Linear I,Disc.,04,60,60,0,MAT0150(PRO)",
			"FISI0260,Física 1,Disc.,04,60,45,15,MAT0151(PRO);MAT0150(PRO)",
			"FISI0264,Laboratório de Física1*,Disc.,02,30,0,30,MAT0151(PRO)",
			"COMP0410,Lógica para Computação,Disc.,04,60,45,15,MAT0057(PRO)",
			"COMP0334,Programação Imperativa**,Disc.,04,60,30,30,-"
		};
		else if (periodo == 3)
		return new String[]
		{
			"MAT0153,Cálculo C,Disc.,04,60,60,0,MAT0152(PRO)",
			"COMP0416,Fundamentos de Sistemas Digitais,Disc.,04,60,45,15,MAT0057(PRO);COMP0334(PRO)",
			"COMP0419,Prática em Sistemas Digitais*,Disc.,02,30,0,30,MAT0057(PRO);COMP0334(PRO)",
			"COMP0395,Programação Orientada a Objetos**,Disc.,04,60,30,30,COMP0334(PRO)",
			"COMP0405,Estruturas de Dados**,Disc.,04,60,30,30,MAT0152(PRO);COMP0334(PRO);COMP0393(PRO)",
			"COMP0409,Linguagens Formais e Computabilidade,Disc.,04,60,45,15,COMP0410(PRO);COMP0393(PRO)",
		};
		
		else if (periodo == 4)
		return new String[]
					{
			"ESTAT0011,Estatística Aplicada,Disc.,04,60,60,0,-",
			"COMP0481,Métodos e Técnicas de Pesquisa para Computação**,Disc.,02,30,30,0,COMP0480(PRO)",
			"COMP0412,Projeto e Análise de Algoritmos**,Disc.,04,60,30,30,MAT0057(PRO);COMP0405(PRO)",
			"COMP0408,Grafos e Algoritmos Computacionais**,Disc.,04,60,30,30,COMP0410(PRO);COMP0405(PRO)",
			"COMP0417,Fundamentos de Sistemas Embarcados,Disc.,02,30,15,15,COMP0334(PRO)",
			"COMP0415,Arquitetura de Computadores,Disc.,04,60,30,30,COMP0334(PRO);COMP0416(PRR);COMP0419(PRR)",
		};
		
		else if (periodo == 5)
		return new String[]
		{
			"COMP0478,Informática Ética e Sociedade,Disc.,04,60,60,0,-",
			"COMP0397,Programação Paralela e Concorrente**,Disc.,04,60,30,30,COMP0415(PRO);COMP0405(PRO)",
			"COMP0438,Engenharia de Software I,Disc.,04,60,30,30,COMP0395(PRO)",
			"COMP0461,Redes de Computadores,Disc.,04,60,30,30,COMP0415(PRO)",
			"COMP0455,Banco de Dados I,Disc.,04,60,30,30,COMP0405(PRO)",
			"COMP0472,Sistemas Operacionais,Disc.,04,60,30,30,COMP0405(PRO);COMP0415(PRO)",
		};
		
		else if (periodo == 6)
		return new String[]
		{
			"COMP0463,Laboratório de Redes de Computadores*,Disc.,02,30,0,30,COMP0461(PRO)",
			"COMP0439,Engenharia de Software II,Disc.,04,60,30,30,COMP0438(PRO)",
			"COMP0443,Interface Humano Computador,Disc.,04,60,60,0,COMP0395(PRO)",
			"COMP0470,Sistemas Distribuídos,Disc.,04,60,30,30,COMP0461(PRO);COMP0472(PRO);COMP0395(PRO)",
			"COMP0432,Processamento de Imagens,Disc.,04,60,30,30,MAT0150(PRO);COMP0334(PRO);MAT0078(PRR);ESTAT0011(PRR)",
			"COMP0427,Inteligência Artificial,Disc.,04,60,30,30,ESTAT0011(PRO);COMP0408(PRO)",
		};
		
		else if (periodo == 7)
		return new String[]
		{
			"COMP0483,Prática Orientada em Computação I*,Disc.,12,180,30,150,COMP0438(PRO);COMP0455(PRO);COMP0461(PRO)",
			"COMP0391,Compiladores,Disc.,4,60,30,30,COMP0395(PRO);COMP0409(PRO)",
		};
		
		
		else if (periodo == 8)
		return new String[]
		{
			"COMP0484,Prática Orientada em Computação II *,Disc.,08,120,30,90,COMP0483(PRO)",
			"COMP0485,Trabalho de Conclusão de Curso I,Ativ.,-,60,0,60,COMP0438(PRO);COMP0455(PRO);COMP0461(PRO);COMP0481(PRO)"
		};

		else if (periodo == 9)
		return new String[]
		{
			"COMP0486,Trabalho de Conclusão de Curso II,Ativ.,-,120,0,120,COMP0485(PRO)",
			"COMP0482,Estágio Supervisionado em Computação *,Ativ.,-,210,0,210,COMP0438(PRO);COMP0455(PRO);COMP0461(PRO)",
			"COMP0308,Atividades Complementares,Ativ.,8,120,-,-,-",
		};
    return null;
	}

	static public String[] getRawLinguagensDeProgramacao() {
		return new String[]
		{
			"COMP0392,Paradigmas de Linguagens de Programação,Disc.,04,60,30,30,COMP0395(PRO);COMP0393(PRO);COMP0409(PRO)",
			"COMP0398,Programação para Web**,Disc.,04,60,30,30,COMP0395(PRO);COMP0455(PRO);COMP0461(PRO)"

		};
	}
		

	static public String[] getRawComputacaoTeoricaAlgoritmos() {
		return new String[]
		{
			"MAT0096,Cálculo Numérico I,Disc.,04,60,60,0,MAT0151(PRO);COMP0334(PRO)",
			"COMP0406,Estruturas de Dados Avançadas,Disc.,04,60,30,30,COMP0405(PRO)",
		};
	}
	
	static public String[] getRawInteligenciaArtificial() {
		return new String[]
		{
			"COMP0424,Aprendizagem de Máquina,Disc.,04,60,30,30,COMP0427(PRO)",
			"COMP0423,Aplicações de Inteligência Artificial,Disc.,04,60,30,30,COMP0424(PRO)",
		};
	}
	
	static public String[] getRawProcessamentoDeImagensComputacaoGrafica() {
		return new String[]
		{
			"COMP0431,Computação Gráfica,Disc.,04,60,30,30,MAT0152(PRO);COMP0395(PRO);COMP0405(PRR);MAT0153(PRR)",
			"COMP0435,Visão Computacional,Disc.,04,60,30,30,COMP0432(PRO)",
		};
	}
	
	static public String[] getRawEngenhariaDeSoftware() {
		return new String[]
		{
			"COMP0444,Teste de Software,Disc.,04,60,30,30,COMP0439(PRO)",
			"COMP0441,Evolução de Software,Disc.,04,60,30,30,COMP0439(PRO)"
		};
	}
	
	static public String[] getRedesDeComputadores() {
		return new String[]
		{
			"COMP0455,Banco de Dados II,Disc.,04,60,30,30,COMP0455(PRO)",
			"COMP0459,Mineração de Dados,Disc.,04,60,30,30,COMP0455(PRO); COMP0427(PRO)"
		};
	}
	static public String[] getRawBancoDeDados() {
		return new String[]
		{
			"COMP0464,Infraestrutura de Redes de Computadores,Disc.,04,60,30,30,COMP0461(PRO)",
			"COMP0462,Redes Móveis e Sem Fio,Disc.,04,60,30,30,COMP0461(PRO)"
		};
	}
	
	static public String[] getRawComputaçãoDistribuida() {
		return new String[]
		{
			"COMP0469,Computação em Nuvem,Disc.,04,60,30,30,COMP0470(PRO)",
			"COMP0468,Algoritmos Distribuídos,Disc.,04,60,45,15,COMP0470(PRO)"
		};
	}
	
	static public String[] getRawHardware() {
		return new String[]
		{
			"COMP0420,Sistemas de Hardware/Software I,Disc.,04,60,30,30,COMP0415(PRO)",
			"COMP0421,Sistemas de Hardware/Software II,Disc.,04,60,15,45,COMP0420(PRO)"
		};
	}
}	
/*
// Código Componente,Curricular,Tipo,CR,CH Total,CH Teórico,CH Prática Pré-Requisito
// Perfil:Básico 
// Periodo:1
MAT0152,Cálculo B,Disc.,04,60,60,0,MAT0151(PRO)
MAT0078,Álgebra Linear I,Disc.,04,60,60,0,MAT0150(PRO)
FISI0260,Física 1,Disc.,04,60,45,15,MAT0151(PRO);MAT0150(PRO)
FISI0264,Laboratório de Física1*,Disc.,02,30,0,30,MAT0151(PRO)
COMP0410,Lógica para Computação,Disc.,04,60,45,15,MAT0057(PRO)
COMP0334,Programação Imperativa**,Disc.,04,60,30,30,-
>> 2º Período
MAT0152,Cálculo B,Disc.,04,60,60,0,MAT0151(PRO)
MAT0078,Álgebra Linear I,Disc.,04,60,60,0,MAT0150(PRO)
FISI0260,Física 1,Disc.,04,60,45,15,MAT0151(PRO);MAT0150(PRO)
FISI0264,Laboratório de Física1*,Disc.,02,30,0,30,MAT0151(PRO)
COMP0410,Lógica para Computação,Disc.,04,60,45,15,MAT0057(PRO)
COMP0334,Programação Imperativa**,Disc.,04,60,30,30,-
>> 3º Período
MAT0153,Cálculo C,Disc.,04,60,60,0,MAT0152(PRO)
COMP0416,Fundamentos de Sistemas Digitais,Disc.,04,60,45,15,MAT0057(PRO);COMP0334(PRO)
COMP0419,Prática em Sistemas Digitais*,Disc.,02,30,0,30,MAT0057(PRO);COMP0334(PRO)
COMP0395,Programação Orientada a Objetos**,Disc.,04,60,30,30,COMP0334(PRO)
COMP0405,Estruturas de Dados** Disc. 04 60 30 30 MAT0152(PRO);COMP0334(PRO);COMP0393(PRO)
COMP0409,Linguagens Formais e Computabilidade,Disc.,04,60,45,15,COMP0410(PRO);COMP0393(PRO)
>> 4º Período
ESTAT0011,Estatística Aplicada,Disc.,04,60,60,0,-
COMP0481,Métodos e Técnicas de Pesquisa para Computação**,Disc.,02,30,30,0,COMP0480(PRO)
COMP0412,Projeto e Análise de Algoritmos**,Disc.,04,60,30,30,MAT0057(PRO);COMP0405(PRO)
COMP0408,Grafos e Algoritmos Computacionais**,Disc.,04,60,30,30,COMP0410(PRO);COMP0405(PRO)
COMP0417,Fundamentos de Sistemas Embarcados,Disc.,02,30,15,15,COMP0334(PRO)
COMP0415,Arquitetura de Computadores,Disc.,04,60,30,30,COMP0334(PRO);COMP0416(PRR);COMP0419(PRR)
>> 5º Período
COMP0478,Informática, Ética e Sociedade,Disc.,04,60,60,0,-
COMP0397,Programação Paralela e Concorrente**,Disc.,04,60,30,30,COMP0415(PRO);COMP0405(PRO)
COMP0438,Engenharia de Software I,Disc.,04,60,30,30,COMP0395(PRO)
COMP0461,Redes de Computadores,Disc.,04,60,30,30,COMP0415(PRO)
COMP0455,Banco de Dados I,Disc.,04,60,30,30,COMP0405(PRO)
COMP0472,Sistemas Operacionais,Disc.,04,60,30,30,COMP0405(PRO);COMP0415(PRO)
>> 6º Período
COMP0463,Laboratório de Redes de Computadores*,Disc.,02,30,0,30,COMP0461(PRO)
COMP0439,Engenharia de Software II,Disc.,04,60,30,30,COMP0438(PRO)
COMP0443,Interface Humano Computador,Disc.,04,60,60,0,COMP0395(PRO)
COMP0470,Sistemas Distribuídos,Disc.,04,60,30,30,COMP0461(PRO);COMP0472(PRO);COMP0395(PRO)
COMP0432,Processamento de Imagens,Disc.,04,60,30,30,MAT0150(PRO);COMP0334(PRO);MAT0078(PRR);ESTAT0011(PRR)
COMP0427,Inteligência Artificial,Disc.,04,60,30,30,ESTAT0011(PRO);COMP0408(PRO)
>> 7º Período
COMP0 483,Prática Orientada em Computação I*,Disc.,12,180,30,150,COMP0438(PRO);COMP0455(PRO);COMP0461(PRO)
COMP0391,Compiladores,Disc.,4,60,30,30,COMP0395(PRO);COMP0409(PRO)
>> 8º Período
COMP0484,Prática Orientada em Computação II *,Disc.,08,120,30,90,COMP0483(PRO)
COMP0485,Trabalho de Conclusão de Curso I,Ativ.,-,60,0,60,COMP0438(PRO);COMP0455(PRO);COMP0461(PRO);COMP0481(PRO)
>> 9º Período
COMP0486,Trabalho de Conclusão de Curso II,Ativ.,-,120,0,120,COMP0485(PRO)
COMP0482,Estágio Supervisionado em Computação *,Ativ.,-,210,0,210,COMP0438(PRO);COMP0455(PRO);COMP0461(PRO)
COMP0308,Atividades Complementares,Ativ,8,120,-,-,- 
>> Perfil:Linguagens de Programação
COMP0392,Paradigmas de Linguagens de Programação,04,60,30,30,COMP0395(PRO);COMP0393(PRO);COMP0409(PRO)
COMP0398,Programação para Web**,04,60,30,30,COMP0395(PRO);COMP0455(PRO);COMP0461(PRO)
>> Perfil:Computação Teórica e Algoritmos
MAT0096,Cálculo Numérico I,04,60,60,0,MAT0151(PRO);COMP0334(PRO)
COMP0406,Estruturas de Dados Avançadas,04,60,30,30,COMP0405(PRO)
>> Perfil:Inteligência Artificial
COMP0424,Aprendizagem de Máquina,04,60,30,30,COMP0427(PRO)
COMP0423,Aplicações de Inteligência Artificial,04,60,30,30,COMP0424(PRO)
>> Perfil:Processamento de Imagens e Computação Gráfica
COMP0431,Computação Gráfica,04,60,30,30,MAT0152(PRO);COMP0395(PRO);COMP0405(PRR);MAT0153(PRR)
COMP0435,Visão Computacional,04,60,30,30,COMP0432(PRO)
>> Perfil:Engenharia de Software
COMP0444,Teste de Software,04,60,30,30,COMP0439(PRO)
COMP0441,Evolução de Software,04,60,30,30,COMP0439(PRO)
>> Perfil:Banco de Dados
COMP0455,Banco de Dados II,04,60,30,30,COMP0455(PRO)
COMP0459,Mineração de Dados,04,60,30,30,COMP0455(PRO); COMP0427(PRO)
>> Perfil:Redes de Computadores
COMP0464,Infraestrutura de Redes de Computadores 04 60 30 30 COMP0461(PRO)
COMP0462,Redes Móveis e Sem Fio,04,60,30,30,COMP0461(PRO)
>> Perfil:Computação Distribuída
COMP0469,Computação em Nuvem,04,60,30,30,COMP0470(PRO)
COMP0468,Algoritmos Distribuídos,04,60,45,15,COMP0470(PRO)
> Perfil:Hardware
COMP0420,Sistemas de Hardware/Software I,04,60,30,30,COMP0415(PRO)
COMP0421,Sistemas de Hardware/Software II,04,60,15,45,COMP0420(PRO)
# Curriculares Ofertados pelo Departamento de Computação (DCOMP)
COMP0477,Informática Educativa,04,60,60,0,-
COMP0394,Programação Orientada a Aspectos,02,30,15,15,COMP0395(PRO)
COMP0396,Programação para Dispositivos Móveis**,04,60,30,30,COMP0395(PRO);COMP0455(PRO);COMP0461(PRO)
COMP0403,Desafios de Programação I,04,60,15,45,COMP0334(PRO)
COMP0404,Desafios de Programação II,04,60,30,30,COMP0405(PRO)
COMP0411,Programação Inteira,04,60,30,30,MAT0078(PRO);COMP0412(PRO);COMP0408(PRR)
COMP0407,Geometria Computacional,04,60,30,30,COMP0412(PRO);COMP0408(PRR)
COMP0401,Complexidade Computacional,04,60,45,15,COMP0408(PRO);COMP0409(PRO)
COMP0400,Algoritmos Criptográficos,04,60,30,30,COMP0412(PRO);COMP0408(PRR)
COMP0429,Sistemas Multiagentes,04,60,30,30,COMP0427(PRO)
COMP0428,Processamento de Linguagem Natural,04,60,30,30,COMP0427(PRO)
COMP0426,Computação Natural,04,60,30,30,COMP0427(PRO)
COMP0425,Computação Afetiva,04,60,45,15,COMP0427(PRO);COMP0443(PRO)
COMP0434,Renderização Realística,04,60,30,30,COMP0431(PRO)
COMP0433,Processamento de Imagens Médicas,04,60,30,30,COMP0432(PRO)
COMP028,Qualidade de Software,04,60,30,30,COMP0439(PRO)4
COMP0442,Gerência de Projetos,04,60,30,30,COMP0438(PRO)
COMP0440,Especificação Formal de Sistemas Críticos,04,60,45,15,COMP0438(PRO);COMP0397(PRO)
COMP0413,Semântica Formal,04,60,45,15,COMP0392(PRO)
COMP0458,Data Warehousing,04,60,30,30,COMP0455(PRO)
COMP0457,Banco de Dados Distribuídos,04,60,30,30,COMP0455(PRO);COMP0470(PRO)
COMP0465,Segurança de Redes de Computadores,04,60,30,30,COMP0461(PRO)
COMP0466,Avaliação de Desempenho de Sistemas,04,60,30,30,COMP0461(PRO);ESTAT0011(PRO)
COMP0402,Computação Musical,04,60,30,30,COMP0412(PRO)
COMP0474,Tolerância a Falhas,04,60,45,15,COMP0470(PRO)
COMP0471,Sistemas Multimídia Distribuídos,04,60,45,15,COMP0470(PRO)
COMP0473,Sistemas de Tempo Real,04,60,45,15,COMP0470(PRO)
COMP0418,Interface Hardware/Software,04,60,30,30,COMP0395(PRO)
COMP0452,Sistemas de Informação Empresarial,04,60,30,30,COMP0455(PRO);COMP0449(PRR)
COMP0450,Planejamento Estratégico de TIC,04,60,15,45,COMP0438(PRO)
COMP0451,Sistemas de Apoio à Decisão,04,60,60,0,COMP0438(PRO);COMP0455(PRO)
COMP0339,Tópicos Especiais em Linguagens de Programação,04,60,60,0,-
COMP0414,Tópicos Especiais em Computação Teórica e Algoritmos,04,60,60,0,-
COMP0422,Tópicos Especiais em Hardware,04,60,60,0,-
COMP0430,Tópicos Especiais em Inteligência Artificial,04,60,60,0,- 
COMP0436,Tópicos Especiais em Processamento de Imagens,04,60,60,0,-
COMP0437,Tópicos Especiais em Computação Gráfica,04,60,60,0,-
COMP0445,Tópicos Especiais em Engenharia de Software,04,60,60,0,-
COMP0454,Tópicos Especiais em Sistemas de Informação,04,60,60,0,-
COMP0460,Tópicos Especiais em Banco de Dados,04,60,60,0,-
COMP0467,Tópicos Especiais em Redes de Computadores,04,60,60,0,-
COMP0475,Tópicos Especiais em Computação Distribuída,04,60,60,0,-
COMP0479,Tópicos Especiais em Informática Educativa,04,60,60,0,-
# Componentes Curriculares Ofertados por Outros Departamentos
ADM0002 Introdução à Administração,04,60,60,0,-
DIRE0323,Direito e Legislação Social,04,60,60,0,-
DIRE0065,Legislação em Informática,04,60,60,0,-
DIRE0219,Sociologia Geral e Jurídica,04,60,60,0,-
ECONO0150,Economia, Meio Ambiente e Sustentabilidade,04,60,60,0,-
LETRL0034,Língua Brasileira deSinais,04,60,60,0,-
EPROD0087,Introdução e Laboratório de Propriedade Intelectual,04,60,30,30,-
EPROD0098,Gestão da Inovação,04,60,60,0,-
ESTAT0070,Análise Exploratória de Dados,04,60,45,15,-
ESTAT0072,Probabilidade I,04,60,45,15,-
FILO0018,Introdução à Filosofia,04,60,60,0,-
FILO0068,Tópicos Especiais em Ética,04,60,60,0,-
FISI0262,Física 3 04 60 45 15 FISI0260(PRO)
LETR0429,Inglês Instrumental,04,60,30,30,-
MAT0118,Álgebra Linear Computacional,04,60,60,0,MAT0078(PRO)
MAT0125,Introdução à Teoria dos Jogos,04,60,60,0,MAT0078(PRO)
# Monitoria
DAA0006,Monitoria I,02,30,0,0,-
DAA0007,Monitoria II,02,30,0,0,-
DAA0008,Monitoria III,02,30,0,0,-
DAA0009,Monitoria IV,02,30,0,0,- 
# Atividades Complementares Optativas
COMP0291,Atividades Complementares,-,30,-,30,-
*/