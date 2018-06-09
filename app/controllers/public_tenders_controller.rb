class PublicTendersController < ApplicationController
  def index
    #Listando todos os concursos, respeitando a páginação dos resultados
    @public_tenders = PublicTender.asc("department").page(params[:page])
  end

  def candidates
    #Buscando o concursos pelo ID informado pelo usuário e listando todos os candidatos à ele afins
    @public_tender = PublicTender.find(params[:id])
    @candidates = Candidate.where(:tags.in => @public_tender.tags).page(params[:page])
  end

  def search
    #Recebendo os paraâmetros da busca informados pelo usuário e executando as buscas
    render :json => PublicTender.search(params), status: 200
  end
end
