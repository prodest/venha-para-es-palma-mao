import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { IConcourse } from '../interfaces';
import { BaseModelService } from '../providers';
import { CreateConcourseDto } from './create-concourse.dto';

@Injectable()
export class ConcoursesService extends BaseModelService<IConcourse> {
  /**
   * Creates an instance of ConcoursesService.
   * @author David Vilaça
   * @date 2019-03-27
   * @param {Model<IConcourse>} model
   * @memberof ConcoursesService
   */
  constructor(
    @InjectModel('Concourse') protected readonly model: Model<IConcourse>
  ) {
    super();
  }

  /**
   * @description create a candidate
   * @author David Vilaça
   * @date 2019-03-27
   * @param {CreateConcourseDto} data
   * @returns {Promise<IConcourse>}
   * @memberof CandidatesService
   */
  public async create(data: CreateConcourseDto): Promise<IConcourse> {
    const instance = new this.model(data);
    const res = await instance.save();
    return res;
  }

  /**
   * @description find a document by code
   * @author David Vilaça
   * @date 2019-03-27
   * @param {number} code
   * @returns {(Promise<IConcourse | null>)}
   * @memberof ConcoursesService
   */
  public async findByCode(code: number): Promise<IConcourse | null> {
    const res = (await this.find({ code }))[0];
    return res || null;
  }
}
