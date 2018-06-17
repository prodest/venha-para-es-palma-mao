import MongoDB from "./../../lib/mongodb";
import Candidato from "./candidato";

class CandidatoDAO {
  constructor() {
    this.collection = "candidatos";
  }

  insert(candidato) {
    const data = {
      nome: candidato.nome,
      nascimento: candidato.nascimento,
      cpf: candidato.cpf,
      profissoes: candidato.profissoes
    };
    MongoDB.insert(this.collection, data);
    return true;
  }

  findOne(cpf, callback) {
    MongoDB.find(this.collection, {cpf: cpf}, (docs) => {
      if (!docs) {
        callback(null);
      }
      const result = docs[0];
      callback(new Candidato(result.nome, result.nascimento, result.cpf, result.profissoes, result._id));
    });
  }

  findAll(callback) {
    MongoDB.find(this.collection, {}, callback);
  }
}

export default new CandidatoDAO;
