
# Como Compilar e Rodar o projeto

## Requisitos
- `python 3`
- `javac` e `java`, versão Java 8.
- `git`

#### **Observação**: Todos os programas acima devem estar configurados no `PATH`

### Ubuntu Linux:

- Atualizar		
		
		$ sudo apt update && sudo apt upgrade

- JDK, python e git
		
		$ sudo apt-get install default-jdk python3 git


### Manjaro Linux (usando pacman):

- Update pacman

		$ sudo pacman -Syu

- JDK pode ser instalado com o comando

		$ sudo pacman -S jre8-openjdk-headless jre8-openjdk jdk8-openjdk openjdk8-doc openjdk8-src

- Python para compilar os arquivos java
		
		$ sudo pacman -S python3
		$ sudo pacman -S git

## Primeiramente
-	Clone esse repo em um diretório que **NÃO possua caracteres especiais** (acentos, emojis, coisas assim) e vá para o diretório clonado. Todos os comandos a seguir em (Opção 1,2,3 e 4) devem ser feitos **dentro do diretório onde a repo foi clonada**.

		$ git clone https://github.com/evertonse/CSP-Problem-UFS-CC.git
		$ cd ./CSP-Problem-UFS-CC

## Compilar : Opção 1 - Usar python para *compilar* e *rodar*:
- Rode `./compile.py` que irá compilar e rodar todo o projeto.
			
		$ python3 compile.py

- Se tiver no Windows basta dar double-click em `compile.py`

## Compilar: Opção 2 - Usar um programa java para apenas *compilar*:
- Rode o comando abaixo para compilar um programa em java que é responsável a compilar o projeto:

		$ javac Compile.java

- Agora rode esse programa com o comando abaixo:
		
		$ java Compile
	
- Se tudo deu certo, então o projeto foi compilado, basta agora rodar com o comando abaixo:
	
		$ java -cp "./build/" ufs.Main
		
## Compilar: Opção 3 - Manualmente:
- Rode o comando abaixo para *compilar*:

		$ javac -classpath "./build/" -sourcepath "./" -d "./build/" @javafiles.args

- Opcionalmente para compilar rode essa versão menor
		
		$ javac -d "./build/" @javafiles.args

- Rode o comando abaixo para iniciar o programa a partir da classe `Main` do projeto
	
		$ java -cp "./build/" ufs.Main

## Compilar: Opção 4 (caso esteja faltando o código do AIMA):

- Clone a lib AIMA na versão 4 pelo comando abaixo, você deve estar na `directory do projeto`.

		$ git clone --recursive https://github.com/aimacode/aima-java.git --branch AIMA4e

- Opcionalmente Se tiver `git 1.7.10` ou mais:

		$ git clone --recursive  --single-branch --branch AIMA4e https://github.com/aimacode/aima-java.git

- Agora tente a `Opção 1`, se não funcionar tente `Opção 2` e se não funcionar tente `Opção 3`  novamente.

# Resources 
- Compiling resource
`https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html#BHCGAJDC`