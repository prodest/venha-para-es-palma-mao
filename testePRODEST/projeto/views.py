from django.shortcuts import render, redirect
from projeto.models import Candidatos, Concursos



def busca(request):
    if request.GET:
        if 'candidato' in request.GET['tipoBusca']:
            return redirect('/busca/candidatos/'+request.GET['codigo'])
        elif 'concurso' in request.GET['tipoBusca']:
            return redirect('/busca/concursos/'+request.GET['codigo'])

    return render(request, 'projeto/busca.html', {})

def busca_candidatos(request, concurso):
    conc = Concursos.objects.filter(codigoConcurso__contains=concurso)
    if conc:
        vagas = conc[0].vagas.split(', ')
        for v in vagas:
            candidatos = Candidatos.objects.filter(profissoes__contains=v)
    else:
        candidatos = []
    return render(request, 'projeto/candidatos.html', {'candidatos' : candidatos})

def busca_concursos(request, candidato):
    cand = Candidatos.objects.filter(cpf__contains=candidato)
    if cand:
        profissoes = cand[0].profissoes.split(', ')
        for p in profissoes:
            concursos = Concursos.objects.filter(vagas__contains=p)
    else:
        concursos = []
    return render(request, 'projeto/concursos.html', {'concursos' : concursos})
