import { readFileSync } from 'fs';
import * as mongoose from 'mongoose';
import * as papa from 'papaparse';
import { CandidateSchema } from '../candidates/candidate.schema';
import { ConcourseSchema } from '../concourses/concourse.schema';
import { ENVIRONMENTS } from '../config';
import { ICandidate, IConcourse } from '../interfaces';

// tslint:disable-next-line: variable-name
let Candidate: mongoose.Model<ICandidate>;
// tslint:disable-next-line: variable-name
let Concourse: mongoose.Model<IConcourse>;
const candidateRegex = /([a-zA-Z]+\s[a-zA-Z]+)\s([\d]{2}\/[\d]{2}\/[\d]{4})\s([\d]{3}\.[\d]{3}\.[\d]{3}\-[\d]{2})\s(\[.+\])/g;
const concourseRegex = /([A-Z]+)\s([\d]{1,2}\/[\d]{4})\s([\d]+)\s(\[.+\])/g;

/**
 * @description get txt input file and return a parsed csv
 * @author David Vilaça
 * @date 2019-03-27
 * @param {string} path
 * @param {RegExp} regex
 * @param {string} [encoding='UTF-8']
 * @returns {Promise<papa.ParseResult>}
 */
async function txtToCsv(
  path: string,
  regex: RegExp,
  encoding: string = 'UTF-8'
): Promise<papa.ParseResult> {
  const csv = readFileSync(path, { encoding })
    .trim()
    .replace(regex, '$1;$2;$3;$4');
  return papa.parse(csv, {
    delimiter: ';',
    header: false
  });
}

/**
 * @description create candidate recursive
 * @author David Vilaça
 * @date 2019-03-27
 * @param {string[]} csvData
 * @returns {Promise<ICandidate[]>}
 */
async function createCandidate(
  csvData: string[][],
  _: Array<ICandidate> = []
): Promise<ICandidate[]> {
  if (csvData.length === 0) {
    return _;
  }
  const data = csvData[0];
  if (data.length === 4) {
    const candidate = new Candidate({
      name: data[0],
      dateOfBirth: data[1].replace(
        /([\d]{2})\/([\d]{2})\/([\d]{4})/,
        '$3-$2-$1'
      ),
      cpf: data[2],
      professions: data[3].replace(/(\[|\])/g, '').split(', ')
    });
    return createCandidate(csvData.slice(1), [..._, await candidate.save()]);
  }
  return createCandidate(csvData.slice(1), [..._]);
}

/**
 * @description create concourse recursive
 * @author David Vilaça
 * @date 2019-03-27
 * @param {string[][]} csvData
 * @returns {Promise<IConcourse[]>}
 */
async function createConcourse(
  csvData: string[][],
  _: Array<IConcourse> = []
): Promise<IConcourse[]> {
  if (csvData.length === 0) {
    return _;
  }
  const data = csvData[0];
  if (data.length === 4) {
    const concourse = new Concourse({
      organ: data[0],
      edital: data[1],
      code: data[2],
      vacancies: data[3].replace(/(\[|\])/g, '').split(', ')
    });
    return createConcourse(csvData.slice(1), [..._, await concourse.save()]);
  }
  return createConcourse(csvData.slice(1), [..._]);
}

/**
 * @description bootstrap seed
 * @author David Vilaça
 * @date 2019-03-27
 */
async function bootstrap() {
  await mongoose.connect(ENVIRONMENTS.DB.DB_URI, {
    useNewUrlParser: true
  });
  Candidate = mongoose.model<ICandidate>('Candidate', CandidateSchema);
  Concourse = mongoose.model<IConcourse>('Concourse', ConcourseSchema);
  const candidates = await txtToCsv(
    `${__dirname}/../../../candidatos.txt`,
    candidateRegex
  );
  const concourses = await txtToCsv(
    `${__dirname}/../../../concursos.txt`,
    concourseRegex
  );
  await createCandidate(candidates.data);
  await createConcourse(concourses.data);
}

bootstrap().then(
  () => process.exit(0),
  err => {
    console.error(err);
    process.exit(1);
  }
);
