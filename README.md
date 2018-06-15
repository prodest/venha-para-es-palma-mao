Nesse projeto foi utilizado TAD para implementação do codigo em c.

O programa lê os arquivos "concursos.txt" e "candidatos.txt", armazenando as informações em estruturas próprias.
Logo após a leitura e armazenamento das informações o programa recebe os parâmetros passados pelo usuário:

1. Pesquisar concursos vigentes pelo CPF;
2. Pesquisar dados do concurso específico por meio do seu número.

Na opção 1 o candidato terá no retorno do terminal todos os concursos que a pessoa com o CPF cadastrado poderá fazer de acordo com suas opções de profissões. Entretanto seja realizado a opção 2, retornará no terminal a saída com os dados do concurso de acordo com o código informado.
Caso o CPF ou Número do concurso não existam o programa irá informar aos usuário e encerrar!

Para copilar a aplicação basta realizar os seguintes comandos:
1. gcc -c main.c
2. gcc -c functions.c
3. gcc -o exec main.o functions.o
4. ./exec

Caso o programa não rode devido não ter o compilador do C, basta realizar o seguite comando:
sudo apt-get install gcc
