import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { ConcourseSchema } from './concourse.schema';
import { ConcoursesController } from './concourses.controller';
import { ConcoursesService } from './concourses.service';

@Module({
  imports: [
    MongooseModule.forFeature([
      {
        name: 'Concourse',
        schema: ConcourseSchema
      }
    ])
  ],
  providers: [ConcoursesService],
  controllers: [ConcoursesController],
  exports: [ConcoursesService]
})
export class ConcoursesModule {}
