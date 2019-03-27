import { Document } from 'mongoose';

export interface IConcourse extends Document {
  organ: string;
  edital: string;
  code: string;
  vacancies: Array<string>;
}
