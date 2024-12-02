# Projeto Socket: Cliente e Servidor

Este projeto implementa comunicação entre cliente e servidor com objetivo de analisar o uso de dados em cada metodo de serialização de objeto.

## Serializações utilizadas

- **Serializable Java**
- **Serialização Otimizada com string**
- **JSON**
- **XML**
- **Protocol Buffers**


## Pré-requisitos

- **Java 8 ou superior**
- **Maven**

## Passos para Rodar

### 1. Clonar o repositório

`bash`
`git clone https://github.com/seu-usuario/seu-repositorio.git`
`cd seu-repositorio`


### 2. Instalar dependências

`mvn install`

### 3. Executar a aplicação

## Para Pessoa:

`mvn exec:java -Dexec.mainClass="socket.Main" -Dexec.args="pessoa"`

## Para Contatos:

`mvn exec:java -Dexec.mainClass="socket.Main" -Dexec.args="contatos"`
