from django.db import models
from django.utils import timezone
# Create your models here.

class Profissao(models.Model):
    nome = models.CharField(max_length=50)

    def __str__(self):
        return self.nome

class Candidato(models.Model):
    nome = models.CharField(max_length=100)
    data_nascimento = models.CharField(max_length=10)
    cpf = models.CharField(max_length=14)
    profissao = models.ForeignKey(Profissao)

    def __str__(self):
        return self.nome

class Orgao(models.Model):
    nome = models.CharField(max_length=10)

    def publish(self):
        self.save()

    def __str__(self):
        return self.nome

class Concurso(models.Model):
    orgao = models.ForeignKey(Orgao)
    edital = models.CharField(max_length=10)
    codigo_curso = models.CharField(max_length=14)
    vagas = models.ForeignKey(Profissao)

    def __str__(self):
        return self.edital