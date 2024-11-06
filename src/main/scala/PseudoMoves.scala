import Color.WHITE
import PieceType.EMPTY

import scala.annotation.tailrec

object PseudoMoves {

    def pseudoLegalPawnAttack(board: Vector[Piece], pos: Int, rd: Int, cd: Int, attackColor: Color): Boolean = {
        onBoard(pos, rd, cd) && board(pos + rd * 8 + cd).color == attackColor
    }

    def pseudoLegalPawnMove(board: Vector[Piece], pos: Int, rd: Int, cd: Int): Boolean = {
        if (Math.abs(rd) == 2) {
            onBoard(pos, rd, cd) && board(pos + rd * 8 + cd).color == Color.EMPTY && board(pos + (rd/2) * 8 + cd).color == Color.EMPTY
        } else {
            onBoard(pos, rd, cd) && board(pos + rd * 8 + cd).color == Color.EMPTY
        }
    }
    def pseudoLegalMove(board: Vector[Piece], pos: Int, rd: Int, cd: Int, attackColor: Color): Boolean = {
        onBoard(pos, rd, cd) && (board(pos + rd * 8 + cd).color == Color.EMPTY || board(pos + rd * 8 + cd).color == attackColor)
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
        val oldCol = pos % 8
        if (oldCol + cd < 0 || oldCol + cd > 7) {
            return false
        }
        if (pos / 8 != nc / 8) {
            return false
        }
        true
    }

    def piecePositions(board: Vector[Piece], piece: Piece): List[Int] = {
        @tailrec def sub(board: List[Piece], acc: List[Int], ind: Int): List[Int] = {
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

    def piecesPositions(board: Vector[Piece], pieces: List[Piece]): List[Int] = {
        @tailrec def sub(board: List[Piece], acc: List[Int], ind: Int): List[Int] = {
            board match {
                case Nil => acc
                case h :: t => {
                    if (pieces.contains(h)) {
                        sub(t, ind :: acc, ind + 1);
                    } else {
                        sub(t, acc, ind + 1);
                    }
                }
            }
        }

        sub(board.toList, List(), 0);
    }

    @tailrec def checkPawnMoves(board: Vector[Piece], pos: Int, moves: List[(Int, Int)], attacks: List[(Int, Int)], acc: List[(Int, Int)], attackColor: Color): List[(Int, Int)] = {
        attacks match {
            case Nil => {
                moves match {
                    case Nil => acc
                    case (rd, cd) :: t => {
                        if (pseudoLegalPawnMove(board, pos, rd, cd)) {
                            checkPawnMoves(board, pos, t, attacks, (pos, pos + rd * 8 + cd) :: acc, attackColor)
                        } else {
                            checkPawnMoves(board, pos, t, attacks, acc, attackColor)
                        }
                    }
                }
            }
            case (rd, cd) :: t => {
                if (pseudoLegalPawnAttack(board, pos, rd, cd, attackColor)) {
                    checkPawnMoves(board, pos, moves, t, (pos, pos + rd * 8 + cd) :: acc, attackColor)
                } else {
                    checkPawnMoves(board, pos, moves, t, acc, attackColor)
                }
            }
        }

    }

    def checkEnPassant(board: Vector[Piece], epPos: Int, attackColorNum: Int, moveColor: Color): List[(Int, Int)] = {
        val toCheck = List((attackColorNum * -1, attackColorNum * -1), (attackColorNum * -1, attackColorNum))

        @tailrec def sub1(li: List[(Int, Int)], acc: List[(Int, Int)]): List[(Int, Int)] = {
            li match {
                case Nil => acc
                case (rd, cd) :: t => {
                    if (onBoard(epPos, rd, cd) && board(epPos + 8 * rd + cd) == Piece(PieceType.PAWN, moveColor)) {
                        sub1(t, (epPos + 8 * rd + cd, epPos) :: acc)
                    } else {
                        sub1(t, acc)
                    }
                }
            }
        }

        sub1(toCheck, List());
    }


    def pseudoPawnMoves(fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val attackMoves = List((attackColorNum, attackColorNum), (attackColorNum, attackColorNum * -1));
        val straightMovesBase = List((attackColorNum, 0), (attackColorNum * 2, 0));
        val straightMoves = List((attackColorNum, 0));

        val piecePos = piecePositions(board, Piece(PieceType.PAWN, moveColor));

        @tailrec def sub1(acc: List[(Int, Int)], piecePos: List[Int]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    if ((h > 7 && h < 16 && attackColorNum == 1) || (h > 47 && h < 56 && attackColorNum == -1)) {
                        sub1(checkPawnMoves(board, h, straightMovesBase, attackMoves, acc, attackColor), t)
                    } else {
                        sub1(checkPawnMoves(board, h, straightMoves, attackMoves, acc, attackColor), t)
                    }

                }
            }
        }

        if (fenSplit(3) != "-") {
            sub1(checkEnPassant(board, ChessBoard.coordinatesToIndex(fenSplit(3)), attackColorNum, moveColor), piecePos);
        } else {
            sub1(List(), piecePos);
        }

    }

    @tailrec def checkMoves(board: Vector[Piece], pos: Int, moves: List[(Int, Int)], acc: List[(Int, Int)], attackColor: Color): List[(Int, Int)] = {
        moves match {
            case Nil => acc;
            case (rd, cd) :: t => {
                if (pseudoLegalMove(board, pos, rd, cd, attackColor)) {
                    checkMoves(board, pos, t, (pos, pos + rd * 8 + cd) :: acc, attackColor)
                } else {
                    checkMoves(board, pos, t, acc, attackColor)
                }
            }
        }

    }

    def pseudoKnightMoves(res: List[(Int, Int)], fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val moves = List((-2, 1), (-2, -1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2), (-1, -2));

        val piecePos = piecePositions(board, Piece(PieceType.KNIGHT, moveColor));


        @tailrec def sub1(acc: List[(Int, Int)], piecePos: List[Int]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    sub1(checkMoves(board, h, moves, acc, attackColor), t)
                }
            }
        }
        sub1(res, piecePos);
    }

    def pseudoKingMoves(res: List[(Int, Int)], fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val moves = List((1, 1), (-1, 1), (-1, -1), (1, -1), (-1, 0), (1, 0), (0, 1), (0, -1))

        val piecePos = piecePositions(board, Piece(PieceType.KING, moveColor));


        @tailrec def sub1(acc: List[(Int, Int)], piecePos: List[Int]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    sub1(checkMoves(board, h, moves, acc, attackColor), t)
                }
            }
        }

        val mapKastleToMove: Map[Char, (Int, Int)] = Map(
            'K' -> (-1, -1),
            'Q' -> (-2, -1),
            'k' -> (-3, -1),
            'q' -> (-4, -1)
        )

        def emptyForKastle(c: Char): Boolean = {
            c match {
                case 'K' => board(61).pieceType == PieceType.EMPTY && board(62).pieceType == PieceType.EMPTY
                case 'Q' => board(59).pieceType == PieceType.EMPTY && board(58).pieceType == PieceType.EMPTY && board(57).pieceType == PieceType.EMPTY
                case 'k' => board(5).pieceType == PieceType.EMPTY && board(6).pieceType == PieceType.EMPTY
                case 'q' => board(1).pieceType == PieceType.EMPTY && board(2).pieceType == PieceType.EMPTY && board(3).pieceType == PieceType.EMPTY

            }
        }

        @tailrec
        def sub2(acc: List[(Int, Int)], fenCastle: List[Char]): List[(Int, Int)] = {
            fenCastle match {
                case Nil => acc;
                case h :: t => {
                    h match {
                        case '-' => acc
                        case castleType => {
                            if (((moveColor == WHITE && castleType.isUpper) || (moveColor == Color.BLACK && castleType.isLower)) && emptyForKastle(castleType)) {
                                sub2(mapKastleToMove(castleType)::acc, t);
                            } else {
                                sub2(acc, t);
                            }
                        }
                    }
                }
            }
        }



        sub1(sub2(res, fenSplit(2).toList), piecePos);
    }


    @tailrec def checkContinuingMoves(board: Vector[Piece], pos: Int, direction: (Int, Int), movelength: Int, acc: List[(Int, Int)], attackColor: Color): List[(Int, Int)] = {

        val (rd, cd) = direction
        if(pseudoLegalMove(board, pos, rd, cd, attackColor)){
            checkContinuingMoves(board, pos + 8 * rd + cd, direction, movelength + 1, (pos, pos + rd * 8 * movelength + cd * movelength) :: acc, attackColor);
        } else {
            acc
        }
    }

    def pseudoRookMoves(fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val directions: List[(Int, Int)] = List((-1, 0), (1, 0), (0, 1), (0, -1));

        val piecePos = piecePositions(board, Piece(PieceType.ROOK, moveColor));

        @tailrec def rookMovesRecursive(acc: List[(Int, Int)], piecePos: List[Int], moveDirections: List[(Int, Int)]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    moveDirections match {
                        case Nil => rookMovesRecursive(acc, t, directions)
                        case k :: s => {
                            rookMovesRecursive(checkContinuingMoves(board, h, k, 1, acc, attackColor) ::: acc, piecePos, s);
                        }
                    }
                }
            }
        }
        rookMovesRecursive(List(), piecePos, directions);
    }


    def pseudoRookAndQueenMoves2(res: List[(Int, Int)], fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val directions: List[(Int, Int)] = List((-1, 0), (1, 0), (0, 1), (0, -1));

        val piecePos = piecesPositions(board, List(Piece(PieceType.ROOK, moveColor), Piece(PieceType.QUEEN, moveColor)));

        @tailrec
        def sub2(acc: List[(Int, Int)], piecePos: Int, moveDir: (Int, Int)): List[(Int, Int)] = {
            val (r, c) = moveDir;
            if (pseudoLegalMove(board, piecePos, r, c, attackColor)) {
                val new_pos = piecePos + 8 * r + c;
                sub2((piecePos, new_pos)::acc, new_pos, moveDir);
            } else {
                acc;
            }
        }

        @tailrec
        def sub1(acc: List[(Int, Int)], piecePos: Int, moveDirections: List[(Int, Int)]): List[(Int, Int)] = {
            moveDirections match {
                case Nil => acc;
                case h :: t => sub1(sub2(acc, piecePos, h), piecePos, t);
            }

        }

        @tailrec def rookMovesRecursive(acc: List[(Int, Int)], piecePos: List[Int]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    rookMovesRecursive(sub1(acc, h, directions), t);
                }
            }
        }

        rookMovesRecursive(res, piecePos);
    }

    def pseudoBishopAndQueenMoves1(res: List[(Int, Int)], fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val directions: List[(Int, Int)] = List((-1, 1), (1, 1), (1, -1), (-1, -1));

        val piecePos = piecesPositions(board, List(Piece(PieceType.BISHOP, moveColor), Piece(PieceType.QUEEN, moveColor)));

        @tailrec
        def sub2(acc: List[(Int, Int)], piecePos: Int, moveDir: (Int, Int)): List[(Int, Int)] = {
            val (r, c) = moveDir;
            if (pseudoLegalMove(board, piecePos, r, c, attackColor)) {
                val new_pos = piecePos + 8 * r + c;
                sub2((piecePos, new_pos) :: acc, new_pos, moveDir);
            } else {
                acc;
            }
        }

        @tailrec
        def sub1(acc: List[(Int, Int)], piecePos: Int, moveDirections: List[(Int, Int)]): List[(Int, Int)] = {
            moveDirections match {
                case Nil => acc;
                case h :: t => sub1(sub2(acc, piecePos, h), piecePos, t);
            }

        }

        @tailrec def movesRecursive(acc: List[(Int, Int)], piecePos: List[Int]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    movesRecursive(sub1(acc, h, directions), t);
                }
            }
        }

        movesRecursive(res, piecePos);
    }

    def getAllPseudoLegalMoves(fen: String): List[(Int, Int)] = {
        pseudoBishopAndQueenMoves1(pseudoRookAndQueenMoves2(pseudoKingMoves(pseudoKnightMoves(pseudoPawnMoves(fen), fen), fen), fen), fen);
    }


}