import { NestFactory } from '@nestjs/core';
import { config as dotEnvConfig } from 'dotenv';
import { AppModule } from './app.module';

// .env configuration
dotEnvConfig();

/**
 * @description Bootstrap the application
 * @author David Vilaça
 * @date 2018-09-29
 */
async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  await app.listen(process.env.PORT || 3000);
}
bootstrap();
