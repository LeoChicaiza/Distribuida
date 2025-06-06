# Usamos una imagen base de Nginx
FROM nginx:alpine

# Copiar el archivo index.html al contenedor de Nginx
COPY src/main/webapp/index.html /usr/share/nginx/html/index.html

# Exponer el puerto 80
EXPOSE 80

# Iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]
