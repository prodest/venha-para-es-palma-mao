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

  static read(cpf, callback) {
    CandidatoDAO.findOne(cpf, callback);
  }

  static readAll(callback) {
    CandidatoDAO.findAll(callback);
  }
}

export default CandidatoService;
