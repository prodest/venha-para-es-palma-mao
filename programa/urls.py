from django.conf.urls import include, url
from . import views

urlpatterns = [
    url(r'^$', views.candidato_list, name='candidato_list'),
    url(r'^cand/(?P<pk>[0-9]+)/$', views.candidato_detail, name='candidato_detail'),
]