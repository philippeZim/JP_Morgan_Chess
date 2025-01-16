package Model.ChessComponent.BasicChessComponent.StandartChess

class BoardMonad[T](board: Vector[Piece]) {
    val state: Vector[Option[Piece]] = board.map {
        case Piece(PieceType.EMPTY, Color.EMPTY) => None
        case piece => Some(piece)
    }

    def map[T](f: Option[Piece] => T): Vector[T] = {
        state.map(f)
    }

    def flatMap[T](f: Option[Piece] => Vector[T]): Vector[T] = {
        state.flatMap(f)
    }

    def filter(f: Option[Piece] => Boolean): Vector[Option[Piece]] = {
        state.filter(f)
    }
}

