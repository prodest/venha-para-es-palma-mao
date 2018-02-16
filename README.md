Nesse projeto foi utilizado TAD para implementação do codigo em c.

O programa lê os arquivos "concursos.txt" e "candidatos.txt", armazenando as informações em estruturas próprias.
Logo após a leitura e armazenamento das informações o programa recebe os parâmetros passados pelo usuário:

1. Pesquisar concursos vigentes pelo CPF;
2. Pesquisar dados do concurso específico por meio do seu número.

Na opção 1 o candidato terá no retorno do terminal todos os concursos que a pessoa com o CPF cadastrado poderá fazer de acordo com suas opções de profissões. Entretanto seja realizado a opção 2, retornará no terminal a saída com os dados do concurso de acordo com o código informado.
Caso o CPF ou Número do concurso não existam o programa irá informar aos usuário!

Para copilar o programa precisa-se realizar os seguintes comandos:
1. gcc -c main.c
2. gcc -c functions.c
3. gcc -o exec main.o functions.o
4. ./exec

Caso o programa não rode devido não ter o compilador do C, basta realizar o seguite comando:
sudo apt-get install gcc

Agora se quiser verificar o uso de memória(alocação) do programa use o valgrind com o seguinte comando:
valgrind ./exec

Se o valgrind não estiver instalado basta dar o seguinte comando:
sudo apt-get install valgrind

Por fim, segue o link do projeto no WakaTime: [ https://wakatime.com/@bc7c564c-5fa8-485e-ac52-09287dffff0f/projects/pepvkwsnxi?start=2018-02-10&end=2018-02-16 ]
