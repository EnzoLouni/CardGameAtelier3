FROM nginx:latest
EXPOSE 80
RUN rm /etc/nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx
VOLUME /etc/nginx/nginx.conf:ro
