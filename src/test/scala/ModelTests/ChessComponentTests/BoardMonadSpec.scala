package ModelTests.ChessComponentTests

import JP_Morgan_Chess.ChessComponent.Model.DevourChess.BasicChessComponent.StandartChess.{BoardMonad, ChessBoard, Color, Piece, PieceType}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class BoardMonadSpec extends AnyWordSpec {
    "BoardMonad" should {
        "use filter and flatMap correctly" in {
            val board = new BoardMonad(ChessBoard.getDefaultBoard())
            val test1 = board.filter { case Some(e) => e.toString() == "p"
            case None => false
            }
            test1 should be(Vector(Some(Piece(PieceType.PAWN, Color.BLACK)), Some(Piece(PieceType.PAWN, Color.BLACK)), Some(Piece(PieceType.PAWN, Color.BLACK)), Some(Piece(PieceType.PAWN, Color.BLACK)), Some(Piece(PieceType.PAWN, Color.BLACK)), Some(Piece(PieceType.PAWN, Color.BLACK)), Some(Piece(PieceType.PAWN, Color.BLACK)), Some(Piece(PieceType.PAWN, Color.BLACK))))
        }

        "use flatMap Correctly" in {
            val board = new BoardMonad(ChessBoard.getDefaultBoard())
            val test1 = board.filter { case Some(e) => e.toString() == "p"
            case None => false
            }
            val test = test1.flatMap[String] { case Some(piece) => Vector(piece.toString(), "a")
            case None => Vector.empty
            }

            test should be(Vector("p", "a", "p", "a", "p", "a", "p", "a", "p", "a", "p", "a", "p", "a", "p", "a"))
        }
    }
}
