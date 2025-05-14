FROM node:23-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install -g @angular/cli && \
    npm ci

COPY . .
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200"]