import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
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
    ])
  ],
  providers: [CandidatesService],
  controllers: [CandidateController],
  exports: [CandidatesService]
})
export class CandidatesModule {}
