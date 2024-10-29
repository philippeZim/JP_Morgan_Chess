import scala.annotation.tailrec

object PseudoMoves {

    def pseudoLegalAttack(board: Vector[Piece], pos: Int, rd: Int, cd: Int, attackColor: Color): Boolean = {
        onBoard(pos, rd, cd) && board(pos + rd * 8 + cd).color == attackColor
    }

    def pseudoLegalMove(board: Vector[Piece], pos: Int, rd: Int, cd: Int): Boolean = {
        onBoard(pos, rd, cd) && board(pos + rd * 8 + cd).color == Color.EMPTY
    }

    def extractColor(color: String): (Int, Color, Color) = {
        color match {
            case "w" => (-1, Color.WHITE, Color.BLACK);
            case "b" => (1, Color.BLACK, Color.WHITE);
        }
    }

    def onBoard(pos: Int, rd: Int, cd: Int): Boolean = {
        val nr = pos + rd * 8
        if (nr < 0 || nr > 63) {
            return false
        }
        val nc = pos + cd
        if (pos / 8 != nc / 8) {
            return false
        }
        true
    }

    def piecePositions(board: Vector[Piece], piece: Piece): List[Int] = {
        @tailrec
        def sub(board: List[Piece], acc: List[Int], ind: Int): List[Int] = {
            board match {
                case Nil => acc
                case h :: t => {
                    if (h == piece) {
                        sub(t, ind :: acc, ind + 1);
                    } else {
                        sub(t, acc, ind + 1);
                    }
                }
            }
        }
        sub(board.toList, List(), 0);
    }

    @tailrec
    def checkMoves(board: Vector[Piece], pos: Int, moves: List[(Int, Int)], attacks: List[(Int, Int)], acc: List[(Int, Int)], attackColor: Color): List[(Int, Int)] = {
        attacks match {
            case Nil => {
                moves match {
                    case Nil => acc
                    case (rd, cd) :: t => {
                        if (pseudoLegalMove(board, pos, rd, cd)) {
                            checkMoves(board, pos, t, attacks, (pos, pos + rd * 8 + cd) :: acc, attackColor)
                        } else {
                            checkMoves(board, pos, t, attacks, acc, attackColor)
                        }
                    }
                }
            }
            case (rd, cd) :: t => {
                if (pseudoLegalAttack(board, pos, rd, cd, attackColor)) {
                    checkMoves(board, pos, moves, t, (pos, pos + rd * 8 + cd) :: acc, attackColor)
                } else {
                    checkMoves(board, pos, moves, t, acc, attackColor)
                }
            }
        }

    }

    def checkEnPassant(board: Vector[Piece], epPos: Int, attackColorNum: Int): List[(Int, Int)] = {

        val toCheck = List((attackColorNum * -1, attackColorNum * -1), (attackColorNum * -1, attackColorNum))
        def sub()
    }


    def pseudoPawnMoves(fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val attackMoves = List((attackColorNum, attackColorNum), (attackColorNum, attackColorNum * -1));
        val straightMoves = List((attackColorNum, 0), (attackColorNum * 2, 0));

        val piecePos = piecePositions(board, Piece(PieceType.PAWN, moveColor));

        @tailrec
        def sub1(acc: List[(Int, Int)], piecePos: List[Int]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    sub1(checkMoves(board, h, straightMoves, attackMoves, acc, attackColor), t)
                }
            }
        }


        if (fenSplit(3) != "-") {
            sub1(checkEnPassant(board, ChessBoard.coordinatesToIndex(fenSplit(3)), attackColorNum), piecePos);
        } else {
            sub1(List(), piecePos);
        }
    }

    /*
    def pseudoPawnMoves(fen: String): List[(Int, Int, Int, Int)] = {
        val board: Vector[Vector[Piece]] = ChessBoard.fenToBoard(fen);
        val fenSplit: List[String] = fen.split(" ").toList;

        val attackColor: Int = fenSplit(1) match {
            case "w" => -1;
            case "b" => 1;
        }

        val moveColor: Color = attackColor match {
            case -1 => Color.WHITE;
            case 1 => Color.BLACK;
        }
        val attackMoves = List((attackColor, attackColor), (attackColor, attackColor * -1));
        val straightMoves = List((attackColor, 0), (attackColor * 2, 0));

        def pawnPositions(i: Int, j: Int, acc: List[(Int, Int)]): List[(Int, Int)] = {
            if (i == 8) {
                return acc;
            }
            val curPiece = board(i)(j);
            if (curPiece.pieceType == PieceType.PAWN && curPiece.color == moveColor) {
                if (j == 7) {
                    pawnPositions(i + 1, 0, (i, j) :: acc);
                } else {
                    pawnPositions(i, j + 1, (i, j) :: acc)
                }
            } else {
                if (j == 7) {
                    pawnPositions(i + 1, 0, acc);
                } else {
                    pawnPositions(i, j + 1, acc);
                }
            }
        }

        def pawnMovesToList(board: Vector[Vector[Piece]], pawnPos: List[(Int, Int)], moveList: List[(Int, Int, Int, Int)], attacks: List[(Int, Int)], straight: List[(Int, Int)]): List[(Int, Int, Int, Int)] = {
            pawnPos match {
                case Nil => return moveList;
                case (i, j) :: t => {
                    straightMoves match {
                        case Nil => attackMoves match {
                            case Nil => return moveList;
                            case (k, l) :: t2 => {
                                if (i + k >= 0 && i + k < 8 && j + l >= 0 && j + l < 8 && board(i + k)(j + l).color != moveColor && board(i + k)(j + l).color != Color.EMPTY) {
                                    pawnMovesToList(board, t, (i, j, i + k, j + l) :: moveList, t2, straight);
                                } else {
                                    pawnMovesToList(board, t, moveList, t2, straight);
                                }
                            }
                        }
                        case (k, l) :: t2 => {
                            if (i + k >= 0 && i + k < 8 && j + l >= 0 && j + l < 8 && board(i + k)(j + l).color == Color.EMPTY) {
                                pawnMovesToList(board, t, (i, j, i + k, j + l) :: moveList, attacks, t2);
                            } else {
                                pawnMovesToList(board, t, moveList, attacks, t2);
                            }
                        }
                    }
                }
            }
        }

        pawnMovesToList(board, pawnPositions(0, 0, List()), List(), attackMoves, straightMoves);
    }

     */
}
