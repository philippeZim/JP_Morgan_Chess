package Model

import scala.annotation.tailrec
import scala.util.control.Breaks._

class Engine {


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


    def minimax(fen: String, depth: Int): Double = {
        val moves = LegalMoves.getAllLegalMoves(fen);

        if (game_over(fen, moves) || depth == 0) {
            return evaluate(fen)
        }

        val fenSplit = fen.split(" ")
        var best = 0.0
        var lFen = fen
        fenSplit(1) match {
            case "w" => {
                best = -10000.0
                for (move <- moves) {
                    lFen = ChessBoard.makeMove(lFen, move)
                    val tempVal = minimax(lFen, depth - 1)
                    if (tempVal < best) {
                        best = tempVal
                    }
                }

            }
            case "b" => {
                best = 10000.0
                for (move <- moves) {
                    lFen = ChessBoard.makeMove(lFen, move)
                    val tempVal = minimax(lFen, depth - 1)
                    if (tempVal < best) {
                        best = tempVal
                    }
                }
            }
        }
        best
    }

    def minimaxFunctional(fen: String, depth: Int): Double = {
        val moves = LegalMoves.getAllLegalMoves(fen)

        if (game_over(fen, moves) || depth == 0) {
            return evaluate(fen)
        }

        val fenSplit = fen.split(" ")
        val playerTurn = fenSplit(1)
        val initialBest = if (playerTurn == "w") -10000.0 else 10000.0

        val newBest = moves.foldLeft(initialBest) { (best, move) =>
            val newFen = ChessBoard.makeMove(fen, move)
            val tempVal = minimaxFunctional(newFen, depth - 1)

            playerTurn match {
                case "w" => Math.max(best, tempVal)
                case "b" => Math.min(best, tempVal)
            }
        }

        newBest
    }


    // called with alpha = -Double.MaxValue and beta = Double.MaxValue
    def alphabeta(fen: String, alpha: Double, beta: Double, depth: Int): Double = {
        val moves = LegalMoves.getAllLegalMoves(fen);

        if (game_over(fen, moves) || depth == 0) {
            return evaluate(fen)
        }

        val fenSplit = fen.split(" ")
        var best = 0.0
        var lFen = fen
        var currentAlpha = alpha
        var currentBeta = beta

        fenSplit(1) match {
            case "w" => {
                best = -10000.0
                breakable {
                    for (move <- moves) {
                        lFen = ChessBoard.makeMove(lFen, move)
                        val tempVal = alphabeta(lFen, currentAlpha, currentBeta, depth - 1)
                        best = Math.max(best, tempVal)
                        currentAlpha = Math.max(currentAlpha, best)
                        if (currentBeta <= currentAlpha) {
                            break
                        }
                    }
                }
            }
            case "b" => {
                best = 10000.0
                breakable {
                    for (move <- moves) {
                        lFen = ChessBoard.makeMove(lFen, move)
                        val tempVal = alphabeta(lFen, currentAlpha, currentBeta, depth - 1)
                        best = Math.min(best, tempVal)
                        currentBeta = Math.min(currentBeta, best)
                        if (currentBeta <= currentAlpha) {
                            break
                        }
                    }
                }
            }
        }
        best
    }

    def alphabetaFunctional(fen: String, depth: Int, alpha: Double, beta: Double): Double = {
        val moves = LegalMoves.getAllLegalMoves(fen)

        // Base case: either game over or we've reached the maximum depth
        if (game_over(fen, moves) || depth == 0) {
            return evaluate(fen)
        }

        val fenSplit = fen.split(" ")
        val playerTurn = fenSplit(1)
        var best = if (playerTurn == "w") -10000.0 else 10000.0

        playerTurn match {
            case "w" => // White's turn (maximize the score)
                moves.foldLeft(alpha) { (currentAlpha, move) =>
                    val newFen = ChessBoard.makeMove(fen, move)
                    val tempVal = alphabetaFunctional(newFen, depth - 1, currentAlpha, beta)

                    // Update the best score and prune if necessary
                    best = Math.max(best, tempVal)
                    if (best >= beta) return best // Beta cut-off

                    // Update alpha for pruning
                    Math.max(currentAlpha, best)
                }
            case "b" => // Black's turn (minimize the score)
                moves.foldLeft(beta) { (currentBeta, move) =>
                    val newFen = ChessBoard.makeMove(fen, move)
                    val tempVal = alphabetaFunctional(newFen, depth - 1, alpha, currentBeta)

                    // Update the best score and prune if necessary
                    best = Math.min(best, tempVal)
                    if (best <= alpha) return best // Alpha cut-off

                    // Update beta for pruning
                    Math.min(currentBeta, best)
                }
        }

        best
    }


}
