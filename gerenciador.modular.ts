'use strict';
import { Module } from '@nestjs/common'; //importado de app.module.ts original
import { TypeOrmModule } from '@nestjs/typeorm';
import { UsuariosController } from './controles/usuario.controles';
import { UsuarioService } from './servicos/usuario.servicos';
import { TarefasController } from './controles/tarefas.controles';
import { TarefasService } from './servicos/tarefas.servicos';




@Module( {
    imports: [ TypeOrmModule.forRoot( { //nescessario para conectar ao banco
        type: 'postgres',  
        host: '172.17.0.1',//important
        port: 5432,
        username: 'ivonildo',
        password: '123',
        database: 'tarefas',//nome do banco
        entities: [ __dirname + '/**/*.modelos{.ts,.js}' ],//passa o nome da pasta onde estao os modelos 
        synchronize: true,
    } ) ],
    controllers: [ UsuariosController, TarefasController ],//recebe as entidades dos controles
    providers: [ UsuarioService, TarefasService ]          //recebe as entidades dos serviços
} )

export class GerenciadorModular { }//classe para gerenciar a conexão "e o app.module.ts original tem que impotar essa classe"