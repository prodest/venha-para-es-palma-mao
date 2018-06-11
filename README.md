# Documentação da Solução

A presente solução foi implementada na liguagem PHP  tomando como base o padrão MVC 
onde pode ser observado a separação dos arquivos para melhor entendimento da mesma para edição
dos arquivos foi utilizado o editor de codigo [VScode](https://code.visualstudio.com/).

foi utilizado o banco de dados MYSQL para criação das tabelas de cadidatos, Concursos e Profissoes
os arquivos de script do banco estão na pasta arqDocumentacao onde pode ser realizada a construção
do banco de dados através deles.

A aplicação desenvolvida possui uma pagina principal onde pode ser realizada as seguintes tarefas
importar os arquivos, buscar por CPF e buscar por Codígo Concurso

**Scripts de Banco e Select**

Os arquivos de criação do banco de dados e os selects encontram-se na pasta 
arqDocumentação juntamente com o DER

**Importar arquivos**.

Nesta pagina e possivel realizar a importação dos arquivos txt passados no teste e popular o banco com os mesmos, a resolução deste problema e pode ser encontradada na pasta controler e os arquivos procDadosCandidatos.php e procDadosConcursos.php realizam esta ação, os arquivos executam praticamente os mesmos metodos,  primeiramente realizo os includes da conexão com o banco e depois das funções.php apos e recebido via post os arquivos de txt onde a varuavel Super Global $_FILES realiza a captura do arquivo logo apos e utilizada o metodo file() para onde atribui todo conteudo para um array, apos e usado um  laço de repetição para realizar a sepração dos dados, como; nome, data de nascimento e cpf do CAndidato ou orgão, edital e codígo do Concurso dos Concursos e por ultio as profissões.
 
 Para separação dos dados foi utilizado o metodo multiexplode() onde foi possivel realizar a quebra da string e apos lançar no array e assim  capturar os dados do array por posições, para captura das profissões foi realizada por dois metodos o ordenaArrayProf() e vereficaProfissoes() onde o metodo ordenaArrayProf() realiza  uma varredura no array passado como parametro e verefica apartir da posição 4,se a posição e diferente de vazia, apos insere  a posição dentro de um novo arrai e depois em no array novoArraOrder[] ordenado por posições 0 - n.
  
  Ja no metodo vereficaProfissoes() ele realiza  a comparação das strings do array passado como parametro em um aninhamento de if e else verificado cada porfissão e no caso das profissoes que possuem o nome grande como e o caso do professor de matematica e feita a concatenação da string e colocada em um variavel e inserida no no array com as profissões ajustdas, assim e concluido o desmenbramento da linha do arquivo txt para ambos os casos, seja candidatos ou concursos. 
Logo apos e e criada SQL de inserção no banco de dados e executada atravez do metodo query() assim os dados são inseridos no banco de dados


**Buscar por CPF e Codigo Concurso**

Nas buscas por CPF e Codigo do curso não consegui realizar uma solução adequada, devido as inserções no banco dados  serem em TRANSACTIONs devido as FK que pegam os ids de ambas as tabelas  não consegui chegar a uma logica para inserir no banco ,  com isso as inserções das profissões não foram realizadas, mas realizei uma consulta simples via jquery para realizar a consulta sem refresh, dos CPF e codigo dos Cursos. Pela logica e o DER criado seria necessario uma inserção com TRANSACTION, para inserir na tabela associativa das profissões, na pasta arqDocumentação existe os scripts dos banco com suas respectivas SQLs para inserçaõ via TRANSACTION.


# Lista dos diferenciais implementados

**Implementação em banco de dados**

**Utilização de padrão MVC**

**Tratamento de Erros**

**Responsivo**

**Clean Code**

# Link do projeto Wakatime
[Meu WakaTime](https://wakatime.com/@46b10f44-8b60-4607-a4e0-827123d6a029/projects/xuzzsduoql?start=2018-06-04&end=2018-06-10)
