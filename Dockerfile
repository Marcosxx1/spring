# Use a imagem base do MySQL
FROM mysql:8-oracle

# Definimos as variáveis de ambiente
ENV MYSQL_ROOT_PASSWORD=mysql
ENV MYSQL_USER=social-media-user
ENV MYSQL_PASSWORD=mysql
ENV MYSQL_DATABASE=social-media-database

# Autor do container
LABEL maintainer="M.A.S"

# Exponha a porta 3306
EXPOSE 3306

# docker container ls , listamos os containers
# docker build -t social-media-database:tag .
# docker run --detach --name mysql --publish 3306:3306 social-media-database:tag