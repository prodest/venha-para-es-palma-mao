import MongoDB from "./../../lib/mongodb";
import Concurso from "./concurso";

class ConcursoDAO {
  constructor() {
    this.collection = "concursos";
  }

  insert(concurso) {
    const data = {
      orgao: concurso.orgao,
      edital: concurso.edital,
      codigo: concurso.codigo,
      vagas: concurso.vagas
    };
    MongoDB.insert(this.collection, data);
    return true;
  }

  findOne(codigo, callback) {
    MongoDB.find(this.collection, {codigo: codigo}, (docs) => {
      if (!docs) {
        callback(null);
      }
      const result = docs[0];
      callback(new Concurso(result.orgao, result.edital, result.codigo, result.vagas, result._id));
    });
  }

  findAll(callback) {
    MongoDB.find(this.collection, {}, callback);
  }
}

export default new ConcursoDAO;
