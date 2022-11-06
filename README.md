
# Como Compilar e Rodar

### Requisitos, obs: todos os programas abaixo devem estar configurados no PATH
- `python 3`
- possuir `javac` e `java` no PATH
- `git` 

### Primeiramente, clone esse repo. Todos os comandos a seguir em (Opção 1,2,3) devem ser feitos NESTE diretório, onde a repo foi clonada

		$ git clone https://github.com/evertonse/CSP-Problem-UFS-CC.git

## Opção 1:
- Rode `compile.py` apenas de dizer compile, ele comipla e roda todo o projeto java
			
		$ compile.py

## Opção 2:
- Rode o comando abaixo para compilar:

		$ javac -classpath "./build/" -sourcepath "./" -d "./build/" @javafiles.args

- Opcionalmente para compilar rode essa versão menor
		
		$ javac -d "./build/" @javafiles.args

- Rode o comando abaixo para iniciar o programa a partir da classe `Main` do projeto
	
		$ java -cp "./build/" Main

## Opção 3 (caso esteja faltando o código do AIMA):

- Clone a lib AIMA na versão 4 pelo comando abaixo, você deve estar na `directory do projeto`.

		$ git clone --recursive https://github.com/aimacode/aima-java.git --branch AIMA4e

- Opcionalmente Se tiver `git 1.7.10` ou mais:

		$ git clone --recursive  --single-branch --branch AIMA4e https://github.com/aimacode/aima-java.git

- Agora tente a `Opção 1` e se não funcionar tente `Opção 2` novamente

# Resources 
- Compiling resource
`https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html#BHCGAJDC`