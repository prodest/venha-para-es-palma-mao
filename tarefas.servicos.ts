'use strict';
import { Injectable } from '@nestjs/common'; //importado de app.service.ts original
import { Tarefa } from '../modelos/tarefas.modelos';//importa classe do modelos "Tarefa"


@Injectable() //nao muda
export class TarefasService { //nome da nossa classe igual app.service.ts original

   async listar_tarefas () {
        return await Tarefa.find();//busca resultado direto no banco de dados
    }

    async NovaTarefa ( tarefa: Tarefa ) {
        return await Tarefa.insert( tarefa );
    }

    async procuraTarefasPorUsuario ( id: number ) {
        return await Tarefa.find( { where: { usuario: id } } );
    }

  
    async novo (  ){
      let a:number=0;
      let b:number=0;

        for (let i = 5; i <= 10; i++) {
            a=a+i;
           b++;
          }
          return (a+' / '+b+' = '+a/b);
          
    }

   
}