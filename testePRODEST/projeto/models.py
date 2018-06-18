from django.db import models
from django.utils import timezone

class Candidatos(models.Model):
    nome = models.CharField(max_length = 250)
    dataNasc = models.CharField(max_length = 10)
    cpf = models.CharField(max_length = 11)
    profissoes = models.TextField()

    def __str__(self):
        return self.nome
    #end def

#end class

class Concursos(models.Model):
    orgao = models.CharField(max_length = 50)
    edital = models.CharField(max_length = 7)
    codigoConcurso = models.CharField(max_length = 50)
    vagas = models.TextField()

    def __str__(self):
        return self.orgao
    #end def

#end class
