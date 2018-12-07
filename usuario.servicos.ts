import { Injectable } from '@nestjs/common'; //importado de app.service.ts original
import { Usuario } from '../modelos/usuario.modelos';//importa classe do modelos "Usuario"
//import {readFileSync} from "fs"
import * as fs from 'fs';
@Injectable()
export class UsuarioService { //nome da nossa classe igual app.service.ts original

    async listar_usuarios () {
        return await Usuario.find();
    }

    async criar ( usuario: Usuario ) {
        console.log( `serviço recebeu o objeto: ${JSON.stringify( Usuario )}` )
        return await Usuario.insert( usuario );
    }

    async nome (  ){ 
      const a="Deus maravilhoso: ";
        return a;
    }
    async test (){ 
       
        // trabalhando com arquivos txt
          let arquivo = fs.readFileSync("./arquivo.txt",'utf8');
          var linhas = arquivo.split(" ",4);
          console.log("Começa aqui /n");
             console.log(arquivo);
             console.log("Termina aqui");

        
       linhas.forEach(function(linha){
        linhas = arquivo.split(" ",4);
        console.log("Começa aqui o SPLIT /n");
             console.log(linhas);
             console.log("Termina aqui o SPLIT");
    
     })
    }
   
}