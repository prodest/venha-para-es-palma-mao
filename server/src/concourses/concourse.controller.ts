import {
  Body,
  Controller,
  Delete,
  Get,
  HttpException,
  Param,
  Post,
  Put
} from '@nestjs/common';
import { CandidatesService } from '../candidates/candidates.service';
import { IConcourse } from '../interfaces';
import { ConcoursesService } from './concourses.service';
import { CreateConcourseDto } from './create-concourse.dto';

@Controller('concourse')
export class ConcourseController {
  /**
   * Creates an instance of ConcoursesController.
   * @author David Vilaça
   * @date 2019-03-27
   * @param {ConcoursesService} concourseService
   * @param {CandidatesService} candidateService
   * @memberof ConcoursesController
   */
  constructor(
    private readonly concourseService: ConcoursesService,
    private readonly candidateService: CandidatesService
  ) {}

  /**
   * @description create concourse route
   * @author David Vilaça
   * @date 2019-03-27
   * @param {CreateConcourseDto} dto
   * @returns
   * @memberof CandidateController
   */
  @Post()
  async create(@Body() dto: CreateConcourseDto) {
    const result = await this.concourseService.create(dto);
    return result;
  }

  /**
   * @description get all concourses route
   * @author David Vilaça
   * @date 2019-03-27
   * @returns
   * @memberof CandidateController
   */
  @Get()
  async concourses() {
    const result = await this.concourseService.find({});
    return result;
  }

  /**
   * @description update concourse route
   * @author David Vilaça
   * @date 2019-03-27
   * @param {string} id
   * @param {IConcourse} candidate
   * @returns
   * @memberof CandidateController
   */
  @Put(':id')
  async update(@Param('id') id: string, @Body() candidate: IConcourse) {
    await this.validateId(id);
    const obj = Object.assign({}, candidate, { _id: id });
    const result = await this.concourseService.save(obj);
    return result;
  }

  /**
   * @description delete concourse route
   * @author David Vilaça
   * @date 2019-03-27
   * @param {string} id
   * @returns
   * @memberof CandidateController
   */
  @Delete(':id')
  async delete(@Param('id') id: string) {
    await this.validateId(id);
    const result = await this.concourseService.delete(id);
    return result;
  }

  @Get('possible-candidates/:concourseCode')
  async possibleCandidates(@Param('concourseCode') code: number) {
    const concourse = await this.concourseService.findByCode(Number(code));
    if (!concourse) {
      throw new HttpException(`Concourse with code "${code}" not found.`, 400);
    }
    const candidates = await this.candidateService.find({
      professions: {
        $in: [...concourse.vacancies]
      }
    });
    return candidates;
  }

  /**
   * @description validate id exists in db
   * @author David Vilaça
   * @date 2019-03-27
   * @private
   * @param {string} id
   * @returns {Promise<boolean>}
   * @memberof CandidateController
   */
  private async validateId(id: string): Promise<void> {
    const doc = await this.concourseService.findOne(id);
    if (!doc) {
      throw new HttpException(`Concourse with id "${id} not found.`, 400);
    }
  }
}
