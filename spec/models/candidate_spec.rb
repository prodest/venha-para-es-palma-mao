require 'rails_helper'

describe Candidate do
  describe "#validate candidates?" do
    it "Should be valid candidate" do
      candidate1 = Candidate.new(name: "Lindsey Craft", document_number: "122.885.607-97", birthdate: "19/05/1976")
      expect(candidate1.valid?).to eq(true)
    end
    it "Should be invalid candidate" do
      candidate1 = Candidate.new(name: "Lindsey Craft", document_number: "122.885.607-96", birthdate: "19/05/1976")
      expect(candidate1.valid?).to eq(false)
      candidate2 = Candidate.new(name: "Lindsey Craft", document_number: "122.885.607-97", birthdate: "19/05/2002")
      expect(candidate2.valid?).to eq(false)
      candidate3 = Candidate.new(name: "Lindsey Craft", document_number: "122.885.607-97")
      expect(candidate3.valid?).to eq(false)
      candidate4 = Candidate.new(name: "Lindsey Craft")
      expect(candidate4.valid?).to eq(false)
      candidate5 = Candidate.new
      expect(candidate5.valid?).to eq(false)
    end
  end
end
