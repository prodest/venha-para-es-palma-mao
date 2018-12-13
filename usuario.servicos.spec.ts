import { Test, TestingModule } from '@nestjs/testing';
import { UsuarioService } from './usuario.servicos';

describe('UsuarioService', () => {

  let service: UsuarioService;
  let retornoEsperado: string;
  retornoEsperado = "ivonildo bispo dos santos 13/12/1986 025.321.255-45 [programador]"+
  "Paulo bispo guimaraes 13/04/1988 025.321.255-45 [Desenvolvedor]"+
  "sergio bispo  santos 11/12/1999 025.321.255-45 [programador-web]";


  beforeAll(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [UsuarioService],
    }).compile();


    service = module.get<UsuarioService>(UsuarioService);
  });


  it('Teste do nome()', async () => {
    expect(await service.nome()).toBe("Deus maravilhoso: ");//resultado esperado da funçao "nome"
  });


  it('Teste do arquivoTXT()', async () => {
    let retorno = await service.test();
    expect(retorno[0]).toBe("ivonildo");//resultado esperado da funçao "nome"
  }); 
  
  it('Teste do Split()', async () => {
    let retorno = await service.test();
    expect(retorno[0]).toBe("ivonildo");//resultado esperado da funçao "nome"
  });

});
