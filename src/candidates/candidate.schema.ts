import * as mongoose from 'mongoose';

// tslint:disable-next-line: variable-name
export const CandidateSchema = new mongoose.Schema({
  name: String,
  dateOfBirth: Date,
  cpf: String,
  professions: [String]
});
