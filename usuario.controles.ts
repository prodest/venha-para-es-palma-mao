'use strict';
import { Controller, Get, Post, Req, Body } from '@nestjs/common'; //importado de app.controller.ts original
import { UsuarioService } from '../servicos/usuario.servicos';
import { Usuario } from '../modelos/usuario.modelos';

@Controller( 'usuarios' )
export class UsuariosController {
    constructor( private readonly Service: UsuarioService ) { }

    @Get()
    raiz () {
        return ( "So jesus pode te amar verdadeiramente!" );
    }

    @Post()
    salvar ( @Body() params: Usuario ) {
        console.log( `Controller recebeu o objeto: ${JSON.stringify( params )}` )
        this.Service.criar( params );
    }

    @Get( 'listar' )  //chamando e passando as rotas
    async lista_a_galera () {
        return this.Service.listar_usuarios();
    }
    @Get( '/ok' )    //chamando e passando as rotas
    async usu () {
        return this.Service.nome();
    }
    @Get( '/teste' )   
    async testar () {
        return this.Service.test();
    }
}