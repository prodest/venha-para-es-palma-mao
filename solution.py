def pegar_cargos(linha): 
    # Esta função retorna uma lista de strings correspondente às profissões
    # listadas entre colchetes no final de uma linha de um arquivo
    contador = 0
    for caracter in linha:
        if caracter != '[':
            contador = contador + 1
        else:
            break
    # a variavel contador guarda a posição do caracter '['
    end = len(linha)
    substring = linha[contador+1:end-2] # corta a parte da linha do arquivo entre os colchetes
    cargos = substring.split(', ') # separa a substring em uma lista de strings para ser retornada
    return cargos

def listar_candidatos(codigo_concurso):
    cargos_concurso = []
    with open('concursos.txt', 'r') as file: # abre o arquivo para achar o concurso correspondente ao código fornecido
        while True:
            linha = file.readline()
            if len(linha) > 0: # se o arquivo chegar ao fim, file.readline retorna uma string vazia, ou seja len(linha) = 0
                words = linha.split(" ") # Separa a linha em uma lista de 'palavras' entre espaços
                if codigo_concurso == words[2]: # a terceira 'palavra' corresponde ao código do concurso
                    cargos_concurso = pegar_cargos(linha)
                    break
            else:
                print("Código inválido! Concurso não encontrado!")
                break
    with open('candidatos.txt','r') as file: # abre o arquivo para listar os candidatos
        linha = file.readline()
        while len(linha) > 0:
            adequado = False # variavel que diz se o candidato é adequado ao concurso 
            cargos_candidato = pegar_cargos(linha)
            for cargo_candidato in cargos_candidato:
                for cargo_concurso in cargos_concurso:
                    if cargo_candidato == cargo_concurso:
                        adequado = True # se o candidato tiver alguma profissão que está na lista do concurso ele é adequado
                        break
            if adequado:
                linha_aparada = linha.split('[')
                linha_aparada = linha_aparada[0] # remove a parte da lista de profissões da linha
                print(linha_aparada) # imprime as linhas correspondentes aos candidatos adequados
            linha = file.readline()
    return

def listar_concursos(cpf_candidato):
    cargos_candidato = []
    with open('candidatos.txt', 'r') as file:  # abre o arquivo para achar o candidato correspondente ao cpf fornecido
        while True:
            linha = file.readline()
            if len(linha) > 0: # se o arquivo chegar ao fim, file.readline retorna uma string vazia, ou seja len(linha) = 0
                words = linha.split(" ")
                if cpf_candidato == words[3]: # O cpf do candidato está na quarta 'palavra' da linha
                    cargos_candidato = pegar_cargos(linha)
                    break
            else:
                print("CPF inválido! Candidato não encontrado!")
                break
    with open('concursos.txt','r') as file:
        linha = file.readline()
        while len(linha) > 0:
            adequado = False # variavel que diz se o concurso é adequado ao candidato
            cargos_concurso = pegar_cargos(linha)
            for cargo_candidato in cargos_candidato:
                for cargo_concurso in cargos_concurso:
                    if cargo_candidato == cargo_concurso:
                        adequado = True # se o concurso tiver alguma profissão que está na lista do candidato ele é adequado
                        break
            if adequado:
                linha_aparada = linha.split('[')
                linha_aparada = linha_aparada[0] # remove a parte da lista de profissões da linha
                print(linha_aparada) # imprime as linhas correspondentes aos concursos adequados
            linha = file.readline()
    return

while True:
    # pequeno menu para o usuário
    print('Para listar os concursos para um candidato, digite \'1\'')
    print('Para listar os candidatos para um concurso, digite \'2\'')
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
