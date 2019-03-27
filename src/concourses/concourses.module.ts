import { forwardRef, Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { CandidatesModule } from '../candidates/candidates.module';
import { ConcourseController } from './concourse.controller';
import { ConcourseSchema } from './concourse.schema';
import { ConcoursesService } from './concourses.service';

@Module({
  imports: [
    MongooseModule.forFeature([
      {
        name: 'Concourse',
        schema: ConcourseSchema
      }
    ]),
    forwardRef(() => CandidatesModule)
  ],
  providers: [ConcoursesService],
  controllers: [ConcourseController],
  exports: [ConcoursesService]
})
export class ConcoursesModule {}
