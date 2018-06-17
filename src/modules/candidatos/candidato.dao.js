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

  insertMany(candidatos) {
    MongoDB.insertMany(this.collection, candidatos);
  }

  findOne(cpf, callback) {
    MongoDB.find(this.collection, {cpf: cpf}, (docs) => {
      if (!docs) {
        callback(null);
        return;
      }
      const result = docs[0];
      callback(new Candidato(result.nome, result.nascimento, result.cpf, result.profissoes, result._id));
    });
  }

  findAll(callback) {
    MongoDB.find(this.collection, {}, (docs) => {
      if (!docs[0]) {
        callback(null);
        return;
      }
      let candidatos = [];
      docs.map((doc) => {
        candidatos.push(new Candidato(doc.nome, doc.nascimento, doc.cpf, doc.profissoes, doc._id));
      });
      callback(candidatos);
    });
  }
}

export default new CandidatoDAO;
