class Candidate
  include Mongoid::Document
  include Mongoid::TimesTamps

  field :name
  field :cpf
  field :birthdate

  validates :name, presece: true
  validates :cpf, presece: true
  validates :birth_date, presence: true
  validate :validate_age
  validate :validate_cpf

  private

  def validate_age
    if birth_date.present? && birth_date > 18.years.ago.to_d
      errors.add(:birthdate, 'You should be over 18 years old.')
    end
  end

  def validate_cpf
    if cpf.present? && CPF.valid?(cpf)
      errors.add(:cpf, 'You should be over 18 years old.')
    end
  end
end
