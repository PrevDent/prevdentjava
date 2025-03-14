# Usar uma imagem base que contém Maven e OpenJDK
FROM maven:3.8.4-openjdk-17-slim AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar todos os arquivos do projeto para o diretório de trabalho
COPY . .

# Executar o Maven para construir o projeto e gerar o JAR
RUN mvn clean install -DskipTests

# Usar a imagem do Java como base para a execução
FROM openjdk:17-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado da etapa de construção
COPY --from=build /app/target/PrevDent-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta que a aplicação vai usar
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]