import Candidato from "./candidato";
import CandidatoDAO from "./candidato.dao";

class CandidatoService {
  static create(nome, nascimento, cpf, profissoes) {
    var candidato = new Candidato(nome, nascimento, cpf, profissoes);
    CandidatoDAO.insert(candidato);
    return candidato;
  }

  static createMany(candidatos) {
    CandidatoDAO.insertMany(candidatos);
  }

  static read(cpf) {
    return CandidatoDAO.findOne(cpf);
  }

  static readAll() {
    return CandidatoDAO.findAll();
  }
}

export default CandidatoService;
