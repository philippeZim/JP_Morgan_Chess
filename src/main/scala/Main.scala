import scala.util.Random
import scala.io.StdIn

@main def main(): Unit = {
    val defaultFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    /*

    val random = new Random()
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
    play(defaultFen);

     */

    def readMove(boardFen: String, legalMoves: List[(Int, Int)]): String = {
        println("Bitte gib einen Zug ein: (Format z.B. von a1 nach c3 = a1c3)")
        val input = StdIn.readLine()
        val board = ChessBoard.fenToBoard(boardFen);
        val move = ChessBoard.translateCastle(board, ChessBoard.moveToIndex(input.substring(0, 2), input.substring(2, 4)))
        if (!input.matches("[a-h][1-8][a-h][1-8]")) {
            println("Denk nochmal nach Bro")
            readMove(boardFen, legalMoves)
        } else if (!legalMoves.contains(move)) {
            println("Das kannste nicht machen Bro (kein legaler Zug)")
            readMove(boardFen, legalMoves)
        } else {
            ChessBoard.makeMove(boardFen, move)
        }
    }

    def playing(curFen: String): Unit = {
        println(ChessBoard.getBoardString(ChessBoard.fenToBoard(curFen)))
        println()
        val legalMoves = LegalMoves.getAllLegalMoves(curFen);
        if (legalMoves.isEmpty) {
            return
        }


        val newFen: String = readMove(curFen, legalMoves)

        playing(newFen)
    }

    playing(defaultFen)

}
