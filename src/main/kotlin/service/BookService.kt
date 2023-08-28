package service

import connection.Connect
import java.sql.SQLException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class BookService {

    private val connection = Connect().creatConnect()

    fun addBook(nameBook: String, author: String, launchDate: String, codeBook: String) {
        try {
            if (!isValidBookInfo(nameBook, author, launchDate, codeBook)) {
                println("As informações do livro não podem estar vazias ou nulas.")
                return
            }
            val sql =
                "INSERT INTO listBooks (namebook, author, launchdate, codebook) VALUES ('$nameBook', '$author', '${parseDate(launchDate)}', '$codeBook')"

            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Livro $nameBook adicionado com sucesso!")
        } catch (e: DateTimeParseException) {
            println("Formato de data inválido. Use o formato DD-MM-AAAA.")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun deleteBook(id: Int) {
        if (!isValidBookId(id)) {
            println("ID de livro inválido!")
            return
        }
        val sql =
            "DELETE FROM listBooks WHERE idBook=$id"

        try {
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Livro deletado com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun updateBook(id: Int, nameBook: String, author: String, launchDate: String, codeBook: String) {
        try {
            if (!isValidBookId(id)) {
                println("ID de livro inválido!")
                return
            } else if (!isValidBookInfo(nameBook, author, launchDate, codeBook)) {
                println("As informações do livro não podem estar vazias ou nulas.")
                return
            }
            val sql =
                "UPDATE listBooks SET nameBook='$nameBook', author='$author', launchDate='${parseDate(launchDate)}', codeBook='$codeBook' WHERE idBook=$id"
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Livro $nameBook atualizado com sucesso!")
            statement.close()
        } catch (e: DateTimeParseException) {
            println("Formato de data inválido. Use o formato DD-MM-AAAA.")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun listBooks() {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT idBook, nameBook, author, launchDate, codeBook FROM listBooks")

        try {
            while (resultSet.next()) {
                val id = resultSet.getInt("idBook")
                val nameBook = resultSet.getString("nameBook")
                val author = resultSet.getString("author")
                val launchDate = resultSet.getString("launchDate")
                val codeBook = resultSet.getString("codeBook")
                println("ID: $id | Nome do livro: $nameBook | Autor: $author | Data de lançamento: $launchDate | Codigo do livro: $codeBook")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        println()
    }

    fun listSpecificBook(idBook: Int) {
        if (!isValidBookId(idBook)) {
            println("ID de livro inválido!")
            return
        }
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT * FROM listBooks WHERE idBook=$idBook")

        try {
            while (resultSet.next()) {
                val id = resultSet.getInt("idBook")
                val nameBook = resultSet.getString("nameBook")
                val author = resultSet.getString("author")
                val launchDate = resultSet.getString("launchDate")
                val codeBook = resultSet.getString("codeBook")
                println("ID: $id | Nome do livro: $nameBook | Autor: $author | Data de lançamento: $launchDate | Código do livro: $codeBook")
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    private fun parseDate(dateStr: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return LocalDate.parse(dateStr, formatter)
    }

    private fun isValidBookId(id: Int): Boolean {
        val sql = "SELECT COUNT(*) FROM listBooks WHERE idBook=?"

        try {
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            val resultSet = preparedStatement.executeQuery()
            resultSet.next()
            val count = resultSet.getInt(1)

            resultSet.close()
            preparedStatement.close()

            return count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return false
    }

    private fun isValidBookInfo(nameBook: String, author: String, launchDate: String, codeBook: String): Boolean {
        return nameBook.isNotBlank() && author.isNotBlank() && launchDate.isNotBlank() && codeBook.isNotBlank()
    }
}
