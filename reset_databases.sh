#!/bin/bash

# Declarar los nombres de los servicios de base de datos que quieres limpiar
services=("userdb" "hoteldb" "resrvationdb" "restaurantdb" "orderdb" "feedbackdb" "payrolldb" "reportdb")

# Detener los contenedores que contienen bases de datos
echo "Deteniendo contenedores de bases de datos..."
docker compose stop "${services[@]}"

# Eliminar los contenedores y volúmenes de las bases de datos
echo "Eliminando contenedores y volúmenes..."
docker compose rm -f -v "${services[@]}"

# Volver a levantar los contenedores forzando la recreación
echo "Levantando contenedores con datos limpios..."
docker compose up -d --force-recreate "${services[@]}"

echo "Operación completada."
