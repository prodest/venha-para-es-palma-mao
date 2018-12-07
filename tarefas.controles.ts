
'use strict';
import { Controller, Get, Post, Body, Param } from '@nestjs/common';
import { TarefasService } from '../servicos/tarefas.servicos';
import { Tarefa } from '../modelos/tarefas.modelos';

@Controller('tarefas')
export class TarefasController {
  constructor(private readonly Service: TarefasService) {
      
  }
  @Post()
  salvar ( @Body() params: Tarefa ) {
      console.log( `Controller recebeu o objeto: ${JSON.stringify( params )}` )
      this.Service.NovaTarefa( params );
  }

  @Get( '/listar' )
  async lista_a_galera () {
      return this.Service.listar_tarefas();
  }

  @Get( '/vetor' )
  async vet () {
      return this.Service.novo();
  }

  @Get( '/usuario/:id' )
  async getById ( @Param() id ) {
      return await this.Service.procuraTarefasPorUsuario( id );
  }
}