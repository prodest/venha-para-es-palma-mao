import { INestApplication } from '@nestjs/common';
import { Test } from '@nestjs/testing';
import * as request from 'supertest';
import { AppModule } from './../src/app.module';

describe('AppController (e2e)', () => {
  let app: INestApplication;

  beforeAll(async () => {
    const moduleFixture = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = moduleFixture.createNestApplication();
    await app.init();
  });

  it('/ (GET)', () => {
    return request(app.getHttpServer())
      .get('/')
      .expect(200)
      .expect('Hello World!');
  });

  it('/ping (ALL)', () => {
    const requests = [
      request(app.getHttpServer()).get('/ping'),
      request(app.getHttpServer()).post('/ping'),
      request(app.getHttpServer()).put('/ping'),
      request(app.getHttpServer()).delete('/ping'),
      request(app.getHttpServer()).options('/ping')
    ];
    return Promise.all(requests.map(req => req.expect(200).expect('pong')));
  });
});
