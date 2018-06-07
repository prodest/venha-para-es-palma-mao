class PublicTendersController < ApplicationController
  def index
    @public_tenders = PublicTender.asc("department").page(params[:page])
  end

  def candidates
    @public_tender = PublicTender.find_by(code: search_params[:document_number])
    @candidates = Candidate.where(tags: @public_tender.tags).page(params[:page])
  end

  private

  def search_params
    params.require(:search).permit(:document_number)
  end
end
