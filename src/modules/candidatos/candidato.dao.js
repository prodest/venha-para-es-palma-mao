import MongoDB from "./../../lib/mongodb";
import Candidato from "./candidato";

class CandidatoDAO {
  constructor() {
    this.collection = "candidatos";
    this.MongoDB = new MongoDB;
  }

  insert(candidato) {
    const data = {
      nome: candidato.nome,
      nascimento: candidato.nascimento,
      cpf: candidato.cpf,
      profissoes: candidato.profissoes
    };
    this.MongoDB.insert(this.collection, data);
    return true;
  }

  insertMany(candidatos) {
    this.MongoDB.insertMany(this.collection, candidatos);
  }

  findOne(cpf) {
    return this.MongoDB.find(this.collection, {cpf: cpf})
    .then((docs) => {
      var result = docs[0];
      return new Candidato(result.nome, result.nascimento, result.cpf, result.profissoes, result._id);
    });
  }

  findAll() {
    return this.MongoDB.find(this.collection, {})
    .then((docs) => {
      var candidatos = [];
      docs.map((doc) => {
        candidatos.push(new Candidato(doc.nome, doc.nascimento, doc.cpf, doc.profissoes, doc._id));
      });
      return candidatos;
    });
  }
}

export default new CandidatoDAO;
