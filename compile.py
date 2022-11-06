import os
from glob import glob

EXCLUDE_FOLDERS :set = {'extra','gradle','test','gui'}

javafiles 	 :list = [y for x in os.walk(".\\") for y in glob(os.path.join(x[0], '*.java'))]
jarfiles 	 	 :list = [y for x in os.walk(".\\") for y in glob(os.path.join(x[0], '*.jar'))]

classpath 	 :str  = "./build/" 
argsfilename :str	 = "javafiles.args"

# Adiciona a lista de arquivos para compilar
# apenas se o path do arquivo não contem os folders 
# especificados em EXCLUDE_FOLDERS
with open( argsfilename, 'w+' ) as f:
	for j in sorted(javafiles):
		if any([j.count(e) > 0 for e in EXCLUDE_FOLDERS]):
			continue
		f.write(f"{j}\n")

cmd_compile :str 	= f'javac -classpath {classpath} -sourcepath "./" -d "{classpath}" ' + f"@{argsfilename}"
print(cmd_compile)
code:int = os.system(cmd_compile)


cmd_run 		:str	= f'java -cp "{classpath}" Main'
print(cmd_run)
code:int = os.system(cmd_run)	


"""
# Opcional compilar em um comando só:

javafiles_star = set([x[0:x.rindex("\\")]+'\\*.java' for x in result ])

cmd_compile :str 	= f'javac -d "{classpath}" \\' +  ' \\'.join(javapaths)
print(cmd_compile)
code:int = os.system(cmd_compile)

"""