Rails.application.routes.draw do
  root "homes#index"

  scope "/candidatos" do
    get "/", to: "candidates#index"
    get "/concursos-afins", to: "candidates#public_tenders"
  end

  scope "/concursos" do
    get "/", to: "public_tenders#index"
    get "/candidatos-afins", to: "public_tenders#candidates"
  end
end
