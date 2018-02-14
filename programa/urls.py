from django.conf.urls import include, url
from . import views

urlpatterns = [
    url(r'^$', views.home, name='home'),
    url(r'^candidato/$', views.candidato_list, name='candidato_list'),
    url(r'^concurso/$', views.concurso_list, name='concurso_list'),
]