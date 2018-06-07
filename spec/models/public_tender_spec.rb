require 'rails_helper'

describe PublicTender do
  describe "#validate public tenders?" do
    it "Should be valid public tender" do
      public_tender1 = PublicTender.new(department: "SEDU", document_number: "9/2016", code: "61828450843")
      expect(public_tender1.valid?).to eq(true)
    end
    it "Should be invalid public tender" do
      public_tender1 = PublicTender.new(department: "SEDU", document_number: "9/2016")
      expect(public_tender1.valid?).to eq(false)
      public_tender2 = PublicTender.new(department: "SEDU")
      expect(public_tender2.valid?).to eq(false)
      public_tender3 = PublicTender.new
      expect(public_tender3.valid?).to eq(false)
    end
  end
end
