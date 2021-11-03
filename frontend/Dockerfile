FROM node:lts-alpine as build-stage

WORKDIR /app

COPY package*.json ./

RUN npm install
RUN npm install vue-router

COPY . .

RUN npm run build

FROM nginx:stable-alpine as production-stage
COPY ./.nginx/nginx.conf /etc/nginx/conf.d/default.conf
RUN rm -rf /usr/share/nginx/html/*

COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

