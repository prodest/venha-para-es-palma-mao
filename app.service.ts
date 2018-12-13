import { Injectable } from '@nestjs/common';

@Injectable()
export class AppService {
  root(): string {
    return 'Olá mundo, primeiro teste!';
  }
  
}
