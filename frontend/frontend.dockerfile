# FROM node:23-alpine

# WORKDIR /app

# COPY package*.json ./

# RUN npm install -g @angular/cli && \
#     npm ci

# COPY . .
# CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200"]

# Stage 1: Build the Angular application
FROM node:latest as build
WORKDIR /app
# Copy package.json from the frontend directory
COPY frontend/package*.json ./
RUN npm install -g @angular/cli && \
    npm ci
# Copy the entire frontend directory content
COPY frontend/ ./
# You might want to pass environment-specific API URLs here if needed
# ARG ANGULAR_ENV=production
# RUN ng build --configuration=$ANGULAR_ENV
RUN ng build --configuration production

# Stage 2: Serve the application with Nginx
FROM nginx:latest
COPY --from=build /app/dist/frontend /usr/share/nginx/html
# Optional: Add a custom Nginx configuration if needed
# COPY nginx.conf /etc/nginx/conf.d/default.conf 
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]