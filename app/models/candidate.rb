class Candidate
  include Mongoid::Document
  include Mongoid::Timestamps
  include Mongoid::TagCollectible::Tagged

  field :name #Nome do Candidato
  field :document_number #CPF
  field :birthdate, type: Date #Data de Nascimento

  index({ document_number: 1 }, { unique: true })

  validates :name, presence: true
  validates :birthdate, presence: true
  validates :document_number, presence: true, uniqueness: true
  validate :validate_age
  validate :validate_document_number

  #Antes de ocorrer a validação, o método 'downcase_tags' é chamado, fazendo com que as tags, que neste caso representam as profissões dos candidatos, sejam transformadas em downcase, ou seja, minúsculas
  before_validation :downcase_tags

  private

  #Valida se o candidato é maior de 18 anos
  def validate_age
    if birthdate.present? && birthdate.to_date >= 18.years.ago.to_date
      errors.add(:birthdate, 'Candidato menor de idade.')
    end
  end

  #Valida o número do CPF informado
  def validate_document_number
    if not (document_number.present? && CPF.valid?(document_number))
      errors.add(:document_number, 'CPF inválido.')
    end
  end

  def downcase_tags
    tags = tags.map(&:downcase) if tags
  end

  #Função utilizada para retornar os candidatos, baseando-se na entrada de dados do usuário
  def self.search(params)
    #Varável do tipo array, onde os resultados da query seguinte serão armazenados, se houverem dados a armazenar
    results = Array.new
    #Query onde, os registros são buscados no banco de dados. As buscas são realizadas tanto pelo nome do Candidato, quanto pelo CPF
    candidates = any_of({:name => /.*#{params[:q].titleize}.*/ }, {document_number: /.*#{params[:q].gsub(".", "").gsub("-", "")}.*/}).only(:id, :name, :document_number)
    #Atribuição dos resultados da query no array declarado acima
    candidates.each do |candidate|
      #Foi utilizada a biblioteca Select2, que implementa o recurso 'Auto Complete'.
      #Os campos 'id' e 'text' são necessários para que o usuário escolha a opção correta e o sistema entanda a qual registro pertence a opção selecionada
      results << {
        id: candidate.id.to_s,
        text: candidate.name + " | " + candidate.document_number
      }
    end
    {results: results}
  end
end
