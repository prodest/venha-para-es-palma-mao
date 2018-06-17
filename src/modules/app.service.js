import CandidatoService from "./candidatos/candidato.service";
import ConcursoService from "./concursos/concurso.service";

class AppService {
  static listarCandidatos(codigo_concurso) {
    let concurso;
    ConcursoService.read(codigo_concurso, (found) => {
      concurso = found;
    });

    let candidatos;
    CandidatoService.readAll((found) => {
      candidatos = found;
    });

    let candidatosPossiveis = [];
    candidatos.forEach((candidato) => {
      candidato.profissoes.forEach((profissao) => {
        if (profissao in concuso.vagas) {
          candidatosPossiveis.push({
            nome: candidato.nome,
            nascimento: candidato.nascimento,
            cpf: candidato.cpf
          });
        }
      });
    });
    return candidatosPossiveis;    
  }

  static listarConcursos(cpf_candidato) {
    let candidato;
    CandidatoService.read(cpf_candidato, (found) => {
      candidato = found;
    });

    let concursos;
    ConcursoService.readAll((found) =>{
      concursos = found;
    });

    let concursosPossiveis;
    concursos.forEach((concurso) => {
      concurso.vagas.forEach((vaga) => {
        if (vaga in candidato.profissoes) {
          concursosPossiveis.push({
            orgao: concurso.orgao,
            codigo: concurso.codigo,
            edital: concurso.edital
          });
        }
      });
    });
    return concursosPossiveis;
  }
}

export default AppService;
