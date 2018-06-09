class CandidatesController < ApplicationController
  def index
    #Listando todos os candidatos, respeitando a páginação dos resultados
    @candidates = Candidate.asc("name").page(params[:page])
  end

  def public_tenders
    #Buscando o candidato pelo ID informado pelo usuário e listando todos os concurso à ele afins
    @candidate = Candidate.find(params[:id])
    @public_tenders = PublicTender.where(:tags.in => @candidate.tags).page(params[:page])
  end

  def search
    #Recebendo os paraâmetros da busca informados pelo usuário e executando as buscas
    render :json => Candidate.search(params)
  end
end
