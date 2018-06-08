require 'rails_helper'

describe CandidatesController, type: :controller do
  describe "#search candidates" do
    it "Should be open candidates#index" do
      get :index
      expect(response).to render_template("index")
    end
  end
end
