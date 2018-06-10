require "csv"
namespace :import do
  desc "Import Candidates"
  task import_candidates: :environment do
    CSV.foreach("#{Rails.root}/candidatos.csv", :headers => true, :col_sep => ';') do |row|
      params = {
        name: row['name'],
        birthdate: row['birthdate'],
        document_number: row['document_number'],
        tags: row['tags'].split(",")
      }
      puts Candidate.create(params)
    end
  end
  desc "Import Public Tenders"
  task import_public_tenders: :environment do
    CSV.foreach("#{Rails.root}/concursos.csv", :headers => true, :col_sep => ';') do |row|
      params = {
        department: row['department'],
        code: row['code'],
        document_number: row['document_number'],
        tags: row['tags'].split(",")
      }
      puts PublicTender.create(params)
    end
  end
end
