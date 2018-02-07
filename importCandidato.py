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

# # # declaracao de obejtos
candidato = Candidato()
orgao = Orgao()
profissao = Profissao()
concurso = Concurso()

# # #funcao de remover caracteres
def remove_caracter(texto) :
    remove = "[]\n"
    for i in range(0, len(remove)) :
        texto = texto.replace(remove[i],"")
    return texto

# # # abrindo o arquivo. Leio linha por linha, para nao colocar
# tudo na memoria
arq = open('candidatos.txt', 'r')
texto = arq.readline()

# # # caracteres a serem removidos da linha
texto = remove_caracter(texto)

# # # apos a limpeza, corto o texto em partes
informacoes = texto.split(" ", 4)
list_prof = informacoes[4].split(",")
i = 1

# # salvando profissao
while texto != '':
    for prof in list_prof :
        profissao = Profissao()

        # # # verifico se o split foi cortado corretamente, isto e,
        # nao tem cpf junto com profissao. Ex 123.123.123-12 adm
        if(prof[0].isdigit()):
            aux = prof.split(" ", 1)
            profissao.nome = aux[1]

        else:
            profissao.nome = prof

        # # # verifico se ja existe a profissao. Se ja existe eu salvo apenas 
        # o candidato
        if not Profissao.objects.filter(nome=profissao.nome).exists():
            profissao.save()

    texto = arq.readline()
    texto = remove_caracter(texto)
    print("salvando profissao", i)
    i += 1
    if texto != '' :
        # # # após a limpeza, corto o texto em partes novamente
        informacoes = texto.split(" ", 4)
        list_prof = informacoes[4].split(",")
print("Profissao salvo com sucesso ")
arq.close()

#***********************************************************************************************************

arq = open('candidatos.txt', 'r')
texto = arq.readline()

# # # caracteres a serem removidos da linha
texto = remove_caracter(texto)

# # # apos a limpeza, corto o texto em partes
informacoes = texto.split(" ", 4)
list_prof = informacoes[4].split(",")
i = 1

# # # salva candidato
while texto != '':
    for prof in list_prof :
        candidato = Candidato()
        profissao = Profissao()

        # # # verifico se o split foi cortado corretamente, isto e,
        # nao tem cpf junto com profissao. Ex 123.123.123-12 adm
        if(prof[0].isdigit()):
            aux = prof.split(" ", 1)
            # # # cpf junto com profissao, eh necessario separar
            informacoes[4] = aux[0]
            candidato.nome = informacoes[0] + " " + informacoes[1] + " " + informacoes[2]
            candidato.data_nascimento = informacoes[3]
            candidato.cpf = informacoes[4]
            profissao.nome = aux[1]

        else:
            profissao.nome = prof
            candidato.nome = informacoes[0]+ " " + informacoes[1]
            candidato.data_nascimento = informacoes[2]
            candidato.cpf = informacoes[3]

        profissao = Profissao.objects.get(nome=profissao.nome)
        candidato.profissao = profissao
        candidato.save()

    texto = arq.readline()
    texto = remove_caracter(texto)
    print("salvando candidato", i)
    i += 1

    if texto != '' :
        # # # após a limpeza, corto o texto em partes novamente
        informacoes = texto.split(" ", 4)
        list_prof = informacoes[4].split(",")

print("candidato salvo com sucesso ")
arq.close()