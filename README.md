# Projeto back-end api instagram
Projeto com finalidade de simular um back-end do aplicativo instagram
## Pré-requisitos:
```
Java 11
Conta AWS
  Usuário com credênciais de acesso e configurado para acessar o bucket
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
Na propriedade aws.s3.bucket-name deve ser colocado o nome do bucket criado na aws
## Dependências
```
  Spring boot
  Lombok
  Mapstruct
  AmazonAws
  H2
```
## Rotas
#### User
```
POST /users
POST /users/{id}/following/add

PUT /users/{id}

DELETE /users/{id}
DELETE /users/{id}/following/{following}/remove

GET /users
GET /users/{id}
GET /users/{id}/following
GET /users/{id}/followers
GET /users/{id}/posts
```
#### Post
```
POST /posts

DELETE /posts/{id}

GET /posts/{id}
GET /posts/{id}/comments
```
#### Comment
```
POST /comments

DELETE /comments/{id}
```
## Executando o projeto
Para executar o projeto no terminal, digite o seguinte comando:
```shell script
mvnw spring-boot:run
```