# API NEST STARTER

[![CircleCI](https://circleci.com/gh/davidpvilaca/api-nest-starter.svg?style=shield&circle-token=339c2a2d8ec08e4de36981ad0121f0ed3a155b7a)](https://circleci.com/gh/davidpvilaca/api-nest-starter)
[![dependencies Status](https://david-dm.org/davidpvilaca/api-nest-starter/status.svg)](https://david-dm.org/davidpvilaca/api-nest-starter)
[![NodeJS](https://img.shields.io/badge/node-v10.12.0-brightgreen.svg)](#prerequisites)

## Introduction

Start an API using NestJS framework with all the tedious commit lint settings and so on.

### Prerequisites

* [NodeJS (^v10.12.0)](https://nodejs.org/en/)
* [Yarn (^v1.10.0)](https://yarnpkg.com/en) (preferably) or Npm (^v6.4.0)
* [Nest CLI](https://docs.nestjs.com/cli/overview) (optional)

## Getting Started

1. Install dependencies: `yarn install`
2. Configure your .env environments based on [.env.example](.env.example)
3. Start your application:
    1. Development: `yarn start`
    2. Watch mode: `yarn start:dev`
    3. Production: `yarn start:prod` (run the build before: `yarn build`)

## Deployment

CircleCI configured with 4 jobs:

1. [__Install dependencies__]: Install all dependencies with _yarn_ and save cache.
2. [__Lint__]: check the link for all code.
3. [__Tests__]: run all unit tests and e2e.
4. [__Build__]: criar aplicativo com o _Docker_.

Note: deploy job not configured because it varies for each type of application.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/xdevelsistemas/cpcon-api/tags).

## Changelog

See [__CHANGELOG__](CHANGELOG.md)

## Authors

* **David Vilaça** - [davidpvilaca](https://github.com/davidpvilaca)

## License

Every code patch accepted in taiga codebase is licensed under [MIT](LICENSE).
