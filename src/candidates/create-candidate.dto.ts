export class CreateCandidateDto {
  readonly name: string;
  readonly dateOfBirth: string;
  readonly cpf: string;
  readonly professions: Array<string>;
}
