package Model

object Remis {


    def isPatt(fen: String, legalMoves: List[(Int, Int)]): Boolean = {
        if (legalMoves.isEmpty) {
            val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
            val fenSplit: List[String] = fen.split(" ").toList;

            val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = PseudoMoves.extractColor(fenSplit(1));
            val kingPos: Int = PseudoMoves.piecePositions(board, Piece(PieceType.KING, moveColor)).head
            if (!LegalMoves.isPosAttacked(fen, kingPos)) {
                return true
            }
        }
        false
    }

    def isRemis(fen: String, legalMoves: List[(Int, Int)]) : Boolean = {
        false
    }
}
