from django.shortcuts import render, get_object_or_404
from .models import *
# Create your views here.

def home(request):
    return render(request, 'programa/index.html', {} )

def candidato_list(request):
    url = 'programa/candidato_list.html'
    candidatos = Candidato()
    concursos = []
    verifica_concurso = False

    var_get_search = request.GET.get('search_box')
    if var_get_search is not None:
        candidatos = Candidato.objects.all()
        if candidatos.filter(cpf=var_get_search).exists() : 
            candidatos = candidatos.filter(cpf=var_get_search)
            for elem in candidatos : 
                concursos = Concurso.objects.filter(vagas=elem.profissao)
        else:
            verifica_concurso = True
    return render(request, url, {'concursos':concursos, 'verifica':verifica_concurso})

def concurso_list(request):
    url = 'programa/concurso_list.html'
    candidatos = []
    concursos = Concurso()
    var_get_search = request.GET.get('search_box')
    verifica_concurso = False

    if var_get_search is not None:
        concursos = Concurso.objects.all()
        if concursos.filter(codigo_curso=var_get_search).exists() :
            concursos = concursos.filter(codigo_curso=var_get_search)
            for elem in concursos : 
                candidatos = Candidato.objects.filter(profissao=elem.vagas)
        else:
            verifica_concurso = True
    return render(request, url, {'candidatos':candidatos, 'verifica':verifica_concurso})

