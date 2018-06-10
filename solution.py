def pegar_cargos(linha):
    contador = 0
    for caracter in linha:
        if caracter != '[':
            contador = contador + 1
        else:
            break
    end = len(linha)
    substring = linha[contador+1:end-2]
    cargos = substring.split(', ')
    return cargos

def listar_candidatos(codigo_concurso):
    cargos_concurso = []
    with open('concursos.txt', 'r') as file:
        while True:
            linha = file.readline()
            if len(linha) > 0:
                words = linha.split(" ")
                if codigo_concurso == words[2]:
                    cargos_concurso = pegar_cargos(linha)
                    break
            else:
                print("Código inválido! Concurso não encontrado!")
                break
    with open('candidatos.txt','r') as file:
        linha = file.readline()
        while len(linha) > 0:
            apto = False
            cargos_candidato = pegar_cargos(linha)
            for cargo_candidato in cargos_candidato:
                for cargo_concurso in cargos_concurso:
                    if cargo_candidato == cargo_concurso:
                        apto = True
                        break
            if apto:
                linha_aparada = linha.split('[')
                linha_aparada = linha_aparada[0]
                print(linha_aparada)
            linha = file.readline()
    return

def listar_concursos(cpf_candidato):
    cargos_candidato = []
    with open('candidatos.txt', 'r') as file:
        while True:
            linha = file.readline()
            if len(linha) > 0:
                words = linha.split(" ")
                if cpf_candidato == words[3]:
                    cargos_candidato = pegar_cargos(linha)
                    break
            else:
                print("CPF inválido! Candidato não encontrado!")
                break
    with open('concursos.txt','r') as file:
        linha = file.readline()
        while len(linha) > 0:
            apto = False
            cargos_concurso = pegar_cargos(linha)
            for cargo_candidato in cargos_candidato:
                for cargo_concurso in cargos_concurso:
                    if cargo_candidato == cargo_concurso:
                        apto = True
                        break
            if apto:
                linha_aparada = linha.split('[')
                linha_aparada = linha_aparada[0]
                print(linha_aparada)
            linha = file.readline()
    return

while True:
    print('Para listar os concursos para um candidato, digite \'1\'')
    print('Para listar os candidatos para um cconcurso, digite \'2\'')
    print('Para sair, digite qualquer outra coisa')
    opcao = input('opcao ')
    if opcao == '2':
        codigo = input("Digite o código do concurso: ")
        listar_candidatos(codigo)
    elif opcao == '1':
        cpf = input("Digite o cpf do candidato no formato xxx.xxx.xxx-xx: ")
        listar_concursos(cpf)
    else:
        break
