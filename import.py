import sys,os

#verificando onde se encontra o arrquivo settings
project_dir= os.path.dirname(os.path.abspath(__file__))+'/avaliacao'
sys.path.append(project_dir)

os.environ['DJANGO_SETTINGS_MODULE']='settings'

#iniciando o django para fazer o import
import django

django.setup()

#abrindo o arquivo
arq = open('candidatos.txt', 'r')
texto = arq.readline()

#caracteres a serem removidos da linha
remove = "[]\n" 
for i in range(0, len(remove)) :
    texto = texto.replace(remove[i],"")

#cortando o texto em partes
print(texto)
informacoes = texto.split(" ", 4)
print(val)
profissao = val[4].split(",")
print(test)
print(val[0]+ " " + val[1])
arq.close()