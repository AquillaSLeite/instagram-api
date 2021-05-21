# Projeto back-end api instagram
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

## Executando o projeto
Para executar o projeto no terminal, digite o seguinte comando:
```shell script
mvn spring-boot:run
```