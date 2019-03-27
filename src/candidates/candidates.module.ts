import { forwardRef, Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { ConcoursesModule } from '../concourses/concourses.module';
import { CandidateController } from './candidate.controller';
import { CandidateSchema } from './candidate.schema';
import { CandidatesService } from './candidates.service';

@Module({
  imports: [
    MongooseModule.forFeature([
      {
        name: 'Candidate',
        schema: CandidateSchema
      }
    ]),
    forwardRef(() => ConcoursesModule)
  ],
  providers: [CandidatesService],
  controllers: [CandidateController],
  exports: [CandidatesService]
})
export class CandidatesModule {}
