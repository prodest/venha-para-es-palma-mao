import sys,os

#verificando onde se encontra o arrquivo settings
project_dir= os.path.dirname(os.path.abspath(__file__))+'/avaliacao'
sys.path.append(project_dir)

os.environ['DJANGO_SETTINGS_MODULE']='settings'

#iniciando o django para fazer o import
import django

django.setup()

#importando os modelos a serem usados
from programa.models import *

#declaraçao de obejtos
candidato = Candidato()
orgao = Orgao()
profissao = Profissao()
concurso = Concurso()

#abrindo o arquivo
arq = open('candidatos.txt', 'r')
texto = arq.readline()
texto = arq.readline()
texto = arq.readline()

#caracteres a serem removidos da linha
remove = "[]\n" 
for i in range(0, len(remove)) :
    texto = texto.replace(remove[i],"")

#após a limpeza, corto o texto em partes
# print(texto)
informacoes = texto.split(" ", 4)
# print(informacoes)
list_prof = informacoes[4].split(",")
# print(list_prof)
# print(informacoes[0]+ " " + informacoes[1])


for prof in list_prof :
    candidato = Candidato()
    profissao = Profissao()
    profissao.nome = prof
    print(profissao.nome)
    candidato.nome = informacoes[0]+ " " + informacoes[1]
    candidato.data_nascimento = informacoes[2]
    candidato.cpf = informacoes[3]
    if Profissao.objects.filter(nome=profissao.nome).exists():
        print("eu existo!")
        profissao = Profissao.objects.get(nome=profissao.nome)
        candidato.profissao = profissao
        candidato.save()
    else:
        print("eu existo!")
        profissao.save()
        profissao = Profissao.objects.get(nome=profissao.nome)
        candidato.profissao = profissao
        candidato.save()

print(" OK ")
arq.close()