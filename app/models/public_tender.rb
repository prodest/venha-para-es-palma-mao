class PublicTender
  include Mongoid::Document
  include Mongoid::Timestamps
  include Mongoid::TagCollectible::Tagged

  field :department #Órgão
  field :document_number #Edital
  field :code #Código do Concurso

  validates :department, presence: true
  validates :document_number, presence: true
  validates :code, presence: true, uniqueness: true

  index({ code: 1 }, { unique: true })

  before_validation :downcase_tags

  def downcase_tags
    tags = tags.map(&:downcase) if tags
  end

  def self.search(params)
    results = Array.new
    PublicTender.any_of({:department => /.*#{params[:q].upcase}.*/ }, {code: /.*#{params[:q]}.*/}).only(:id, :department, :code).each do |public_tender|
      results << {
        id: public_tender.id.to_s,
        text: public_tender.department + " | " + public_tender.code
      }
    end
    {results: results}
  end
end
