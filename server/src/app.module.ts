import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ENVIRONMENTS } from './config';

@Module({
  imports: [MongooseModule.forRoot(ENVIRONMENTS.DB.DB_URI)],
  controllers: [AppController],
  providers: [AppService]
})
export class AppModule {}
