# JAVA-ADVANCED
# PrevDent

# Aluno	RM

#### Keven Ike Pereira da Silva	553215

#### Vitor Cruz dos Santos  553621

#### José Ribeiro dos Santos Neto 553844

## Descrição

O **OdontoPredict** é uma aplicação Java que utiliza análise preditiva para identificar comportamentos de risco em pacientes e dentistas. O sistema tem como objetivo antecipar a necessidade de intervenções preventivas na área de odontologia.

# 🏥 Paciente API

Base URL: `http://localhost:8080/paciente`

| Método HTTP | Endpoint                  | Descrição                           | Corpo da Requisição (JSON)          | Resposta de Sucesso        | 
|-------------|---------------------------|-------------------------------------|-------------------------------------|----------------------------|
| GET         | `/`                       | Listar todos os pacientes           | —                                   | Array de pacientes         | 
| GET         | `/{id}`                   | Buscar um paciente pelo ID          | —                                   | Objeto do paciente         | 
| POST        | `/cadastrar`              | Criar um novo paciente              | `{ "nome": "...", "idade": 0 }`     | Objeto do paciente criado  |
| POST        | `/login`                  | Login de paciente                   | `{ "email":"", "senha": ""}`        | `{ "token": "eyJhbGciO..."}|
| PATCH       | `/{id}`                   | Atualizar dados de um paciente      | Campos parciais do paciente         | Objeto do paciente atuali..| 
| DELETE      | `/{id}`                   | Remover um paciente pelo ID         | —                                   | —                          | 


# 🦷 Dentista API

Base URL: `http://localhost:8080/dentista`

| Método HTTP | Endpoint                  | Descrição                           | Corpo da Requisição (JSON)      | Resposta de Sucesso             |
|-------------|---------------------------|-------------------------------------|---------------------------------|---------------------------------|
| GET         | `/`                       | Listar todos os dentistas           | —                               | Array de dentistas              | 
| GET         | `/{id}`                   | Buscar um dentista pelo ID          | —                               | Objeto do dentista              | 
| POST        | `/cadastrar`              | Criar um novo dentista              | `{ "nome": "...", "idade": 0 }` | Objeto do dentista criado       | 
| PATCH       | `/{id}`                   | Atualizar dados de um dentista      | Campos parciais do dentista     | Objeto do dentista atualizado   | 
| DELETE      | `/{id}`                   | Remover um dentista pelo ID         | —                               | —                               | 


# 📅 Consulta API

Base URL: `http://localhost:8080/consulta`

| Método HTTP | Endpoint                  | Descrição                            | Corpo da Requisição (JSON)      | Resposta de Sucesso             | 
|-------------|---------------------------|--------------------------------------|---------------------------------|---------------------------------|
| GET         | `/`                       | Listar todas as consultas            | —                               | Array de consultas              | 
| GET         | `/{id}`                   | Buscar uma consulta pelo ID          | —                               | Objeto da consulta              | 
| POST        | `/cadastrar`              | Criar uma nova consulta              | `{ "data": "YYYY-MM-DD",}`      | Objeto da consulta criado       | 
| PATCH       | `/{id}`                   | Atualizar dados de uma consulta      | Campos parciais da consulta     | Objeto da consulta atualizado   | 
| DELETE      | `/{id}`                   | Remover uma consulta pelo ID         | —                               | —                               |


# 🆕 Novos Registros API

Base URL: `http://localhost:8080/novos-registros`

| Método HTTP | Endpoint                  | Descrição                             | Corpo da Requisição (JSON)            | Resposta de Sucesso               | 
|-------------|---------------------------|---------------------------------------|---------------------------------------|-----------------------------------|
| GET         | `/`                       | Listar todos os novos registros       | —                                     | Array de registros                | 
| GET         | `/{id}`                   | Buscar um registro pelo ID            | —                                     | Objeto do registro                |
| POST        | `/cadastrar`              | Criar um novo registro                | `{ "nome": "...", "idade": 0 }`       | Objeto do registro criado         | 
| PATCH       | `/{id}`                   | Atualizar dados de um registro        | Campos parciais do registro           | Objeto do registro atualizado     | 
| DELETE      | `/{id}`                   | Remover um registro pelo ID           | —                                     | —                                 | 

 

---

# 📡 API nuvem

Base URL: `https://prevdent-java.azurewebsites.net`

---

## 🏥 Paciente API

| Método HTTP | Endpoint                  | Descrição                           | Corpo da Requisição (JSON)           | Resposta de Sucesso             | 
|-------------|---------------------------|-------------------------------------|--------------------------------------|---------------------------------|
| GET         | `/paciente/`              | Listar todos os pacientes           | —                                    | Array de pacientes              |
| GET         | `/paciente/{id}`          | Buscar um paciente pelo ID          | —                                    | Objeto do paciente              | 
| POST        | `/paciente/cadastrar`     | Criar um novo paciente              | `{ "nome": "...", "idade": 0 }`      | Objeto do paciente criado       | 
| PATCH       | `/paciente/{id}`          | Atualizar dados de um paciente      | Campos parciais do paciente          | Objeto do paciente atualizado   | 
| DELETE      | `/paciente/{id}`          | Remover um paciente pelo ID         | —                                    | —                               | 
| POST        | `/paciente/login`         | Autenticar paciente (login)         | `{ "email": "...", "senha": "..." }` | `{ "token": "<JWT>" }`          | 

---

## 🦷 Dentista API

| Método HTTP | Endpoint                  | Descrição                           | Corpo da Requisição (JSON)      | Resposta de Sucesso             | 
|-------------|---------------------------|-------------------------------------|---------------------------------|---------------------------------|
| GET         | `/dentista/`              | Listar todos os dentistas           | —                               | Array de dentistas              | 
| GET         | `/dentista/{id}`          | Buscar um dentista pelo ID          | —                               | Objeto do dentista              | 
| POST        | `/dentista/cadastrar`     | Criar um novo dentista              | `{ "nome": "...", "idade": 0 }` | Objeto do dentista criado       | 
| PATCH       | `/dentista/{id}`          | Atualizar dados de um dentista      | Campos parciais do dentista     | Objeto do dentista atualizado   | 
| DELETE      | `/dentista/{id}`          | Remover um dentista pelo ID         | —                               | —                               | 

---

## 📅 Consulta API

| Método HTTP | Endpoint                  | Descrição                            | Corpo da Requisição (JSON)      | Resposta de Sucesso             | 
|-------------|---------------------------|--------------------------------------|---------------------------------|---------------------------------|
| GET         | `/consulta/`              | Listar todas as consultas            | —                               | Array de consultas              | 
| GET         | `/consulta/{id}`          | Buscar uma consulta pelo ID          | —                               | Objeto da consulta              | 
| POST        | `/consulta/cadastrar`     | Criar uma nova consulta              | `{ "data": "YYYY-MM-DD",  }`    | Objeto criado                   |
| PATCH       | `/consulta/{id}`          | Atualizar dados de uma consulta      | Campos parciais da consulta     | Objeto atualizado               | 
| DELETE      | `/consulta/{id}`          | Remover uma consulta pelo ID         | —                               | —                               | 

---

## 🆕 Novos Registros API

| Método HTTP | Endpoint                         | Descrição                             | Corpo da Requisição (JSON)            | Resposta de Sucesso             | 
|-------------|----------------------------------|---------------------------------------|---------------------------------------|---------------------------------|
| GET         | `/novos-registros/`              | Listar todos os novos registros       | —                                     | Array de registros              | 
| GET         | `/novos-registros/{id}`          | Buscar um registro pelo ID            | —                                     | Objeto do registro              |
| POST        | `/novos-registros/cadastrar`     | Criar um novo registro                | `{ "nome": "...", "idade": 0 }`       | Objeto criado                  | 
| PATCH       | `/novos-registros/{id}`          | Atualizar dados de um registro        | Campos parciais do registro           | Objeto atualizado               | 
| DELETE      | `/novos-registros/{id}`          | Remover um registro pelo ID           | —                                     | —                               | 

## Tecnologias Utilizadas

- Java
- Spring Boot
- Hibernate
- JPA
- Lombok



## Como rodar o projeto usando uma vm com Almalinux 9.04

Video com o passo a passo: https://www.youtube.com/watch?v=qvUH_6Bitkk

1- Acessar via ssh a vm
```nome_usuario@ip_publico```

2- inserir a senha no terminal
```sua_senha```


## instalando o projeto na VM

1- instalar o gerenciador de pacotes yum
```sudo yum install -y yum-utils -y```

2- instalar o docker
```sudo yum-config-manager --add-repo https://download.docker.com/linux/rhel/docker-ce.repo```

3- verificar a versao do docker
``` docker --version```

4- clonar nosso repositório
```git clone https://github.com/vitorsacz/CHS1-JAVA-ADVANCED.g```

5- start no docker
``` sudo systemctl start docker```

6- enable  no docker
```sudo systemctl enable docker```

7- cria uma build do docker a partir do dockerfile do projeto
```sudo docker build -t docker-prevdent-java .```

8- cria um container com a imagem criada e roda na porta 8080
```sudo docker run -d -p 8080:8080 docker-prevdent-java```

## Estrutura do Projeto

### Pacotes Principais

- **controller**: Contém as classes responsáveis por gerenciar as requisições HTTP.
- **service**: Implementa a lógica de negócios do sistema.
- **repository**: Contém interfaces que estendem o `JpaRepository`, permitindo a interação com o banco de dados.
- **model**: Classes que representam as entidades do domínio do sistema.

### Classes Principais

#### Paciente

A classe `Paciente` representa um paciente no sistema. Ela contém os atributos como `idPaciente`, `nome` e `dataNascimento`.

#### Dentista

A classe `Dentista` representa um dentista no sistema. Os atributos incluem `idDentista`, `nome` e `especializacao`.

### Controladores

#### PacienteController

Gerencia as requisições relacionadas aos pacientes, permitindo criar novos pacientes e obter informações sobre pacientes existentes.

#### DentistaController

Gerencia as requisições relacionadas aos dentistas, permitindo criar novos dentistas e obter informações sobre dentistas existentes.

### Serviços

#### PacienteService

Implementa a lógica de negócios para gerenciar pacientes, permitindo a criação de novos pacientes e a busca de pacientes existentes.

#### DentistaService

Implementa a lógica de negócios para gerenciar dentistas, permitindo a criação de novos dentistas e a busca de dentistas existentes.

## Conclusão

O OdontoPredict busca oferecer uma estrutura robusta e eficiente para a identificação de comportamentos de risco na odontologia, permitindo que dentistas realizem intervenções preventivas mais eficazes.

## Como Executar o Projeto

1. Clone este repositório.
2. Certifique-se de ter o Java e o Maven instalados.
3. Navegue até o diretório do projeto.
4. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

