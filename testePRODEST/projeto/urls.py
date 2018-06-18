
from django.urls import path, re_path
from . import views

urlpatterns = [
    re_path(r'^$', views.busca, name='busca'),
    re_path(r'^busca/candidatos/(?P<concurso>.+)$', views.busca_candidatos, name='busca_candidatos'),
    re_path(r'^busca/concursos/(?P<candidato>.+)$', views.busca_concursos, name='busca_concursos'),
]
