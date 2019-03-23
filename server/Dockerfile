FROM node:10-alpine

ADD . /app/service

WORKDIR /app/service

RUN yarn install --dev

RUN yarn build

CMD ["yarn", "start:prod"]
