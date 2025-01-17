package ModelTests.ChessComponentTests

import Model.ChessComponent.DevourChess.DevourChessFacade
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

/*
def getAllLegalMoves (fen : String) : List[(Int,Int)]= {
        @tailrec
        def filterLegalWithoutTake(accumulator: List[(Int, Int)], pseudoMoves: List[(Int, Int)]): List[(Int, Int)] = {
            pseudoMoves match {
                case Nil => accumulator;
                case h :: t => {
                    if (isTakingMove(fen, h._2)) {
                        filterLegalWithTake(List(h), t);
                    } else {
                        filterLegalWithoutTake(h :: accumulator, t);
                    }
                }
            }
        }

        @tailrec
        def filterLegalWithTake(accumulator: List[(Int, Int)], pseudoMoves: List[(Int, Int)]): List[(Int, Int)] = {
            pseudoMoves match {
                case Nil => accumulator;
                case h :: t => {
                    if (isTakingMove(fen, h._2)) {
                        filterLegalWithTake(h :: accumulator, t);
                    } else {
                        filterLegalWithTake(accumulator, t);
                    }
                }
            }
        }
        filterLegalWithoutTake(List(), BasicChessFacade.getAllPseudoLegalMoves(fen));
    }

    def isTakingMove(fen : String, attackedPosition : Int) : Boolean = {
        BasicChessFacade.isDifferentColorPiece(fen, attackedPosition)
    }
*/

class DevourChessSpec extends AnyWordSpec {
    val testObject = DevourChessFacade()
    "DevourChess" should {
        "getAllLegalMoves Test" in {
            val fen = "rnbqkbnr/ppppppp1/8/7p/4P3/8/PPPP1PPP/RNBQKBNR w KQkq h6 0 1"


            testObject.getAllLegalMoves(fen) should be(List((59, 31)))
        }
        "isColorPiece Test" in {
            val fen = "rnbqkbnr/ppppppp1/8/7p/4P3/8/PPPP1PPP/RNBQKBNR w KQkq h6 0 1"
            testObject.isColorPiece(fen, 59) should be(true)
            testObject.isColorPiece(fen, 0) should be(false)
            testObject.isColorPiece(fen, 31) should be(false)
        }

        "isRemis Test" in {
            val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            testObject.isRemis(fen, testObject.getAllLegalMoves(fen)) should be (false)

            val fen2 = "8/8/8/8/6B1/3b4/8/8 w - - 0 1"
            testObject.isRemis(fen2, testObject.getAllLegalMoves(fen2)) should be(false)

            val fen3 = "8/8/8/6B1/3b4/8/8/8 w - - 0 1"
            testObject.isRemis(fen3, testObject.getAllLegalMoves(fen3)) should be(false)

            val fen4 = "8/8/8/6B1/8/3b4/8/8 w - - 0 1"
            testObject.isRemis(fen4, testObject.getAllLegalMoves(fen4)) should be(true)

            val fen5 = "8/8/8/5B2/8/4b3/8/8 b - - 0 1"
            testObject.isRemis(fen5, testObject.getAllLegalMoves(fen5)) should be(true)
        }

        "getBestMove Test" in {
            testObject.getBestMove("", 0) should be("")
        }

        "translateMoveStringToInt Test" in {
            testObject.translateMoveStringToInt("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "e2e4") should be (52, 36)
        }

        "getDefaultFen Test" in {
            testObject.getDefaultFen() should be ("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")
        }
    }
}
