import { All, Controller } from '@nestjs/common';

/**
 * @description Application home controller
 * @author David Vilaça
 * @date 2018-09-29
 * @export
 * @class AppController
 */
@Controller()
export class AppController {
  /**
   * @description ping route to health check
   * @author David Vilaça
   * @date 2019-03-27
   * @returns {string}
   * @memberof AppController
   */
  @All('ping')
  ping(): string {
    return 'pong';
  }
}
