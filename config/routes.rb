Rails.application.routes.draw do
  root "homes#index"

  scope "/candidatos" do
    get "/", to: "candidates#index"
    #get "/concursos-afins", to: "candidates#public_tenders"
    get "/:id/concursos", to: "candidates#public_tenders", as: :public_tenders
    get "/buscar", to: "candidates#search"
  end

  scope "/concursos" do
    get "/", to: "public_tenders#index"
    #get "/candidatos-afins", to: "public_tenders#candidates"
    get "/:id/candidatos", to: "public_tenders#candidates", as: :candidates
    get "/buscar", to: "public_tenders#search"
  end  
end
