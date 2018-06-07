class CandidatesController < ApplicationController
  def index
    @candidates = Candidate.asc("name").page(params[:page])
  end

  def public_tenders
    @candidate = Candidate.find_by(document_number: search_params[:document_number])
    @public_tenders = PublicTender.where(:tags.in => @candidate.tags).page(params[:page])
  end  

  private

  def search_params
    params.require(:search).permit(:document_number)
  end
end
