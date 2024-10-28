object PseudoMoves {
    def pseudoPawnMoves(fen: String): List[(Int, Int, Int, Int)] = {
        val board: Vector[Vector[Piece]] = ChessBoard.fenToBoard(fen);
        val fenSplit: List[String] = fen.split(" ").toList;

        val attackColor: Int = fenSplit(1) match {
            case "w" => -1;
            case "b" => 1;
        }

        val moveColor: Color = attackColor match {
            case -1 => Color.WHITE;
            case 1  => Color.BLACK;
        }
        val attackMoves =
            List((attackColor, attackColor), (attackColor, attackColor * -1));
        val straightMoves = List((attackColor, 0), (attackColor * 2, 0));

        def pawnPositions(
            i: Int,
            j: Int,
            acc: List[(Int, Int)]
        ): List[(Int, Int)] = {
            if (i == 8) {
                return acc;
            }
            val curPiece = board(i)(j);
            if (
                curPiece.pieceType == PieceType.PAWN && curPiece.color == moveColor
            ) {
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

        def pawnMovesToList(
            board: Vector[Vector[Piece]],
            pawnPos: List[(Int, Int)],
            moveList: List[(Int, Int, Int, Int)],
            attacks: List[(Int, Int)],
            straight: List[(Int, Int)]
        ): List[(Int, Int, Int, Int)] = {
            pawnPos match {
                case Nil => return moveList;
                case (i, j) :: t => {
                    straightMoves match {
                        case Nil =>
                            attackMoves match {
                                case Nil => return moveList;
                                case (k, l) :: t2 => {
                                    if (
                                    	i + k >= 0 && i + k < 8 && j + l >= 0 && j + l < 8 && board(i + k)(j + l).color != moveColor && board(i + k)(j + l).color != Color.EMPTY
                                    ) {
                                        pawnMovesToList(
                                            board,
                                            t,
                                            (i, j, i + k, j + l) :: moveList,
                                            t2,
                                            straight
                                        );
                                    } else {
                                        pawnMovesToList(board, t, moveList, t2, straight);
                                    }
                                }
                            }
                        case (k, l) :: t2 => {
                            if (i + k >= 0 && i + k < 8 && j + l >= 0 && j + l < 8 && board(i + k)(j + l).color == Color.EMPTY) {
                                pawnMovesToList(
                                    board,
                                    t,
                                    (i, j, i + k, j + l) :: moveList,
                                    attacks,
                                    t2
                                );
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
}
