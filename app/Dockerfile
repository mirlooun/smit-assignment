FROM node:alpine

WORKDIR /app

COPY . /app

RUN npm install -g @angular/cli

RUN yarn install

CMD ["ng", "serve", "--host", "0.0.0.0"]
