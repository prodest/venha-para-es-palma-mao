form django import forms
from .models import Concursos, Candidatos

class Busca_Form(forms.Form):
    codigo = forms.CharField(label='Codigo', max_length=100)
    
