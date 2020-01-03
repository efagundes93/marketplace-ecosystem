# sales-lot-wellcome project

## Problema/Motivação
Dado um arquivo de entrada com os seguintes dados

### Dados do vendedor
Os dados do vendedor têm o formato id 001 e a linha terá o seguinte formato: 001çCPFçNameçSalary

### Dados do cliente
Os dados do cliente têm o formato id 002 e a linha terá o seguinte formato: 002çCNPJçNameçBusiness Area

### Dados de vendas
Os dados de vendas têm o formato id 003. Dentro da linha de vendas, existe a lista de itens, que é envolto por colchetes []. A linha terá o seguinte formato: 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

### Criar uma aplicação Java que:

- Faça leitura de dados do diretório padrão, localizado em %HOMEPATH%/data/in.
- Leia somente arquivos .dat.
- Depois de processar todos os arquivos dentro do diretório padrão de entrada, crie um arquivo dentro do diretório de saída padrão, localizado em %HOMEPATH%/data/out. O nome do arquivo deve seguir o padrão, {flat_file_name} .done.dat.

O conteúdo do arquivo de saída deve resumir os seguintes dados:
- Quantidade de clientes no arquivo de entrada
- Quantidade de vendedor no arquivo de entrada
- ID da venda mais cara
- O pior vendedor

## Escopo

- Observa o diretório parametrizado na string de configuração:
- Quando recebido um arquivo com a extensão ".dat" então verifica se na linha existe identificador de um layout
de dados válido, caso haja realiza o processamento e envia o item convertido para um tópico do Kafka. Caso inexista
layout válido para a linha, então realiza gravação da linha em um arquivo de erros oriundos do processamento,
- Ao final do processamento, move o arquivo processado para o subdiretorio \processed e gera relatórios no diretorio parametrizado como output.


## Configurações

Foram externalizadas as configurações de alguns itens visando tornar mais dinâmica a leitura e geração dos arquivos. 
Atualmente estas configurações estão fixadas em `sales-lot-wellcome\src\main\resources\application.properties`.
As configurações de execução são as seguintes:

- `quarkus.mongodb.connection-string`: Indique aqui a URL que faz referência a sua(s) instancia(s) de MongoDB
- `quarkus.mongodb.database`: Indique aqui o nome da base de dados que será utilizada
- `execution.file.layout.separator`: Indique aqui o separador do arquivo, na aplicação está como valor default o "ç" porém não recomendamos o uso deste delimitador no processamento de arquivos oriundos de lingua latina, uma vez que nomes proprios podem conte-lo (Ex.: Gonçalves)
- ` execution.file.layout.new_line`: Indique qual o separador de linha deve ser utilizado, caso o arquivo seja gerado no mesmo SO, pode ser substituido por System.lineSeparator()
- ` execution.file.layout.salesman.id`: Indique qual o identificador que faz referencia as linhas que contém dados de vendedor.
- `execution.file.layout.salesman.columns.qty`: A quantidade de colunas que devem conter nas linhas de dados relativos ao vendedor.
- `execution.file.layout.customer.id`: Indique qual o identificador que faz referencia as linhas que contém dados de comprador.
- `execution.file.layout.customer.columns.qty`: A quantidade de colunas que devem conter nas linhas de dados relativos ao comprador.
- `execution.file.layout.sales.id`: Indique qual o identificador que faz referencia as linhas que contém dados de venda.
- `execution.file.layout.sales.columns.qty`: A quantidade de colunas que devem conter nas linhas de dados relativos a vendas.
- `execution.file.input_subdirectory`: Indique em qual subdiretorio do sistema a aplicação deve aguardar pelos arquivos
- `execution.file.output_subdirectory`: Indique em qual subdiretorio do sistema a aplicação deve agerar os arquivos de saida.
- ` execution.file.error_subdirectory`:Indique em qual subdiretorio do sistema a aplicação deve agerar o arquivo de erros.
- `execution.sales_synthesis.chunk_size`: Indique de quantos em quantos registros o sistema deve fazer persistência na base de dados.
- `execution.kafka.url`: Indique a URL onde está rodando sua instância do Apache Kafka
- `execution.kafka.topic.to.customer_data_read`: Indique o nome do tópico para onde os dados de comprador devem ser enviados.
- `execution.kafka.topic.to.salesman_data_read`:  Indique o nome do tópico para onde os dados de vendedor devem ser enviados.
- `execution.kafka.topic.to.sale_data_read`: Indique o nome do tópico para onde os dados de venda devem ser enviados.


# Manual do usuário

## Pré-requisitos
Para rodar o projeto você precisa de:
- JDK 1.8+ com JAVA_HOME devidamente configurada
- Apache Maven 3.5.3+
- Docker (ou a stack Apache Kafka + MongoDB instalados na máquina)

!!! Na pasta raiz do projeto há um arquivo docker-compose com Apache Kafka e MongoDb configurados, basta em seu cmd navegar até o diretório e executar
`docker-compose up -d`


## Build & Run da aplicação
Por se tratar de uma aplicação Quarkus, você as seguintes opções de build & excução:

## Rodando a aplicação em modo desenvolvedor

Para rodar a aplicação em modo desenvolvedor você pode executar o comando:
```
./mvnw quarkus:dev
```

### Ciando executavel sobre a JVM
Dentro do diretório você pode excecutar o comando `./mvnw package`
posteriormente executar `java -jar target/sales-lot-wellcome-1.0.0-SNAPSHOT-runner.jar`

### Criando executavel nativo

Dentro do diretório você pode excecutar o comando `./mvnw package -Pnative`
ou então realizar o build fazendo uso do Docker, executando o comando `./mvnw package -Pnative -Dquarkus.native.container-build=true`.
Feito isto, agora você pode executar o binário: `./target/sales-lot-wellcome-1.0.0-SNAPSHOT-runner`

Caso tenha dúvidas, indico a leitura da documentação oficial do Quarkus: https://quarkus.io/guides/building-native-image-guide


# Tecnologias

Este projeto usa:
- [Quarkus]( https://quarkus.io/  "Quarkus")
- [Apache Camel](https://camel.apache.org/ "Apache Camel")
- [Apache Kafka](https://kafka.apache.org/ "Apache Kafka")
- [MongoDB](https://www.mongodb.com/ "MongoDB")
