from django.shortcuts import render, get_object_or_404
from .models import *
# Create your views here.

def candidato_list(request):
    var_get_search = request.GET.get('search_box')
    if var_get_search is not None:
        candidatos = Candidato.objects.all()
        verifica = False
        verifica = True
        candidatos = candidatos.filter(cpf=var_get_search)
        return render(request, 'programa/candidato_list.html', {'candidatos':candidatos})
    return render(request, 'programa/candidato_list.html', {})
    # candidatos = Candidato.objects.all()
    # return render(request, 'programa/candidato_list.html', {'candidatos':candidatos})

def candidato_detail(request, pk):
    candidatos = get_object_or_404(Candidato, pk=pk)
    return render(request, 'programa/candidato_detail.html', {'candidatos':candidatos})

