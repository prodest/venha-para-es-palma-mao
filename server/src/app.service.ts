import { Injectable } from '@nestjs/common';

/**
 * @description Application sample service
 * @author David Vilaça
 * @date 2018-09-29
 * @export
 * @class AppService
 */
@Injectable()
export class AppService {
  async root(): Promise<string> {
    return 'Hello World!';
  }
}
