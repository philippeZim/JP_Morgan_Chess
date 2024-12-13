package Model

import Model.Color.WHITE
import Model.PieceType.EMPTY
import Model.{ChessBoard, Color, Piece, PieceType}

import scala.annotation.tailrec

object PseudoMoves {
    /**
     * this method determines if a pawn attack is a pseudo legal pawn attack
     * @param board          current board state
     * @param piecePosition  current position of the pawn
     * @param rowDirection   the number of rows the piece should be moved. A negative number stands for upward movement. A positive Number stands for downward movement.
     * @param columDirection the number of colums the piece should be moved. A negative number stands for leftward movement. A positive Number stands for rightward movement
     * @return true if the piece can attack there, false if it can't */
    def pseudoLegalPawnAttack(board: Vector[Piece], piecePosition: Int, rowDirection: Int, columDirection: Int, attackColor: Color): Boolean = {
        onBoard(piecePosition, rowDirection, columDirection) && board(piecePosition + rowDirection * 8 + columDirection).color == attackColor
    }

    /**
     * this method determines if a pawn move is a pseudo legal pawn move
     * @param board current board state
     * @param piecePosition current position of the pawn
     * @param rowDirection the number of rows the piece should be moved. A negative number stands for upward movement. A positive Number stands for downward movement.
     * @param columDirection the number of colums the piece should be moved. A negative number stands for leftward movement. A positive Number stands for rightward movement
     * @return true if the piece can move there, false if it can't
     */
    def pseudoLegalPawnMove(board: Vector[Piece], piecePosition: Int, rowDirection: Int, columDirection: Int): Boolean = {
        if (Math.abs(rowDirection) == 2) {
            onBoard(piecePosition, rowDirection, columDirection) && board(piecePosition + rowDirection * 8 + columDirection).color == Color.EMPTY && board(piecePosition + (rowDirection/2) * 8 + columDirection).color == Color.EMPTY
        } else {
            onBoard(piecePosition, rowDirection, columDirection) && board(piecePosition + rowDirection * 8 + columDirection).color == Color.EMPTY
        }
    }

    /**
     * this method checks if the given move (given by row- and columdirection) is a correct pseudomove, meaning if the target square is
     * on the board and if it's empty or taken by an enemy piece. If that's the case it will return true. Blocking pieces will not be taken into account.
     * @param board current board
     * @param piecePosition the current position of the piece, who's move is to be checked
     * @param rowDirection the number of rows the piece should be moved. A negative number stands for upward movement. A positive Number stands for downward movement.
     * @param columdirection the number of colums the piece should be moved. A negative number stands for leftward movement. A positive Number stands for rightward movement
     * @param attackColor the color that isn't moving next
     * @return true if the piece can move there (not considering blocking pieces), false if it can't
     */
    def pseudoLegalMove(board: Vector[Piece], piecePosition: Int, rowDirection: Int, columdirection: Int, attackColor: Color): Boolean = {
        //onBoard(piecePosition, rowDirection, columdirection) && (board(piecePosition + rowDirection * 8 + columdirection).color == Color.EMPTY || board(piecePosition + rowDirection * 8 + columdirection).color == attackColor)
        val handler = new OnBoardHandler(Some(new EmptySquareHandler(Some(new EnemySquareHandler(None)))))
        handler.handle(piecePosition, rowDirection, columdirection, board, attackColor)
    }

    /**
     * this determines which color is to move on the current board
     * @param color the part of the fen String giving information about which color is to move
     * @return Int = the general direction these pieces move to attack (is used for pawns and Kings), Color 1 = the color to move, Color 2 = the color that is not to move
     */
    def extractColor(color: String): (Int, Color, Color) = {
        color match {
            case "w" => (-1, Color.WHITE, Color.BLACK);
            case "b" => (1, Color.BLACK, Color.WHITE);
        }
    }

    /**
     * on Board determines if a move from a starting position is still on the board
     * @param beginningPosition the current position of the piece that is to be moved
     * @param rowDirection the number of rows the piece should be moved. A negative number stands for upward movement. A positive Number stands for downward movement.
     * @param columDirection the number of colums the piece should be moved. A negative number stands for leftward movement. A positive Number stands for rightward movement
     * @return true = the endposition is still on the board. false = the endposition isn't on the board
     */
    def onBoard(beginningPosition: Int, rowDirection: Int, columDirection: Int): Boolean = {
        val newRow = beginningPosition + rowDirection * 8
        if (newRow < 0 || newRow > 63) {
            return false
        }
        val newColum = beginningPosition + columDirection
        val oldColum = beginningPosition % 8
        if (oldColum + columDirection < 0 || oldColum + columDirection > 7) {
            return false
        }
        true
    }

    /**
     * this method extracts all indices from a given board on which the given Piece stands
     * @param board current board state
     * @param piece PieceType if which the position is to be determined
     * @return List of all indices of the board on which the given Piecetype stands
     */
    def piecePositions(board: Vector[Piece], piece: Piece): List[Int] = {
        @tailrec def sub(board: List[Piece], accumulator: List[Int], index: Int): List[Int] = {
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

    /**
     * this method determines all positions of a given pieces
     * @param board current board state
     * @param pieces pieceType that is the be searched
     * @return List of indices of the current positions of the given piece
     */
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

  /* def piecePositions(board: Vector[Piece], piece: Piece): List[Int] = {
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
    }  */

    /**
     * this method collects all pseudo legal pawn moves of a given pawn
     * @param board current board
     * @param pawnPostion Postion of the pawn, who's move is to be checked
     * @param moves List of the possible pawn moves (so 1 or 2 forward)
     * @param attacks List of the possible attack moves (so either left or right vertical)
     * @param accumulator this List gathers all possible moves
     * @param attackColor the color whos pieces could be taken this turn
     * @return List of all possible moves of this pawn
     */
    @tailrec def checkPawnMoves(board: Vector[Piece], pawnPostion: Int, moves: List[(Int, Int)], attacks: List[(Int, Int)], accumulator: List[(Int, Int)], attackColor: Color): List[(Int, Int)] = {
        attacks match {
            case Nil => {
                moves match {
                    case Nil => accumulator
                    case (rowDirection, columDirection) :: t => {
                        if (pseudoLegalPawnMove(board, pawnPostion, rowDirection, columDirection)) {
                            checkPawnMoves(board, pawnPostion, t, attacks, (pawnPostion, pawnPostion + rowDirection * 8 + columDirection) :: accumulator, attackColor)
                        } else {
                            checkPawnMoves(board, pawnPostion, t, attacks, accumulator, attackColor)
                        }
                    }
                }
            }
            case (rowDirection, columDirection) :: t => {
                if (pseudoLegalPawnAttack(board, pawnPostion, rowDirection, columDirection, attackColor)) {
                    checkPawnMoves(board, pawnPostion, moves, t, (pawnPostion, pawnPostion + rowDirection * 8 + columDirection) :: accumulator, attackColor)
                } else {
                    checkPawnMoves(board, pawnPostion, moves, t, accumulator, attackColor)
                }
            }
        }

    }

    /**
     * this method gathers all possible en passant moves of a given pawn piece
     * @param board current board
     * @param enPassantPosition Position of position that hos been jumped over
     * @param attackColorNumber 1 if it's black's turn and the pawns move down, -1 if it's white's turn and the pawns move up
     * @param moveColor the color that is to move on the board
     * @return all possible en passant moves for the given pawn
     */
    def checkEnPassant(board: Vector[Piece], enPassantPosition: Int, attackColorNumber: Int, moveColor: Color): List[(Int, Int)] = {
        val toCheck = List((attackColorNumber * -1, attackColorNumber * -1), (attackColorNumber * -1, attackColorNumber))

        @tailrec def sub1(li: List[(Int, Int)], acc: List[(Int, Int)]): List[(Int, Int)] = {
            li match {
                case Nil => acc
                case (rd, cd) :: t => {
                    if (onBoard(enPassantPosition, rd, cd) && board(enPassantPosition + 8 * rd + cd) == Piece(PieceType.PAWN, moveColor)) {
                        sub1(t, (enPassantPosition + 8 * rd + cd, enPassantPosition) :: acc)
                    } else {
                        sub1(t, acc)
                    }
                }
            }
        }

        sub1(toCheck, List());
    }

    /**
     * this method gathers all possible pawn moves on a given board
     * @param fen the current board state as fen
     * @return List of all possible pawn moves on the current board (only for the color that is to move)
     */
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

    /**
     * this method checks if given moves for a given piece are pseudo legal moves and returns all that are as a List (used for Kings and Knights)
     * @param board current board state
     * @param piecePosition the position of the piece, who's moves are to be checked
     * @param moves List of all possible moves of this piece (not considering the board state)
     * @param accumulator gathers the pseudo legal moves
     * @param attackColor the color that is not to move
     * @return all pseudo legal moves for the given piece
     */
    @tailrec def checkMoves(board: Vector[Piece], piecePosition: Int, moves: List[(Int, Int)], accumulator: List[(Int, Int)], attackColor: Color): List[(Int, Int)] = {
        moves match {
            case Nil => accumulator;
            case (rd, cd) :: t => {
                if (pseudoLegalMove(board, piecePosition, rd, cd, attackColor)) {
                    checkMoves(board, piecePosition, t, (piecePosition, piecePosition + rd * 8 + cd) :: accumulator, attackColor)
                } else {
                    checkMoves(board, piecePosition, t, accumulator, attackColor)
                }
            }
        }

    }

    /**
     * this method gathers all pseudo legal Knight moves on the current board
     * @param result this List will gather all pseudo legal moves
     * @param fen current board state
     * @return List of all pseudo legal Knight moves
     */
    def pseudoKnightMoves(result: List[(Int, Int)], fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val moves = List((-2, 1), (-2, -1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2), (-1, -2));

        val piecePos = piecePositions(board, Piece(PieceType.KNIGHT, moveColor));


        @tailrec def sub1(accumulator: List[(Int, Int)], piecePosition: List[Int]): List[(Int, Int)] = {
            piecePosition match {
                case Nil => accumulator
                case h :: t => {
                    sub1(checkMoves(board, h, moves, accumulator, attackColor), t)
                }
            }
        }
        sub1(result, piecePos);
    }

    /**
     * this method gathers all pseudo legal King moves on the current board
     * @param result this List will gather all pseudo legal moves
     * @param fen current board state
     * @return List of all pseudo legal King moves
     */
    def pseudoKingMoves(result: List[(Int, Int)], fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val moves = List((1, 1), (-1, 1), (-1, -1), (1, -1), (-1, 0), (1, 0), (0, 1), (0, -1))

        val currentPiecePositions = piecePositions(board, Piece(PieceType.KING, moveColor));


        @tailrec def sub1(accumulator: List[(Int, Int)], piecePosistions: List[Int]): List[(Int, Int)] = {
            piecePosistions match {
                case Nil => accumulator
                case h :: t => {
                    sub1(checkMoves(board, h, moves, accumulator, attackColor), t)
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



        sub1(sub2(result, fenSplit(2).toList), currentPiecePositions);
    }



    def pseudoHorizontalMoves(res: List[(Int, Int)], fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val directions: List[(Int, Int)] = List((-1, 0), (1, 0), (0, 1), (0, -1));

        val piecePos = piecesPositions(board, List(Piece(PieceType.ROOK, moveColor), Piece(PieceType.QUEEN, moveColor)));

        @tailrec
        def sub2(acc: List[(Int, Int)], piecePos: Int, startingPosition: Int, moveDir: (Int, Int)): List[(Int, Int)] = {
            val (rowDirection, columDirection) = moveDir
            if (!PseudoMoves.onBoard(piecePos, rowDirection, columDirection)) {
                return acc
            }
            board(piecePos + 8 * rowDirection + columDirection) match {
                case Piece(_, `attackColor`) => (startingPosition, piecePos + 8* rowDirection + columDirection) :: acc
                case Piece(PieceType.EMPTY, Color.EMPTY) => sub2((startingPosition, piecePos + 8* rowDirection + columDirection) :: acc, piecePos + 8* rowDirection + columDirection, startingPosition, moveDir);
                case _ => acc;
            }
        }

        @tailrec
        def sub1(acc: List[(Int, Int)], piecePos: Int, moveDirections: List[(Int, Int)]): List[(Int, Int)] = {
            moveDirections match {
                case Nil => acc;
                case h :: t => sub1(sub2(acc, piecePos, piecePos, h), piecePos, t);
            }
        }

        @tailrec def rookAndQueenMovesRecursive(acc: List[(Int, Int)], piecePos: List[Int]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    rookAndQueenMovesRecursive(sub1(acc, h, directions), t);
                }
            }
        }
        rookAndQueenMovesRecursive(res, piecePos);
    }

    def pseudoVerticalMoves(res: List[(Int, Int)], fen: String): List[(Int, Int)] = {
        val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
        val fenSplit: List[String] = fen.split(" ").toList;

        val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = extractColor(fenSplit(1));

        val directions: List[(Int, Int)] = List((-1, 1), (1, 1), (1, -1), (-1, -1));

        val piecePos = piecesPositions(board, List(Piece(PieceType.BISHOP, moveColor), Piece(PieceType.QUEEN, moveColor)));

        @tailrec
        def sub2(acc: List[(Int, Int)], piecePos: Int, startingPosition: Int, moveDir: (Int, Int)): List[(Int, Int)] = {
            val (rowDirection, columDirection) = moveDir
            if (!PseudoMoves.onBoard(piecePos, rowDirection, columDirection)) {
                return acc
            }

            board(piecePos + 8 * rowDirection + columDirection) match {
                case Piece(_, `attackColor`) => (startingPosition, piecePos + 8 * rowDirection + columDirection) :: acc
                case Piece(PieceType.EMPTY, Color.EMPTY) => sub2((startingPosition, piecePos + 8 * rowDirection + columDirection) :: acc, piecePos + 8 * rowDirection + columDirection, startingPosition, moveDir);
                case _ => acc;
            }
        }

        @tailrec
        def sub1(acc: List[(Int, Int)], piecePos: Int, moveDirections: List[(Int, Int)]): List[(Int, Int)] = {
            moveDirections match {
                case Nil => acc;
                case h :: t => sub1(sub2(acc, piecePos, piecePos, h), piecePos, t);
            }

        }

        @tailrec def verticalMovesRecursive(acc: List[(Int, Int)], piecePos: List[Int]): List[(Int, Int)] = {
            piecePos match {
                case Nil => acc
                case h :: t => {
                    verticalMovesRecursive(sub1(acc, h, directions), t);
                }
            }
        }

        verticalMovesRecursive(res, piecePos);
    }

   /* def getAllPseudoLegalMoves(fen: String): List[(Int, Int)] = {
        pseudoVerticalMoves(pseudoHorizontalMoves(pseudoKingMoves(pseudoKnightMoves(pseudoPawnMoves(fen), fen), fen), fen), fen);
    }*/


}