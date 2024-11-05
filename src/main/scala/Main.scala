import scala.util.Random

@main def main(): Unit = {
    val defaultFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    val random = new Random()
    def play(curFen: String): Unit = {
        println(ChessBoard.getBoardString(ChessBoard.fenToBoard(curFen)))
        println()
        val legalMoves = LegalMoves.getAllLegalMoves(curFen);
        if (legalMoves.isEmpty) {
            return
        }
        val newFen = ChessBoard.makeMove(curFen, legalMoves(random.nextInt(legalMoves.length)))
        play(newFen)
    }
    play(defaultFen);

}
