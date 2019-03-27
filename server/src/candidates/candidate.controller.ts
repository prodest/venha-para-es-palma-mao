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
import { ConcoursesService } from '../concourses/concourses.service';
import { ICandidate } from '../interfaces';
import { CandidatesService } from './candidates.service';
import { CreateCandidateDto } from './create-candidate.dto';

@Controller('candidate')
export class CandidateController {
  /**
   * Creates an instance of CandidateController.
   * @author David Vilaça
   * @date 2019-03-23
   * @param {CandidatesService} candidateService
   * @param {ConcoursesService} concoursesService
   * @memberof CandidateController
   */
  constructor(
    private readonly candidateService: CandidatesService,
    private readonly concoursesService: ConcoursesService
  ) {}

  /**
   * @description create candidate route
   * @author David Vilaça
   * @date 2019-03-23
   * @param {CreateCandidateDto} dto
   * @returns
   * @memberof CandidateController
   */
  @Post()
  async create(@Body() dto: CreateCandidateDto) {
    const result = await this.candidateService.create(dto);
    return result;
  }

  /**
   * @description get all candidates route
   * @author David Vilaça
   * @date 2019-03-23
   * @returns
   * @memberof CandidateController
   */
  @Get()
  async candidates() {
    const result = await this.candidateService.find({});
    return result;
  }

  /**
   * @description update candidate route
   * @author David Vilaça
   * @date 2019-03-23
   * @param {string} id
   * @param {ICandidate} candidate
   * @returns
   * @memberof CandidateController
   */
  @Put(':id')
  async update(@Param('id') id: string, @Body() candidate: ICandidate) {
    await this.validateId(id);
    const obj = Object.assign({}, candidate, { _id: id });
    const result = await this.candidateService.save(obj);
    return result;
  }

  /**
   * @description delete candidate route
   * @author David Vilaça
   * @date 2019-03-23
   * @param {string} id
   * @returns
   * @memberof CandidateController
   */
  @Delete(':id')
  async delete(@Param('id') id: string) {
    await this.validateId(id);
    const result = await this.candidateService.delete(id);
    return result;
  }

  /**
   * @description get suggestions of concourses for candidade by cpf
   * @author David Vilaça
   * @date 2019-03-27
   * @param {string} cpf
   * @returns
   * @memberof CandidateController
   */
  @Get('suggestions/:cpf')
  async suggestions(@Param('cpf') cpf: string) {
    const candidate = await this.candidateService.findByCpf(cpf);
    if (!candidate) {
      throw new HttpException(`Candidate with cpf "${cpf}" not found.`, 400);
    }
    const concourses = await this.concoursesService.find({
      vacancies: {
        $in: [...candidate.professions]
      }
    });
    return concourses;
  }

  /**
   * @description validate id exists in db
   * @author David Vilaça
   * @date 2019-03-23
   * @private
   * @param {string} id
   * @returns {Promise<boolean>}
   * @memberof CandidateController
   */
  private async validateId(id: string): Promise<void> {
    const doc = await this.candidateService.findOne(id);
    if (!doc) {
      throw new HttpException(`Document with id "${id}" not found.`, 400);
    }
  }
}
