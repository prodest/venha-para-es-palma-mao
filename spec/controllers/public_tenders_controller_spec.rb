require 'rails_helper'

describe PublicTendersController, type: :controller do
  describe "#search public_tenders" do
    it "Should be open public_tenders#index" do
      get :index
      expect(response).to render_template("index")
    end
    it "Should be open public_tenders#candidates" do
      get :candidates, params: {id: PublicTender.first.id}
      expect(response).to render_template("candidates")
    end
    it "Should be a valid search fot public_tenders" do
      get :search, params: {q: '56551235392'}
      search = response_body["results"]
      expect(response.status).to eq 200
      expect(search.to_s.include? "IASES").to eq(true)
    end

    it "Should be a invalid search fot candidates" do
      get :search, params: {q: 'ABCD'}
      search = response_body["results"]
      expect(response.status).to eq 200
      expect(search).to eq([])
    end
  end

  def response_body
    JSON.parse(response.body)
  end
end
