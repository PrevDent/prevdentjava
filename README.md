# JAVA-ADVANCED
# PrevDent

# Aluno	RM

#### Keven Ike Pereira da Silva	553215

#### Vitor Cruz dos Santos  553621

#### José Ribeiro dos Santos Neto 553844

## Descrição

O **OdontoPredict** é uma aplicação Java que utiliza análise preditiva para identificar comportamentos de risco em pacientes e dentistas. O sistema tem como objetivo antecipar a necessidade de intervenções preventivas na área de odontologia.

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

