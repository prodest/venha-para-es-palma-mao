import * as mongoose from 'mongoose';

// tslint:disable-next-line: variable-name
export const ConcourseSchema = new mongoose.Schema({
  organ: String,
  edital: String,
  code: String,
  vacancies: [String]
});
