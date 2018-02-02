from django.shortcuts import render
from .models import *
# Create your views here.

def candidato_list(request):
    candidatos = Candidato.objects.all()
    return render(request, 'programa/candidato_list.html', {'candidatos':candidatos})

