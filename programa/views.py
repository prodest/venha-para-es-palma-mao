from django.shortcuts import render, get_object_or_404
from .models import *
# Create your views here.

def home(request):
    return render(request, 'programa/index.html', {} )

def candidato_list(request):
    candidatos = Candidato()
    concursos = Concurso()
    var_get_search = request.GET.get('search_box')

    if var_get_search is not None:
        candidatos = Candidato.objects.all()
        candidatos = candidatos.filter(cpf=var_get_search)
        for elem in candidatos : 
            concursos = Concurso.objects.filter(vagas=elem.profissao)
        return render(request, 'programa/candidato_list.html', {'concursos':concursos})

    return render(request, 'programa/candidato_list.html', {})

def candidato_detail(request, pk):
    candidatos = get_object_or_404(Candidato, pk=pk)
    return render(request, 'programa/candidato_detail.html', {'candidatos':candidatos})


