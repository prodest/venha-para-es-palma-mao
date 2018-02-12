import sys,os

# # # verificando onde se encontra o arrquivo settings
project_dir= os.path.dirname(os.path.abspath(__file__))+'/avaliacao'
sys.path.append(project_dir)

os.environ['DJANGO_SETTINGS_MODULE']='settings'

# # # iniciando o django para fazer o import
import django

django.setup()

# # # importando os modelos a serem usados
from programa.models import *

# # #funcao de remover caracteres
def remove_caracter(texto) :
    remove = "[]\n"
    for i in range(0, len(remove)) :
        texto = texto.replace(remove[i],"")
    return texto

arq = open('concursos.txt', 'r')
texto = arq.readline()
texto = remove_caracter(texto)

# # # apos a limpeza, corto o texto em partes
informacoes = texto.split(" ", 3)
list_prof = informacoes[3].split(",")
i = 1

# # # salva Concurso
while texto != '':
    for prof in list_prof :
        concurso = Concurso()
        profissao = Profissao()
        orgao = Orgao()
        
        profissao.nome = prof
        orgao.nome = informacoes[0]
        concurso.edital = informacoes[1]
        concurso.codigo_curso = informacoes[2]

        if not Profissao.objects.filter(nome=profissao.nome).exists():
            profissao.save()

        if not Orgao.objects.filter(nome=orgao.nome).exists():
            orgao.save()

        profissao = Profissao.objects.get(nome=profissao.nome)
        orgao = Orgao.objects.get(nome=orgao.nome)
        concurso.vagas = profissao
        concurso.orgao = orgao
        concurso.save()

    texto = arq.readline()
    texto = remove_caracter(texto)
    print("salvando concurso", i)
    i += 1

    if texto != '' :
        # # # apos a limpeza, corto o texto em partes novamente
        informacoes = texto.split(" ", 3)
        list_prof = informacoes[3].split(",")

print("concurso salvo com sucesso ")
arq.close()