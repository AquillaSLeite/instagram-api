# Projeto back-end api instagram
Projeto com finalidade de simular um back-end do aplicativo instagram
## Pré-requisitos:
```
Java 11
Conta AWS
  Usuário com credências de acesso e configurado para acessar o bucket
  Bucket S3 
Git
```
## Configuração
#### Acesso a AWS
Criar variáveis de ambientes com os nomes:
```
  AWS_ACCESS_KEY [deve receber o valor do access key do usuário da aws]
  AWS_SECRET_KEY [deve receber o valor do secret key do usuário da aws]
```
#### application.properties
na propriedade aws.s3.bucket-name deve ser colocado o nome do bucket criado na aws

## Rotas
#### User
```
POST /users
DELETE /users/{id}

GET /users
GET /users/{id}
GET /users/{id}/following
GET /users/{id}/followers
GET /users/{id}/posts

PUT /users/{id}

PATCH /users/{id}/following/add
PATCH /users/{id}/following/remove
```
#### Post
```
POST /posts
DELETE /posts/{id}
```

## Executando o projeto
Para executar o projeto no terminal, digite o seguinte comando:
```shell script
mvn spring-boot:run
```