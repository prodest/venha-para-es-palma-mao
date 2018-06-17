import MongoDB from "./../../lib/mongodb";
import Concurso from "./concurso";
import { isUndefined } from "util";

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

  insertMany(concursos) {
    MongoDB.insertMany(this.collection, concursos);
  }

  findOne(codigo, callback) {
    MongoDB.find(this.collection, {codigo: codigo}, (docs) => {
      if (!docs) {
        callback(null);
        return;
      }
      const result = docs[0];
      callback(new Concurso(result.orgao, result.edital, result.codigo, result.vagas, result._id));
    });
  }

  findAll(callback) {
    MongoDB.find(this.collection, {}, (docs) => {
      if (!docs[0]) {
        callback(null);
        return;
      }
      let concursos = [];
      docs.map((doc) => {
        concursos.push(new Concurso(doc.orgao, doc.edital, doc.codigo, doc.vagas, doc._id));
      });
      callback(concursos);
    });
  }
}

export default new ConcursoDAO;
