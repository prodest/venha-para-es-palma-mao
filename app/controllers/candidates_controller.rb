class CandidatesController < ApplicationController
  def index
    @candidates = Candidate.asc("name").page(params[:page])
  end

  def public_tenders
    @candidate = Candidate.find(params[:id])
    @public_tenders = PublicTender.where(:tags.in => @candidate.tags).page(params[:page])
  end

  def search    
    render :json => Candidate.search(params)
  end
end
