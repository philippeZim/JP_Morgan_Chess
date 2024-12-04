package Model

import scala.annotation.tailrec
import scala.util.control.Breaks._

object Engine {


    def material(fen: String): Double = {

        val pawnPosW: Vector[Double] = Vector(
            0,   0,   0,   0,   0,   0,   0,   0,
            0.50, 0.50, 0.50, 0.50, 0.50, 0.50, 0.50, 0.50,
            0.10, 0.10, 0.20, 0.30, 0.30, 0.20, 0.10, 0.10,
            0.05, 0.05, 0.10, 0.25, 0.25, 0.10, 0.05, 0.05,
            0,    0,    0,    0.20, 0.20, 0,    0,    0,
            0.05, -0.05, -0.10, 0,    0,   -0.10, -0.05, 0.05,
            0.05, 0.10, 0.10, -0.20, -0.20, 0.10, 0.10, 0.05,
            0,    0,    0,    0,    0,    0,    0,    0
        )

        val pawnPosB: Vector[Double] = Vector(
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
            0.05, 0.1, 0.1, -0.2, -0.2, 0.1, 0.1, 0.05,
            0.05, -0.05, -0.1, 0.0, 0.0, -0.1, -0.05, 0.05,
            0.0, 0.0, 0.0, 0.2, 0.2, 0.0, 0.0, 0.0,
            0.05, 0.05, 0.1, 0.25, 0.25, 0.1, 0.05, 0.05,
            0.1, 0.1, 0.2, 0.3, 0.3, 0.2, 0.1, 0.1,
            0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
        )

        val qeenPosW: Vector[Double] = Vector(
            -0.20, -0.10, -0.10, -0.05, -0.05, -0.10, -0.10, -0.20,
            -0.10, 0,    0,    0,    0,    0,    0,   -0.10,
            -0.10, 0,    0.05, 0.05, 0.05, 0.05, 0,    -0.10,
            -0.05, 0,    0.05, 0.05, 0.05, 0.05, 0,   -0.05,
            0,    0,    0.05, 0.05, 0.05, 0.05, 0,   -0.05,
            -0.10, 0.05, 0.05, 0.05, 0.05, 0.05, 0,   -0.10,
            -0.10, 0,    0.05, 0,    0,    0,    0,   -0.10,
            -0.20, -0.10, -0.10, -0.05, -0.05, -0.10, -0.10, -0.20
        )

        val qeenPosB: Vector[Double] = Vector(
            -0.2, -0.1, -0.1, -0.05, -0.05, -0.1, -0.1, -0.2,
            -0.1, 0.0, 0.0, 0.0, 0.0, 0.05, 0.0, -0.1,
            -0.1, 0.0, 0.05, 0.05, 0.05, 0.05, 0.05, -0.1,
            -0.05, 0.0, 0.05, 0.05, 0.05, 0.05, 0.0, 0.0,
            -0.05, 0.0, 0.05, 0.05, 0.05, 0.05, 0.0, -0.05,
            -0.1, 0.0, 0.05, 0.05, 0.05, 0.05, 0.0, -0.1,
            -0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.1,
            -0.2, -0.1, -0.1, -0.05, -0.05, -0.1, -0.1, -0.2
        )

        val kingPosW: Vector[Double] = Vector(
            -0.30, -0.40, -0.40, -0.50, -0.50, -0.40, -0.40, -0.30,
            -0.30, -0.40, -0.40, -0.50, -0.50, -0.40, -0.40, -0.30,
            -0.30, -0.40, -0.40, -0.50, -0.50, -0.40, -0.40, -0.30,
            -0.30, -0.40, -0.40, -0.50, -0.50, -0.40, -0.40, -0.30,
            -0.20, -0.30, -0.30, -0.40, -0.40, -0.30, -0.30, -0.20,
            -0.10, -0.20, -0.20, -0.20, -0.20, -0.20, -0.20, -0.10,
            0.20, 0.20, 0,    0,    0,    0,    0.20, 0.20,
            0.20, 0.30, 0.10, 0,    0,    0.10, 0.30, 0.20
        )

        val kingPosB: Vector[Double] = Vector(
            0.2, 0.3, 0.1, 0.0, 0.0, 0.1, 0.3, 0.2,
            0.2, 0.2, 0.0, 0.0, 0.0, 0.0, 0.2, 0.2,
            -0.1, -0.2, -0.2, -0.2, -0.2, -0.2, -0.2, -0.1,
            -0.2, -0.3, -0.3, -0.4, -0.4, -0.3, -0.3, -0.2,
            -0.3, -0.4, -0.4, -0.5, -0.5, -0.4, -0.4, -0.3,
            -0.3, -0.4, -0.4, -0.5, -0.5, -0.4, -0.4, -0.3,
            -0.3, -0.4, -0.4, -0.5, -0.5, -0.4, -0.4, -0.3,
            -0.3, -0.4, -0.4, -0.5, -0.5, -0.4, -0.4, -0.3
        )

        val rookPosW: Vector[Double] = Vector(
            0,    0,    0,    0,    0,    0,    0,    0,
            0.05, 0.10, 0.10, 0.10, 0.10, 0.10, 0.10, 0.05,
            -0.05, 0,    0,    0,    0,    0,    0,   -0.05,
            -0.05, 0,    0,    0,    0,    0,    0,   -0.05,
            -0.05, 0,    0,    0,    0,    0,    0,   -0.05,
            -0.05, 0,    0,    0,    0,    0,    0,   -0.05,
            -0.05, 0,    0,    0,    0,    0,    0,   -0.05,
            0,    0,    0,    0.05, 0.05, 0,    0,    0

        )
        val rookPosB: Vector[Double] = Vector(
            0.0, 0.0, 0.0, 0.05, 0.05, 0.0, 0.0, 0.0,
            -0.05, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.05,
            -0.05, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.05,
            -0.05, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.05,
            -0.05, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.05,
            -0.05, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.05,
            0.05, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.05,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
        )

        val bishopPosW: Vector[Double] = Vector(
            -0.20, -0.10, -0.10, -0.10, -0.10, -0.10, -0.10, -0.20,
            -0.10, 0,    0,    0,    0,    0,    0,    -0.10,
            -0.10, 0,    0.05, 0.10, 0.10, 0.05, 0,    -0.10,
            -0.10, 0.05, 0.05, 0.10, 0.10, 0.05, 0.05, -0.10,
            -0.10, 0,    0.10, 0.10, 0.10, 0.10, 0,    -0.10,
            -0.10, 0.10, 0.10, 0.10, 0.10, 0.10, 0.10, -0.10,
            -0.10, 0.05, 0,    0,    0,    0,    0.05, -0.10,
            -0.20, -0.10, -0.10, -0.10, -0.10, -0.10, -0.10, -0.20
        )
        val bishopPosB: Vector[Double] = Vector(
            -0.2, -0.1, -0.1, -0.1, -0.1, -0.1, -0.1, -0.2,
            -0.1, 0.05, 0.0, 0.0, 0.0, 0.0, 0.05, -0.1,
            -0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, -0.1,
            -0.1, 0.0, 0.1, 0.1, 0.1, 0.1, 0.0, -0.1,
            -0.1, 0.05, 0.05, 0.1, 0.1, 0.05, 0.05, -0.1,
            -0.1, 0.0, 0.05, 0.1, 0.1, 0.05, 0.0, -0.1,
            -0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.1,
            -0.2, -0.1, -0.1, -0.1, -0.1, -0.1, -0.1, -0.2
        )
        val knightPosW: Vector[Double] = Vector(
            -0.50, -0.40, -0.30, -0.30, -0.30, -0.30, -0.40, -0.50,
            -0.40, -0.20, 0,    0,    0,    0,   -0.20, -0.40,
            -0.30, 0,    0.10, 0.15, 0.15, 0.10, 0,    -0.30,
            -0.30, 0.05, 0.15, 0.20, 0.20, 0.15, 0.05, -0.30,
            -0.30, 0,    0.15, 0.20, 0.20, 0.15, 0,    -0.30,
            -0.30, 0.05, 0.10, 0.15, 0.15, 0.10, 0.05, -0.30,
            -0.40, -0.20, 0,    0.05, 0.05, 0,   -0.20, -0.40,
            -0.50, -0.40, -0.30, -0.30, -0.30, -0.30, -0.40, -0.50
        )
        val knightPosB: Vector[Double] = Vector(
            -0.5, -0.4, -0.3, -0.3, -0.3, -0.3, -0.4, -0.5,
            -0.4, -0.2, 0.0, 0.05, 0.05, 0.0, -0.2, -0.4,
            -0.3, 0.05, 0.1, 0.15, 0.15, 0.1, 0.05, -0.3,
            -0.3, 0.0, 0.15, 0.2, 0.2, 0.15, 0.0, -0.3,
            -0.3, 0.05, 0.15, 0.2, 0.2, 0.15, 0.05, -0.3,
            -0.3, 0.0, 0.1, 0.15, 0.15, 0.1, 0.0, -0.3,
            -0.4, -0.2, 0.0, 0.0, 0.0, 0.0, -0.2, -0.4,
            -0.5, -0.4, -0.3, -0.3, -0.3, -0.3, -0.4, -0.5
        )

        @tailrec
        def sub1(li: List[(Piece, Int)], acc: Double): Double = {
            li match {
                case Nil => acc
                case (e, i) :: t => e match {
                    case Piece(PieceType.QUEEN, Color.WHITE) => sub1(t, acc + 9.0 + qeenPosW(i))
                    case Piece(PieceType.QUEEN, Color.BLACK) => sub1(t, acc - 9.0 + qeenPosB(i))
                    case Piece(PieceType.KING, Color.WHITE) => sub1(t, acc + kingPosW(i))
                    case Piece(PieceType.KING, Color.BLACK) => sub1(t, acc + kingPosB(i))
                    case Piece(PieceType.ROOK, Color.WHITE) => sub1(t, acc + 5.0 + rookPosW(i))
                    case Piece(PieceType.ROOK, Color.BLACK) => sub1(t, acc - 5.0 + rookPosB(i))
                    case Piece(PieceType.BISHOP, Color.WHITE) => sub1(t, acc + 3.0 + bishopPosW(i))
                    case Piece(PieceType.BISHOP, Color.BLACK) => sub1(t, acc - 3.0 + bishopPosB(i))
                    case Piece(PieceType.KNIGHT, Color.WHITE) => sub1(t, acc + 2.5 + knightPosW(i))
                    case Piece(PieceType.KNIGHT, Color.BLACK) => sub1(t, acc - 2.5 + knightPosB(i))
                    case Piece(PieceType.PAWN, Color.WHITE) => sub1(t, acc + 1.0 + pawnPosW(i))
                    case Piece(PieceType.PAWN, Color.BLACK) => sub1(t, acc - 1.0 + pawnPosB(i))
                    case _ => sub1(t, acc)
                }
            }
        }

        val board = ChessBoard.fenToBoard(fen).toList
        sub1(board.zipWithIndex, 0.0)
    }



    def evaluate(fen: String): Double = {
        val moves = LegalMoves.getAllLegalMoves(fen)
        if (Remis.isRemis(fen, moves)) {
            return 0.0
        }
        val fenSplit = fen.split(" ")
        if (moves.isEmpty) {
            fenSplit(1) match {
                case "w" => return -9999.0
                case "b" => return 9999.0
            }
        }
        material(fen)
    }

    def game_over(fen: String, legalMoves: List[(Int, Int)]): Boolean = {
        if (Remis.isRemis(fen, legalMoves)) {
            return true
        }
        val fenSplit = fen.split(" ")
        if (legalMoves.isEmpty) {
            return true
        }
        false
    }
    var see = 0

    def minimax(fen: String, depth: Int): Double = {
        //see += 1
        //println(see + " " + depth)
        val moves = LegalMoves.getAllLegalMoves(fen)

        if (game_over(fen, moves) || depth == 0) {
            return evaluate(fen)
        }

        val fenSplit = fen.split(" ")
        var best = 0.0
        fenSplit(1) match {
            case "w" => {
                best = -10000.0
                for (move <- moves) {
                    val originalFen = fen // Copy the FEN string
                    val lFen = ChessBoard.makeMove(originalFen, move) // Make move on the copy
                    val tempVal = minimax(lFen, depth - 1)
                    // No need to undo, we just use the originalFen in the next iteration
                    if (tempVal > best) {
                        best = tempVal
                    }
                }
            }
            case "b" => {
                best = 10000.0
                for (move <- moves) {
                    val originalFen = fen // Copy the FEN string
                    val lFen = ChessBoard.makeMove(originalFen, move) // Make move on the copy
                    val tempVal = minimax(lFen, depth - 1)
                    // No need to undo, we just use the originalFen in the next iteration
                    if (tempVal < best) {
                        best = tempVal
                    }
                }
            }
        }
        best
    }

    def minimaxAB(fen: String, depth: Int, alpha: Double, beta: Double): Double = {
        see += 1
        println(see + " " + depth)
        val moves = LegalMoves.getAllLegalMoves(fen)

        if (game_over(fen, moves) || depth == 0) {
            return evaluate(fen)
        }

        val fenSplit = fen.split(" ")
        fenSplit(1) match {
            case "w" => { // Maximizing player (White)
                var a = alpha
                var best = -10000.0
                for (move <- moves) {
                    val originalFen = fen
                    val lFen = ChessBoard.makeMove(originalFen, move)
                    val tempVal = minimaxAB(lFen, depth - 1, a, beta)
                    if (tempVal > best) {
                        best = tempVal
                    }
                    a = Math.max(a, best) // Update alpha
                    if (beta <= a) {
                        return best // Beta cutoff
                    }
                }
                best
            }
            case "b" => { // Minimizing player (Black)
                var b = beta
                var best = 10000.0
                for (move <- moves) {
                    val originalFen = fen
                    val lFen = ChessBoard.makeMove(originalFen, move)
                    val tempVal = minimaxAB(lFen, depth - 1, alpha, b)
                    if (tempVal < best) {
                        best = tempVal
                    }
                    b = Math.min(b, best) // Update beta
                    if (b <= alpha) {
                        return best // Alpha cutoff
                    }
                }
                best
            }
        }
    }

    def find_min(li: List[((Int, Int), Double)]): ((Int, Int), Double) = {
        @tailrec
        def sub1(sub_li: List[((Int, Int), Double)], res: ((Int, Int), Double)): ((Int, Int), Double) = {
            sub_li match {
                case Nil => res
                case (x, v)::t =>
                    if (v < res._2) {
                        sub1(t, (x, v))
                    } else {
                        sub1(t, res)
                    }
            }
        }
        sub1(li, ((-1, -1), Double.MaxValue))
    }

    def play(fen: String): (Int, Int) = {
        val moves = LegalMoves.getAllLegalMoves(fen)
        val search_depth = 2
        val ranked_moves = moves.map(x => (x, minimaxAB(ChessBoard.makeMove(fen, x), search_depth, Double.MinValue, Double.MaxValue)))
        find_min(ranked_moves)._1
    }


}
