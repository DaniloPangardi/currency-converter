# Currency Converter
Tem como objetivo realizar a conversão entre duas moedas.

## Tecnologias
* Java 11
* Spring Boot 2.6
* Apache Maven
* Lombok
* Swagger
* JUnit 5
* Mockito
* AssertJ
* JPA
* H2 Database
* Jasypt

## Notas Técnicas
* A exposição da API é em modelo REST e todos os conteúdos retornados estão em formatos JSON.

##Guidelines
- File name:
  - camelCase
- Classes:
  - PascalCase
Pacotes:
  - config: Configurações da aplicação
  - controller: Controllers da aplicação
  - dto: Objetos que trafegam entre camadas
  - exception: Exceções de regras de negócio
  - exceptionhandler: Contem a classe responsável por capturar/tratar todas as exceções lançadas pela aplicação.
  - model: Entidades mapeadas com a base de dados
  - repository: Interface de acesso a base de dados
  - service: Regras de negócio     

## Execução
* mvn clean install
* java -jar target/currency-converter-1.0.0.jar

## Ou se preferir
* Na raiz da aplicação, abra um interpretador bash e execute os comandos:
* chmod +x run-app.sh
* ./run-app.sh

## Deploy
- O deploy será realizado no "Heroku" automaticamente após o push ou pull request na branch "main" pelo pipeline do Github.

## Swagger UI
* Local: http://localhost:8080/swagger-ui.html
* Heroku: https://currencyconverters.herokuapp.com/swagger-ui.html
