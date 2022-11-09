import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

public class Compile {
	
	static Set<String> EXCLUDE_FOLDERS = new HashSet<String>( Arrays.asList("extra","gradle","test","gui"));
	static String root_path = "./";
	static String classpath    = "./build/"; 
	static String argsfilename = "javafiles.args";
	
	static ArrayList<String> javafiles = new ArrayList<String>();

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
				System.out.println(javafiles.get(i));
			}
			fw.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}

		

		String cmd_compile = "javac -classpath \"" + Compile.classpath + "\" -sourcepath \"./\" -d \"" + Compile.classpath + "\" @" + argsfilename;
		System.out.println(cmd_compile);
		
		String cmd_run = "java -cp \"" + Compile.classpath + "\" Main";
		System.out.println(cmd_run);

	}
	
	static void  getFilesRecursive(File pFile, List list) {

		for(File files : pFile.listFiles()) {
				
			if(files.isDirectory()) {
				getFilesRecursive(files, list);
			}
			else {
				String filename = files.getName();
				list.add(filename);
			}
		}
	}
}
