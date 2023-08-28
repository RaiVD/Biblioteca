package View

import model.InputUserModel
import service.BookService

class BookView() {
    private val bookService = BookService()
    private val inputUserModel = InputUserModel()
    fun start() {
        println("======================= Biblioteca Sim City =========================")
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> println("Encerrando o programa...")
                1 -> addNewBook()
                2 -> deleteBook()
                3 -> updateBook()
                4 -> listBooks()
                5 -> listSpecificBook()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    private fun addNewBook() {
        val name = inputUserModel.readStringFromUser("Digite o nome do livro: ")
        val author = inputUserModel.readStringFromUser("Digite o autor do livro: ")
        val releaseDate = inputUserModel.readStringFromUser("Digite a data de lançamento do livro (DD-MM-YYYY): ")
        val code = inputUserModel.readStringFromUser("Digite o código do livro: ")

        bookService.addBook(name, author, releaseDate, code)
    }

    private fun deleteBook() {
        val idToDelete = inputUserModel.readIntFromUser("Digite o ID do livro que deseja deletar: ")
        bookService.deleteBook(idToDelete)
    }

    private fun updateBook() {
        val idToUpdate = inputUserModel.readIntFromUser("Digite o ID do livro que deseja alterar: ")
        val newName = inputUserModel.readStringFromUser("Digite o novo nome do livro: ")
        val newAuthor = inputUserModel.readStringFromUser("Digite o novo autor do livro: ")
        val newReleaseDate = inputUserModel.readStringFromUser("Digite a nova data de lançamento do livro (DD-MM-YYYY): ")
        val newCode = inputUserModel.readStringFromUser("Digite o novo código do livro: ")

        bookService.updateBook(idToUpdate, newName, newAuthor, newReleaseDate, newCode)
    }

    private fun listBooks() {
        bookService.listBooks()
    }

    private fun listSpecificBook() {
        val idToQuery = inputUserModel.readIntFromUser("Digite o ID do livro que deseja consultar: ")
        bookService.listSpecificBook(idToQuery)
    }

    private fun printMenu() {
        println("\n0. Sair                                     1. Inserir um novo livro" +
                "\n2. Deletar um livro                         3. Alterar um livro" +
                "\n4. Consultar os livros                      5. Consultar livro por ID")
    }
}
