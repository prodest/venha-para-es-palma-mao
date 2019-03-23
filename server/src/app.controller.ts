import { All, Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';

/**
 * @description Application home controller
 * @author David Vilaça
 * @date 2018-09-29
 * @export
 * @class AppController
 */
@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  async root(): Promise<string> {
    const rootMessage = await this.appService.root();
    return rootMessage;
  }

  @All('ping')
  ping(): string {
    return 'pong';
  }
}
