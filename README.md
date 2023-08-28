# Biblioteca SimCity - Sistema de Gerenciamento de Livros

Bem-vindo a **Biblioteca SimCity**, um sistema de gerenciamento de livros simples desenvolvido em Kotlin. Este aplicativo permite que você realize várias operações relacionadas a livros, como adicionar, deletar, atualizar e listar livros em uma biblioteca fictícia.

## Funcionalidades

- Adicionar um novo livro à coleção.
- Deletar um livro com base no ID.
- Atualizar informações de um livro existente.
- Listar todos os livros disponíveis na biblioteca.
- Consultar detalhes de um livro específico por ID.

## Pré-requisitos

Certifique-se de ter o seguinte instalado em seu sistema:

- [Kotlin](https://kotlinlang.org/docs/tutorials/command-line.html)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)

## Como Executar
Para executar o Biblioteca SimCity, siga as instruções abaixo:
### Passos para Execução
- Clone o repositório da Biblioteca SimCity para o seu computador ou baixe-o como um arquivo ZIP.
- Abra o projeto no IntelliJ IDEA (ou qualquer outra IDE que suporte Kotlin).
- Localize o arquivo main.kt e execute a função main().
- O programa será iniciado e apresentará um menu de opções para você fazer o gerenciamento da sua biblioteca.
- Escolha a opção desejada digitando o número correspondente e siga as instruções no console para seguir seu gerenciamento.

## Linguagem de uso: **Kotlin**
Um trecho de código escrito em Kotlin:
~~~kotlin
if (!isValidBookId(id)) {
    println("ID de livro inválido!")
    return
} else if (!isValidBookInfo(nameBook, author, launchDate, codeBook)) {
    println("As informações do livro não podem estar vazias ou nulas.")
    return
}
~~~
### **Pessoa Autora**
Raissa Vicente Dias
* [GitHub](https://github.com/RaiVD)
* [Linkedin](https://www.linkedin.com/mwlite/in/raissa-vicente-86a3b2210)
  
