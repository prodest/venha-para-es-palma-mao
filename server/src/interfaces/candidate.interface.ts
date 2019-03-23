import { Document } from 'mongoose';

export interface ICandidate extends Document {
  name: string;
  dateOfBirth: string;
  cpf: string;
  professions: Array<string>;
}
