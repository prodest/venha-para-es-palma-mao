require 'rails_helper'

describe CandidatesController, type: :controller do
  describe "#search candidates" do
    it "Should be open candidates#index" do
      get :index
      expect(response).to render_template("index")
    end
    it "Should be open candidates#public_tenders" do
      get :public_tenders, params: {id: Candidate.first.id}
      expect(response).to render_template("public_tenders")
    end
    it "Should be a valid search fot candidates by name" do
      get :search, params: {q: 'Aaron Mitchell'}
      search = response_body["results"]
      expect(response.status).to eq 200
      expect(search.to_s.include? "Aaron Mitchell").to eq(true)
    end
    it "Should be a valid search fot candidates by CPF" do
      get :search, params: {q: '970.009.867-66'}
      search = response_body["results"]
      expect(response.status).to eq 200
      expect(search.to_s.include? "Aaron Allison").to eq(true)
      get :search, params: {q: '97000986766'}
      search = response_body["results"]
      expect(response.status).to eq 200
      expect(search.to_s.include? "Aaron Allison").to eq(true)
    end
    it "Should be a invalid search fot candidates" do
      get :search, params: {q: 'A190'}
      search = response_body["results"]
      expect(response.status).to eq 200
      expect(search).to eq([])
    end
  end

  def response_body
    JSON.parse(response.body)
  end
end
