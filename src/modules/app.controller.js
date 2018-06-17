import express from "express";
import AppService from "./app.service";

class AppController {
  constructor() {
    this.router = express.Router();

    this.router.get("/candidatos/:cod_concurso", (req, res) => {
      res.json(AppService.listarCandidatos(req.params.cod_concurso));
    });

    this.router.get("/concursos/:cpf_candidato", (req, res) => {
      res.json(AppService.listarConcursos(req.params.cpf_candidato));
    });
  }
}

export default AppController;
