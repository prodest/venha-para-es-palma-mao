import { Module } from '@nestjs/common';
import { GerenciadorModular } from './gerenciador/gerenciador.modular';

@Module( {
  imports: [ GerenciadorModular ]
} )
export class AppModule {}
