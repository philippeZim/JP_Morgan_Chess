package Model.ChessComponent

enum Color:
    case BLACK, WHITE, EMPTY

enum PieceType:
    case PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING, EMPTY


final case class Piece (pieceType: PieceType, color: Color) {

    override def toString(): String = {
        val pieceMap: Map[(PieceType, Color), String] = Map(
            (PieceType.PAWN, Color.WHITE) -> "P", 
            (PieceType.PAWN, Color.BLACK) -> "p", 
            (PieceType.ROOK, Color.WHITE) -> "R", 
            (PieceType.ROOK, Color.BLACK) -> "r", 
            (PieceType.KNIGHT, Color.WHITE) -> "N", 
            (PieceType.KNIGHT, Color.BLACK) -> "n", 
            (PieceType.BISHOP, Color.WHITE) -> "B", 
            (PieceType.BISHOP, Color.BLACK) -> "b", 
            (PieceType.QUEEN, Color.WHITE) -> "Q", 
            (PieceType.QUEEN, Color.BLACK) -> "q", 
            (PieceType.KING, Color.WHITE) -> "K", 
            (PieceType.KING, Color.BLACK) -> "k", 
            (PieceType.EMPTY, Color.EMPTY) -> ".");
        pieceMap.get((this.pieceType, this.color)) match {
            case None => "?"
            case Some(value) => value
        }
    }
}

