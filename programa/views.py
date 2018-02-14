from django.shortcuts import render, get_object_or_404
from .models import *
# Create your views here.

def home(request):
    return render(request, 'programa/index.html', {} )

def candidato_list(request):
    concursos = []
    verifica_candidato = False
    var_get_search = request.GET.get('search_box')

    if var_get_search is not None:
        candidatos = Candidato.objects.all()
        if candidatos.filter(cpf=var_get_search).exists() : 
            candidatos = candidatos.filter(cpf=var_get_search)
            for elem in candidatos : 
                # coloquei um limite de 50 concursos para exibiçao. Para tirar o limite 
                # retire o [0:50]
                #OBS: como o banco de dados eh online e gratuito, demora de 10s - 60s
                # ao retirar o limite pode demorar cerca de 2min
                concursos.append(Concurso.objects.filter(vagas=elem.profissao)[0:50])
        else:
            verifica_candidato = True
    return render(request, 'programa/candidato_list.html', {'concursos':concursos, 'verifica':verifica_candidato})

def concurso_list(request):
    candidatos = []
    var_get_search = request.GET.get('search_box')
    verifica_concurso = False

    if var_get_search is not None:
        concursos = Concurso.objects.all()
        if concursos.filter(codigo_curso=var_get_search).exists() :
            concursos = concursos.filter(codigo_curso=var_get_search)
            for elem in concursos :
                # coloquei um limite de 50 candidatos para exibiçao. Para tirar o limite 
                # retire o [0:50]
                #OBS: como o banco de dados eh online e gratuito, demora de 10s - 60s
                # ao retirar o limite pode demorar cerca de 2min 
                candidatos.append(Candidato.objects.filter(profissao=elem.vagas)[0:50])
        else:
            verifica_concurso = True
    return render(request, 'programa/concurso_list.html', {'candidatos':candidatos, 'verifica':verifica_concurso})

