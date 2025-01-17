import Model.ChessComponent.BasicChessComponent.StandartChess.{ChessBoard, Color, Piece, PieceType}


class BoardMonad[T](board: Vector[Piece]) {
    val state: Vector[Option[Piece]] = board.map {
        case Piece(PieceType.EMPTY, Color.EMPTY) => None
        case p => Some(p)
    }

    def map[T](f: Option[Piece] => T): Vector[T] = {
        state.map(f)
    }

    //def map[T](f: Nothing => T): Vector[T] = state.map(f)

    def flatMap(f: Option[Piece] => Vector[T]): Vector[T] = {
        state.flatMap(f)
    }

    def filter(f: Option[Piece] => Boolean): Vector[Option[Piece]] = {
        state.filter(f)
    }
}

def piecePositions(board: Vector[Piece], piece: Piece): List[Int] = {
    def sub(board: List[Piece], accumulator: List[Int], index: Int): List[Int] = {
        board match {
            case Nil => accumulator
            case h :: t => {
                if (h == piece) {
                    sub(t, index :: accumulator, index + 1);
                } else {
                    sub(t, accumulator, index + 1);
                }
            }
        }
    }

    sub(board.toList, List(), 0);
}

def piecePositionsMonad(board: Vector[Piece], piece: Piece): List[Int] = {
    val bm = BoardMonad(board)
    var index = -1
    val bm_index = bm.map(e =>
        index += 1
        (e, index)
    )
    val correct_pieces_with_index = bm_index.filter(
        (e, i) => e match {
            case None => false
            case Some(a) if a.pieceType == piece.pieceType && a.color == piece.color => true
            case _ => false
        }
    )
    correct_pieces_with_index.map((e, i) => i).toList
}

println(piecePositions(ChessBoard.getDefaultBoard(), new Piece(PieceType.KNIGHT, Color.WHITE)))
println(piecePositionsMonad(ChessBoard.getDefaultBoard(), new Piece(PieceType.KNIGHT, Color.WHITE)))