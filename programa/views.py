from django.shortcuts import render, get_object_or_404
from .models import *
# Create your views here.

def candidato_list(request):
    candidatos = Candidato.objects.all()
    return render(request, 'programa/candidato_list.html', {'candidatos':candidatos})

def candidato_detail(request, pk):
    candidatos = get_object_or_404(Candidato, pk=pk)
    return render(request, 'programa/candidato_detail.html', {'candidatos':candidatos})

