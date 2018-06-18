import fs from "fs";
import CandidatoService from "./src/modules/candidatos/candidato.service";
import ConcursoService from "./src/modules/concursos/concurso.service";

fs.readFile("candidatos.txt", "utf-8", (err, data) => {
  if (err) console.log(err);
  
  var linhas = data.split(/\r?\n/);
  var candidatos = [];
  linhas.forEach((linha) => {
    if (linha) {
      let i = 0;
      while (!"0123456789".includes(linha.charAt(i++))) {}
      var nome = linha.slice(0, i-2);
      var nascimento = linha.slice(i-1, i+9);
      i += 10;
      var cpf = linha.slice(i, i+14);
      i += 15;
      var profissoes = [];
      linha.slice(i).slice(1, -1).split(",").forEach((s)=>{
        profissoes.push(s.trim());
      });
      
      candidatos.push({
        nome: nome,
        nascimento: nascimento,
        cpf: cpf,
        profissoes: profissoes
      });
    }
  });
  CandidatoService.createMany(candidatos);
});

fs.readFile("concursos.txt", "utf-8", (err, data) => {
  if (err) console.log(err);
  
  var linhas = data.split(/\r?\n/);
  var concursos = [];
  linhas.forEach((linha) => {
    if (linha) {
      let i = 0;
      while (!"0123456789".includes(linha.charAt(i++))) {}
      var orgao = linha.slice(0, i-2);
      var j = i-1;
      
      while (!" ".includes(linha.charAt(i++))) {}
      var edital = linha.slice(j, i-1);
      
      j = i;
      while (!" ".includes(linha.charAt(i++))) {}
      var codigo = linha.slice(j, i-1);
      
      var vagas = [];
      linha.slice(i).slice(1,-1).split(",").forEach((s)=>{
        vagas.push(s.trim());
      });

      concursos.push({
        orgao: orgao,
        edital: edital,
        codigo: codigo,
        vagas: vagas
      });
    }
  });

  ConcursoService.createMany(concursos);
});
