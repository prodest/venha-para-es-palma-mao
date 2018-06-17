import Concurso from "./concurso";
import ConcursoDAO from "./concurso.dao";

class ConcursoService {
  static create(orgao, edital, codigo, vagas) {
    var concurso = new Concurso(orgao, edital, codigo, vagas);
    ConcursoDAO.insert(concurso);
    return concurso;
  }

  static createMany(concursos) {
    ConcursoDAO.insertMany(concursos);
  }

  static read(codigo) {
    return ConcursoDAO.findOne(codigo);
  }

  static readAll(callback) {
    return ConcursoDAO.findAll();
  }
}

export default ConcursoService;
