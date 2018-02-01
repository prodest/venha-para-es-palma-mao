from django.shortcuts import render

# Create your views here.

def candidato_list(request):
    return render(request, 'programa/candidato_list.html', {})