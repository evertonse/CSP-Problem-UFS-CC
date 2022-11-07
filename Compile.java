import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.lang.ProcessBuilder;
import java.lang.Process;


public class Compile {
	
	static Set<String> EXCLUDE_FOLDERS = new HashSet<>(Arrays.asList("extra","gradle","test","gui")); 
	static String root_path 	 = "./";
	static String classpath    = "./build/"; 
	static String argsfilename = "javafiles.args";

	static String cmd_compile  = "javac -classpath \"" + Compile.classpath + "\" -sourcepath \"./\" -d \"" + Compile.classpath + "\" @" + argsfilename;
	static String cmd_run 		 = "java -cp \"" + Compile.classpath + "\" Main";

	static ArrayList<String> javafiles = new ArrayList<String>(25);

	public static void main(String[] args) {

		File root = new File(Compile.root_path);
		for ( File file : root.listFiles()) {
			getFilesRecursive(file,Compile.javafiles);
		}
		try {
			File argsfile = new File(Compile.argsfilename);
			argsfile.createNewFile();  
			FileWriter fw = new FileWriter(argsfile,false); 
			
			// Adiciona a lista de arquivos para compilar
			// apenas se o path do arquivo não contem os folders 
			// especificados em EXCLUDE_FOLDERS
			for (int i = 0; i < javafiles.size(); i++) {
				fw.write(javafiles.get(i) + "\n");
				//System.out.println(javafiles.get(i));
			}
			fw.close();
			System.out.println( ">> Escrita dos arquivos java no arquvivo: " + Compile.argsfilename + " feita com sucesso \n");
		} 
		catch (Exception e) {
			System.out.println( ">> Escrita dos arquivos java no arquvivo: " + Compile.argsfilename + " falhou \n" + e);
		}
		
		
		Process  compile_proc, run_proc;
		String[] compile_args = Compile.cmd_compile.split(" ");
		String[] run_args = Compile.cmd_run.split(" ");
		



		// Compilar o programa
		try {
			System.out.println(">> Compilando...");
			compile_proc = new ProcessBuilder(compile_args).start();
			compile_proc.waitFor();
			if (compile_proc.exitValue() == 0) {
				System.out.println(cmd_compile);
				System.out.println(">> Compilado com sucesso!");
			}
		}
			catch (Exception e) {
			System.out.println( ">> Comando javac falhou ao tentar compilar o projeto\n" + e);
			System.out.println( ">> IMPORTANTE : Compile manualmente com o comando abaixo:\n" + Compile.cmd_compile);
		}
		
		System.out.println( ">> IMPORTANTE : para rodar o projeto compilado, deve-se rodar o comando abaixo:\n" + Compile.cmd_run);


	}

	static void  getFilesRecursive(File pFile, List<String> list) {
		File[] files =  pFile.listFiles();
		
		if (files == null){
			//System.out.println("Files returned are empty");
			return;
		}
		for (int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) {
				// Excluindo Folder que não queremos acesssar
				if (EXCLUDE_FOLDERS.contains(files[i].getName())) {
				}
				else {
					getFilesRecursive(files[i], list);
				}
			}
			else {
				String filename = files[i].getPath();
				if (filename.endsWith(".java")){
					list.add(filename.replaceAll("\\\\", "/"));
				}
			}
			
		}

	}
}
