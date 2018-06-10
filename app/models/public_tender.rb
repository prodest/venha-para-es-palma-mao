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

  #Antes de ocorrer a validação, o método 'downcase_tags' é chamado, fazendo com que as tags, que neste caso representam as vagas do concurso, sejam transformadas em downcase, ou seja, minúsculas
  before_validation :downcase_tags

  def downcase_tags
    tags = tags.map(&:downcase) if tags
  end

  #Função utilizada para retornar os concursos públicos, baseando-se na entrada de dados do usuário
  def self.search(params)
    #Varável do tipo array, onde os resultados da query seguinte serão armazenados, se houverem dados a armazenar
    results = Array.new
    #Query onde, os registros são buscados no banco de dados. As buscas são realizadas tanto pelo nome do Órgão, quanto pelo código do concurso
    public_tenders = any_of({:department => /.*#{params[:q].upcase}.*/ }, {code: /.*#{params[:q]}.*/}).only(:id, :department, :code)
    #Atribuição dos resultados da query no array declarado acima
    public_tenders.each do |public_tender|
      #Foi utilizada a biblioteca Select2, que implementa o recurso 'Auto Complete'.
      #Os campos 'id' e 'text' são necessários para que o usuário escolha a opção correta e o sistema entanda a qual registro pertence a opção selecionada
      results << {
        id: public_tender.id.to_s,
        text: public_tender.department + " | " + public_tender.code
      }
    end
    {results: results}
  end
end
