package Model

enum Color:
    case BLACK, WHITE, EMPTY

enum PieceType:
    case PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING, EMPTY

trait Piec {
    val piece : Piece
    def toString: String
}

class WhitePawn extends Piec {
    val piece = Piece(PieceType.PAWN, Color.WHITE)

    override def toString = "P"
}

class BlackPawn extends Piec {
    val piece = Piece(PieceType.PAWN, Color.BLACK)

    override def toString = "p"
}

class WhiteKnight extends Piec {
    val piece = Piece(PieceType.KNIGHT, Color.WHITE)

    override def toString = "N"
}

class BlackKnight extends Piec {
    val piece = Piece(PieceType.KNIGHT, Color.BLACK)

    override def toString = "n"
}

class WhiteBishop extends Piec {
    val piece = Piece(PieceType.BISHOP, Color.WHITE)

    override def toString = "B"
}

class BlackBishop extends Piec {
    val piece = Piece(PieceType.BISHOP, Color.BLACK)

    override def toString = "b"
}

class WhiteRook extends Piec {
    val piece = Piece(PieceType.ROOK, Color.WHITE)

    override def toString = "R"
}

class BlackRook extends Piec {
    val piece = Piece(PieceType.ROOK, Color.BLACK)

    override def toString = "r"
}

class WhiteQueen extends Piec {
    val piece = Piece(PieceType.QUEEN, Color.WHITE)

    override def toString = "Q"
}

class BlackQueen extends Piec {
    val piece = Piece(PieceType.QUEEN, Color.BLACK)

    override def toString = "q"
}

class WhiteKing extends Piec {
    val piece = Piece(PieceType.KING, Color.WHITE)

    override def toString = "K"
}

class BlackKing extends Piec {
    val piece = Piece(PieceType.KING, Color.BLACK)

    override def toString = "k"
}

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

