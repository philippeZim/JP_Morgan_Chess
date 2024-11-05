import PieceType.{KING, PAWN}

import scala.annotation.tailrec

object LegalMoves {


    def isAttacker(board: Vector[Piece], attacker: Piece, pos: Int, row: Int, col: Int): Boolean = {
        PseudoMoves.onBoard(pos, row, col) && board(pos + 8 * row + col) == attacker
    }

    def pawnAttack(fen: String, pos: Int): Boolean = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = PseudoMoves.extractColor(fenSplit(1));

        val attacks: List[(Int, Int)] = List((attackColorNum, attackColorNum), (attackColorNum, attackColorNum * -1))

        @tailrec
        def sub(moves: List[(Int, Int)]): Boolean = {
            moves match {
                case Nil => false
                case (rd, cd) :: t => {
                    if (isAttacker(board, Piece(PieceType.PAWN, attackColor), pos, rd, cd)) {
                        return true
                    }
                    sub(t)
                }
            }
        }

        sub(attacks)
    }

    def knightAttack(fen: String, pos: Int): Boolean = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = PseudoMoves.extractColor(fenSplit(1));

        val attacks: List[(Int, Int)] = List((-2, 1), (-2, -1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2), (-1, -2))

        @tailrec
        def sub(moves: List[(Int, Int)]): Boolean = {
            moves match {
                case Nil => false
                case (rd, cd) :: t => {
                    if (isAttacker(board, Piece(PieceType.KNIGHT, attackColor), pos, rd, cd)) {
                        return true
                    }
                    sub(t)
                }
            }
        }

        sub(attacks)
    }


    def horizontalAttack(fen: String, pos: Int): Boolean = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = PseudoMoves.extractColor(fenSplit(1));

        val attacks: List[(Int, Int)] = List((-1, 0), (1, 0), (0, 1), (0, -1))

        @tailrec
        def sub2(lr: Int, lc: Int, pos: Int): Boolean = {
            if (!PseudoMoves.onBoard(pos, lr, lc)) {
                return false;
            }

            board(pos + 8 * lr + lc) match {
                case Piece(e, attackColor) if e == PieceType.ROOK || e == PieceType.QUEEN => true;
                case Piece(PieceType.EMPTY, Color.EMPTY) => sub2(lr, lc, pos + 8 * lr + lc);
                case _ => false;
            }
        }


        @tailrec
        def sub(moves: List[(Int, Int)]): Boolean = {
            moves match {
                case Nil => false;
                case (rd, cd) :: t => {
                    if(sub2(rd, cd, pos)) {
                        true;
                    } else {
                        sub(t);
                    }
                }
            }
        }

        sub(attacks);
    }

    def verticalAttack(fen: String, pos: Int): Boolean = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = PseudoMoves.extractColor(fenSplit(1));

        val attacks: List[(Int, Int)] = List((1, 1), (-1, 1), (-1, -1), (1, -1))

        @tailrec
        def sub2(lr: Int, lc: Int, pos: Int): Boolean = {
            if (!PseudoMoves.onBoard(pos, lr, lc)) {
                return false;
            }

            board(pos + 8 * lr + lc) match {
                case Piece(e, attackColor) if e == PieceType.BISHOP || e == PieceType.QUEEN => true;
                case Piece(PieceType.EMPTY, Color.EMPTY) => sub2(lr, lc, pos + 8 * lr + lc);
                case _ => false;
            }
        }


        @tailrec
        def sub(moves: List[(Int, Int)]): Boolean = {
            moves match {
                case Nil => false;
                case (rd, cd) :: t => {
                    if (sub2(rd, cd, pos)) {
                        true;
                    } else {
                        sub(t);
                    }
                }
            }
        }

        sub(attacks);
    }

    def kingAttack(fen: String, pos: Int): Boolean = {

        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = PseudoMoves.extractColor(fenSplit(1));

        val attacks: List[(Int, Int)] = List((1, 1), (-1, 1), (-1, -1), (1, -1), (-1, 0), (1, 0), (0, 1), (0, -1))

        @tailrec
        def sub(moves: List[(Int, Int)]): Boolean = {
            moves match {
                case Nil => false
                case (rd, cd) :: t => {
                    if (isAttacker(board, Piece(PieceType.KING, attackColor), pos, rd, cd)) {
                        return true
                    }
                    sub(t)
                }
            }
        }

        sub(attacks)
    }



    def isPosAttacked(fen: String, pos: Int): Boolean = {
        pawnAttack(fen, pos) || knightAttack(fen, pos) || verticalAttack(fen, pos) || horizontalAttack(fen, pos) || verticalAttack(fen, pos) || kingAttack(fen, pos)
    }

    def makeMove(board: Vector[Piece], move: (Int, Int)): Vector[Piece] = {
        val (from, to) = move;
        val from_piece = board(from);
        board.updated(from, Piece(PieceType.EMPTY, Color.EMPTY)).updated(to, from_piece);
    }

    def isLegalMove(fen: String, move: (Int, Int)): Boolean = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = PseudoMoves.extractColor(fenSplit(1));
        val (from, to) = move;
        val kingPos: Int = PseudoMoves.piecePositions(board, Piece(PieceType.KING, moveColor)).head
        val moveFen = ChessBoard.boardToFen(makeMove(board, move)) + " " + fenSplit.tail.mkString
        isPosAttacked(moveFen, kingPos)
    }

}
