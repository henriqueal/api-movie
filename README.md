## Requisitos
Java 11

## Configuração BD
Para importar os dados no BD, basta alterar o seguinte arquivo:
```
./src/main/resources/movielist.csv
```

## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/henriqueal/api-movie
```

### Construção

Para construir o projeto com o Maven, executar os comando abaixo:

```shell
mvn clean package
```

## Testes

### Configurando
Altere o Json esperado de resposta que se encontra no seguinte arquivo:

```
./src/main/resources/response.json
```

### Rodando
Para rodar os testes, utilize o comando abaixo:

```
mvn test
```

## Rodar o projeto
Basta executar o jar gerado no target
```
java -jar target/api-movie-0.0.1-SNAPSHOT.jar
```

## Consumindo a API
http://localhost:8080/intervaloPremioView
