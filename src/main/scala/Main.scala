import .readMove

import scala.io.StdIn
import scala.util.Random

@main def main(): Unit = {
    val defaultFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    /*val random = new Random()
    def play(curFen: String): Unit = {
        println(ChessBoard.getBoardString(ChessBoard.fenToBoard(curFen)))
        println(curFen)
        println()
        val legalMoves = LegalMoves.getAllLegalMoves(curFen);
        if (legalMoves.isEmpty) {
            return
        }
        val newFen = ChessBoard.makeMove(curFen, legalMoves(random.nextInt(legalMoves.length)))
        play(newFen)
    }
    play(defaultFen); */
    def playing(curFen: String): Unit = {
        println(ChessBoard.getBoardString(ChessBoard.fenToBoard(curFen)))
        println()
        val legalMoves = LegalMoves.getAllLegalMoves(curFen);
        if (legalMoves.isEmpty) {
            return
        }
        val newFen : String = readMove(curFen, legalMoves)
        def readMove(boardFen: String, legalMoves: List[(Int, Int)]) : String = {
            println("Bitte gib einen Zug ein: (z.B. von a1 nach c3 = a1c3")
            val input =  StdIn.readLine()
            if(!input.matches("[a-h][1-8][a-h][1-8]") ) {
                println("Denk nochmal nach Bro")
                readMove(boardFen, legalMoves)
            } else if(!legalMoves.contains(ChessBoard.moveToIndex(input.substring(0, 2), input.substring(2, 4)))) {
                println("Das kannste nicht machen Bro (kein legaler Zug)")
                readMove(boardFen, legalMoves)
            }
            ChessBoard.makeMove(boardFen, ChessBoard.moveToIndex(input.substring(0, 2), input.substring(2, 4)))
        }
        playing(newFen)
    }
    playing(defaultFen)
}
