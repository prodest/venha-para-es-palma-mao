import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { ICandidate } from '../interfaces';
import { BaseModelService } from '../providers';
import { CreateCandidateDto } from './create-candidate.dto';

@Injectable()
export class CandidatesService extends BaseModelService<ICandidate> {
  constructor(
    @InjectModel('Candidate') protected readonly model: Model<ICandidate>
  ) {
    super();
  }

  /**
   * @description create a candidate
   * @author David Vilaça
   * @date 2019-03-23
   * @param {CreateCandidateDto} data
   * @returns {Promise<ICandidate>}
   * @memberof CandidatesService
   */
  public async create(data: CreateCandidateDto): Promise<ICandidate> {
    const instance = new this.model(data);
    const res = await instance.save();
    return res;
  }

  /**
   * @description find a candidate by cpf
   * @author David Vilaça
   * @date 2019-03-27
   * @param {string} cpf
   * @returns {(Promise<ICandidate | null>)}
   * @memberof CandidatesService
   */
  public async findByCpf(cpf: string): Promise<ICandidate | null> {
    const res = (await this.find({ cpf }))[0];
    return res || null;
  }
}
