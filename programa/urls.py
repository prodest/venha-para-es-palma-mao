from django.conf.urls import include, url
from . import views

urlpatterns = [
    url(r'^$', views.candidato_list, name='candidato_list'),
]