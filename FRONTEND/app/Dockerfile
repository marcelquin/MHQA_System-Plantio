# Etapa 1: build do React
FROM node:18 as build

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Etapa 2: servir com Nginx
FROM nginx:stable-alpine

COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
