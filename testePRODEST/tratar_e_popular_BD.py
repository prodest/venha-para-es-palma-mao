#!/usr/bin/python

import re
import sqlite3

'''
Esse algoritmo serve para tratar os arquivos concursos.txt e candidatos.txt
através de Expressões regulares (regex). Dividindo cada linha em colunas, adicionar
elas em uma matriz e presistir essa matriz no banco de dados em suas respectivas
tabelas.
'''
def tratar_linhas(linha):

    colunas = []
    profissoes = ""

    if (re.match(r'([A-Z][a-z\u00C0-\u00FF]+)', linha.split(' ')[0])): #verifica se é um nome de pessoa
        colunas = re.split(r'([A-Za-z\u00C0-\u00FF\s\']+)\s(\d{2}\/\d{2}\/\d{4})\s(\d{3}\.\d{3}\.\d{3}\-\d{2})\s(\[[A-Za-z\u00C0-\u00FF\s\,]+\])', linha)
    elif(re.match(r'([A-Z]+)', linha.split(' ')[0])): # verifica se é uma instiruição
        colunas = re.split(r'([A-Z]+)\s(\d{1,2}\/\d{1,4})\s(\d+)\s(\[[A-Za-z\u00C0-\u00FF\s\,]+\])',linha)
    else:
        return ValueError('Formato inválido: ', linha)
    #end if

    if '' in colunas:
        colunas.remove('')
    #end if
    if '\n' in colunas:
        colunas.remove('\n')
    #end if

    profissoes = colunas.pop()
    profissoes = profissoes.replace('[','').replace(']','')
    colunas.append(profissoes)

    return colunas
#end def

def persistir_Candidatos(candidatos):
    i = 0
    conn = sqlite3.connect('db.sqlite3')
    c = conn.cursor()

    print('Salvando candidatos no banco de dados. Aguarde...')
    for linha in candidatos:
        c.execute("INSERT INTO projeto_candidatos (nome, dataNasc, cpf, profissoes) VALUES (?, ?, ?, ?)",
                    (linha[0], linha[1], linha[2], linha[3]))
        conn.commit()
    #end for
    print('Finalizado.')
    c.close()
    conn.close()
#end def

def persistir_Concursos(concursos):
    i = 0
    conn = sqlite3.connect('db.sqlite3')
    c = conn.cursor()

    print('Salvando concursos no banco de dados. Aguarde...')
    for linha in concursos:
        c.execute("INSERT INTO projeto_concursos (orgao, edital, codigoConcurso, vagas) VALUES (?, ?, ?, ?)",
                    (linha[0], linha[1], linha[2], linha[3]))
        conn.commit()
    #end for
    print('Finalizado.')
    c.close()
    conn.close()
#end def

def main():

    candidatos = []
    concursos = []

    file_candidatos = input('Digite o caminho do arquivo dos candidatos: ')
    arqCandidatos = open(file_candidatos, 'r')
    textoCandidatos = arqCandidatos.readlines()

    file_concursos = input('Digite o caminho do arquivo dos concursos: ')
    arqConcursos = open(file_concursos, 'r')
    textoConcursos = arqConcursos.readlines()

    for can in textoCandidatos:
        candidatos.append(tratar_linhas(can))
    #end for
    for conc in textoConcursos:
        concursos.append(tratar_linhas(conc))
    #end for

    if (candidatos):
        persistir_Candidatos(candidatos);
    #end if

    if (concursos):
        persistir_Concursos(concursos)
    #end if

    arqCandidatos.close()
    arqConcursos.close()
#end def

if __name__ == '__main__':
    main()
