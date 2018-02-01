from django.db import models
from django.utils import timezone
# Create your models here.

class Profissao(models.Model):
    nome = models.CharField(max_length=50)


class Candidato(models.Model):
    nome = models.CharField(max_length=100)
    data_nascimento = models.CharField(max_length=10)
    cpf = models.CharField(max_length=14)
    profissao = models.ForeignKey(Profissao)


class Orgao(models.Model):
    nome = models.CharField(max_length=10)



class Concurso(models.Model):
    orgao = models.ForeignKey(Orgao)
    edital = models.CharField(max_length=10)
    codigo_curso = models.CharField(max_length=14)
    vagas = models.ForeignKey(Profissao)
