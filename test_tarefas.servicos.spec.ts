'use strict';
import { Test, TestingModule } from '@nestjs/testing';
import { TarefasService } from './tarefas.servicos';
 jest.mock('./tarefas.servicos');

describe('TarefasService', () => {

  let service: TarefasService;


  beforeAll(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [TarefasService],
    }).compile();


    service = module.get<TarefasService>(TarefasService);
  });
  it('Teste do novo()', async () => {
    expect(await service.novo()).toBe("45 / 6 = 7.5");
    console.log("45 / 6 = 7.5");
  });

  it('Teste listar_tarefas()', async () => {
    let resposta = await service.listar_tarefas();
    expect(resposta.length).toBeGreaterThan(0);
    expect(resposta[0].nome).toBe("prodest");
    console.log(resposta);
  });



});
