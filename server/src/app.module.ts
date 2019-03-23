import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CandidatesModule } from './candidates/candidates.module';
import { ENVIRONMENTS } from './config';

@Module({
  imports: [MongooseModule.forRoot(ENVIRONMENTS.DB.DB_URI), CandidatesModule],
  controllers: [AppController],
  providers: [AppService]
})
export class AppModule {}
