import scala.annotation.tailrec

object ChessBoard {

    def splitLine(): String = {
        "    " + "+-----" * 8 + "+\n"
    }

    def pieceLine(line: Vector[Piece], columnNumber: String): String = {
        s"$columnNumber   |" + line.map(el => s"  ${el.toString()}  |").mkString("") + "\n";
    }

    def getBoardString(board: Vector[Piece]): String = {
        @tailrec def sub(ind: Int, acc: String): String = {
            ind match {
                case 8 => {
                    val letters = "abcdefgh";
                    return acc + splitLine() + "       " + letters.map(el => s"$el     ").mkString("");
                }
                case _ => sub(ind + 1, acc + splitLine() + pieceLine(board.slice(ind * 8, ind * 8 + 8), s"${8 - ind}"));
            }
        }

        sub(0, "")

    }

    def getDefaultBoard(): Vector[Piece] = {
        val defaultFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        fenToBoard(defaultFEN);
    }

    def boardToFen(board: Vector[Piece]): String = {

        @tailrec
        def sub2(fen: List[String], acc: List[String], empty: Int): String = {
            fen match {
                case Nil => {
                    if (empty == 0) {
                        acc.mkString
                    } else {
                        (empty.toString::acc).mkString
                    }

                }
                case h :: t => {
                    h match {
                        case "." => sub2(t, acc, empty + 1);
                        case el => {
                            if (empty == 0) {
                                sub2(t, el::acc, empty)
                            } else {
                                sub2(t, el::empty.toString::acc, 0)
                            }
                        }
                    }
                }
            }
        }

        @tailrec
        def sub1(acc: List[String], size: Int, pieces: List[Piece]): List[String] = {
            pieces match {
                case Nil => acc
                case h :: t => {
                    if ((size + 1) % 8 == 0 && size != 63) {
                        sub1("/"::h.toString()::acc, size+1, t)
                    } else {
                        sub1(h.toString()::acc, size+1, t)
                    }
                }
            }
        }
        sub2(sub1(List(), 0, board.toList), List(), 0);
    }

    def fenToBoard(fen: String): Vector[Piece] = {
        @tailrec def sub2(acc: List[Piece], n: Int): List[Piece] = {
            n match {
                case el if el > 0 => sub2(Piece(PieceType.EMPTY, Color.EMPTY) :: acc, n - 1)
                case _ => acc
            }
        }

        @tailrec def sub(fen: List[Char], acc: List[Piece]): List[Piece] = {
            fen match {
                case Nil => acc
                case h :: t => {
                    h match {
                        case '/' => sub(t, acc)
                        case 'p' => sub(t, Piece(PieceType.PAWN, Color.BLACK) :: acc)
                        case 'r' => sub(t, Piece(PieceType.ROOK, Color.BLACK) :: acc)
                        case 'n' => sub(t, Piece(PieceType.KNIGHT, Color.BLACK) :: acc)
                        case 'b' => sub(t, Piece(PieceType.BISHOP, Color.BLACK) :: acc)
                        case 'q' => sub(t, Piece(PieceType.QUEEN, Color.BLACK) :: acc)
                        case 'k' => sub(t, Piece(PieceType.KING, Color.BLACK) :: acc)
                        case 'P' => sub(t, Piece(PieceType.PAWN, Color.WHITE) :: acc)
                        case 'R' => sub(t, Piece(PieceType.ROOK, Color.WHITE) :: acc)
                        case 'N' => sub(t, Piece(PieceType.KNIGHT, Color.WHITE) :: acc)
                        case 'B' => sub(t, Piece(PieceType.BISHOP, Color.WHITE) :: acc)
                        case 'Q' => sub(t, Piece(PieceType.QUEEN, Color.WHITE) :: acc)
                        case 'K' => sub(t, Piece(PieceType.KING, Color.WHITE) :: acc)
                        case el: Char if el.isDigit => sub(t, sub2(acc, el.toInt - 48))
                    }
                }
            }
        }

        sub(fen.split(" ")(0).toList, List()).reverse.toVector
    }

    def coordinatesToIndex(coord: String): Int = {
        (coord.charAt(0).toInt - 97) + 8 * (8 - (coord.charAt(1).toInt - 48));
    }

    def indexToCoordinates(index: Int): String = {
        val file = (index % 8)
        val rank = 7 - (index / 8)

        val fileChar = (file + 97).toChar
        val rankChar = (rank + 49).toChar

        s"$fileChar$rankChar"
    }

    def moveToIndex(from: String, to: String): (Int, Int) = {
        (coordinatesToIndex(from), coordinatesToIndex(to));
    }

    def updateCastleing(fenCastles: String, move:(Int, Int)): String = {
        move match {
            case (a, b) if b != -1 => fenCastles
            case (-1, -1) => fenCastles.replace("K", "").replace("Q", "")
            case (-2, -1) => fenCastles.replace("Q", "").replace("K", "")
            case (-3, -1) => fenCastles.replace("k", "").replace("q", "")
            case (-4, -1) => fenCastles.replace("q", "").replace("k", "")
            case (60, _) => fenCastles.replace("K", "").replace("Q", "")
            case (4, _) => fenCastles.replace("k", "").replace("q", "")
            case (0, _) => fenCastles.replace("q", "")
            case (7, _) => fenCastles.replace("k", "")
            case (63, _) => fenCastles.replace("K", "")
            case (56, _) => fenCastles.replace("Q", "")
        }
    }
    
    def updateEnpassant(fen: String, move:(Int, Int)): String = {
        val (from, to) = move;
        if(to < 0) {
            return "-"
        }
        val fenSplit = fen.split(" ");
        val board = ChessBoard.fenToBoard(fen);
        if (board(from).pieceType == PieceType.PAWN && Math.abs(from - to) == 16) {
            ChessBoard.indexToCoordinates(from + 8 * ((to - from) / Math.abs(to - from)))
        } else {
            "-"
        }
    }

    def makeMove(fen: String, move: (Int, Int)): String = {
        val fenSplit = fen.split(" ")
        val board = ChessBoard.fenToBoard(fen);
        val newBoard = LegalMoves.makeMove(board, move);
        if (fenSplit(1) == "w") {
            ChessBoard.boardToFen(newBoard) + " b " + updateCastleing(fenSplit(2), move) + " " + updateEnpassant(fen, move) + " " + fenSplit(4) + " " + fenSplit(5)
        } else {
            ChessBoard.boardToFen(newBoard) + " w " + updateCastleing(fenSplit(2), move) + " " + updateEnpassant(fen, move) + " " + fenSplit(4) + " " + (fenSplit(5).toInt+1).toString
        }
    }

}
