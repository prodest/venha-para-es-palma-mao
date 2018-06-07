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
end
