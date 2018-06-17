import CandidatoService from "./candidatos/candidato.service";
import ConcursoService from "./concursos/concurso.service";

class AppService {
  static listarCandidatos(codigo_concurso) {
    var concurso;
    var candidatos;
    var candidatosPossiveis = [];

    return ConcursoService.read(codigo_concurso)
    .then((res) => {
      console.log("listar candidatos - read concurso", res);
      concurso = res;
      return CandidatoService.readAll();
    })
    .then((res) => {
      candidatos = res;
      return;
    })
    .then(() => {
      candidatos.forEach((candidato) => {
        candidato.profissoes.forEach((profissao) => {
          if (concurso.vagas.includes(profissao)) {
            candidatosPossiveis.push({
              nome: candidato.nome,
              nascimento: candidato.nascimento,
              cpf: candidato.cpf
            });
          }
        });
      });
      return candidatosPossiveis;
    });
  }

  static listarConcursos(cpf_candidato) {
    var candidato;
    var concursos;
    var concursosPossiveis = [];

    return CandidatoService.read(cpf_candidato)
    .then((res) => {
      candidato = res;
      return ConcursoService.readAll();
    })
    .then((res) => {
      concursos = res;
      return;
    })
    .then(() => {
      concursos.forEach((concurso) => {
        concurso.vagas.forEach((vaga) => {
          if (candidato.profissoes.includes(vaga)) {
            concursosPossiveis.push({
              orgao: concurso.orgao,
              codigo: concurso.codigo,
              edital: concurso.edital
            });
          }
        });
      });
      return concursosPossiveis;
    });
  }
}

export default AppService;
