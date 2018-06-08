class PublicTendersController < ApplicationController
  def index
    @public_tenders = PublicTender.asc("department").page(params[:page])
  end

  def candidates
    @public_tender = PublicTender.find(params[:id])
    @candidates = Candidate.where(:tags.in => @public_tender.tags).page(params[:page])
  end

  def search
    render :json => PublicTender.search(params), status: 200
  end
end
