class PublicTendersController < ApplicationController
  def index
    @public_tenders = PublicTender.asc("department").page(params[:page])
  end

  def candidates
    @public_tender = PublicTender.find(params[:id])
    @candidates = Candidate.where(:tags.in => @public_tender.tags).page(params[:page])
  end

  def search
    respond_to do |format|
      format.json {
        render :json => PublicTender.search(params)
      }
    end
  end
end
