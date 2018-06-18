import MongoDB from "./../../lib/mongodb";
import Concurso from "./concurso";

class ConcursoDAO {
  constructor() {
    this.collection = "concursos";
    this.MongoDB = new MongoDB;
  }

  insert(concurso) {
    const data = {
      orgao: concurso.orgao,
      edital: concurso.edital,
      codigo: concurso.codigo,
      vagas: concurso.vagas
    };
    return this.MongoDB.insert(this.collection, data);
  }

  insertMany(concursos) {
    return this.MongoDB.insertMany(this.collection, concursos);
  }

  findOne(codigo, callback) {
    return this.MongoDB.find(this.collection, {codigo: codigo})
    .then((docs) => {
      var result = docs[0];
      return new Concurso(result.orgao, result.edital, result.codigo, result.vagas, result._id);
    });
  }

  findAll(callback) {
    return this.MongoDB.find(this.collection, {})
    .then((docs) => {
      var concursos = [];
      docs.map((doc) => {
        concursos.push(new Concurso(doc.orgao, doc.edital, doc.codigo, doc.vagas, doc._id));
      });
      return concursos;
    });
  }
}

export default new ConcursoDAO;
