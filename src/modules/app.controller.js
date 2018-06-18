import express from "express";
import AppService from "./app.service";

class AppController {
  constructor() {
    this.router = express.Router();

    this.router.get("/candidatos/:cod_concurso", (req, res) => {
      AppService.listarCandidatos(req.params.cod_concurso)
      .then((data) => {
        res.json(data);
      });
    });

    this.router.get("/concursos/:cpf_candidato", (req, res) => {
      AppService.listarConcursos(req.params.cpf_candidato)
      .then((data) => {
        res.json(data);
      });
    });
  }
}

export default new AppController;
