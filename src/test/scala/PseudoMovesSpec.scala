import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import PseudoMoves._
import ChessBoard._

class PseudoMovesSpec extends AnyWordSpec {

    "PseudoMoves" should {
        "pseudo pawn moves for white" in {
            val correctMoves1: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a2", "a3"),
                ChessBoard.moveToIndex("a2", "a4"),
                ChessBoard.moveToIndex("b2", "b3"),
                ChessBoard.moveToIndex("b2", "b4"),
                ChessBoard.moveToIndex("c2", "c3"),
                ChessBoard.moveToIndex("c2", "c4"),
                ChessBoard.moveToIndex("d2", "d3"),
                ChessBoard.moveToIndex("d2", "d4"),
                ChessBoard.moveToIndex("e2", "e3"),
                ChessBoard.moveToIndex("e2", "e4"),
                ChessBoard.moveToIndex("f2", "f3"),
                ChessBoard.moveToIndex("f2", "f4"),
                ChessBoard.moveToIndex("g2", "g3"),
                ChessBoard.moveToIndex("g2", "g4"),
                ChessBoard.moveToIndex("h2", "h3"),
                ChessBoard.moveToIndex("h2", "h4"));
            PseudoMoves.pseudoPawnMoves("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1") should contain allElementsOf (correctMoves1);
            //PseudoMoves.pseudoPawnMoves("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1").length should be correctMoves1.length;


            val correctMoves2: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a2", "a3"),
                ChessBoard.moveToIndex("a2", "a4"),
                ChessBoard.moveToIndex("c3", "c4"),
                ChessBoard.moveToIndex("d2", "d3"),
                ChessBoard.moveToIndex("d2", "d4"),
                ChessBoard.moveToIndex("e4", "d5"),
                ChessBoard.moveToIndex("g2", "g3"),
                ChessBoard.moveToIndex("g2", "g4"),
                ChessBoard.moveToIndex("h2", "h3"),
                ChessBoard.moveToIndex("h2", "h4")
            );
            PseudoMoves.pseudoPawnMoves(
                "r1bqkbnr/2p2ppp/p1n5/1p1pp3/4P3/1BP2N2/PP1P1PPP/RNBQK2R w KQkq - 0 1"
            ) should contain allElementsOf (correctMoves2);

            val correctMoves3: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a3", "a4"),
                ChessBoard.moveToIndex("b2", "b3"),
                ChessBoard.moveToIndex("b2", "c3"),
                ChessBoard.moveToIndex("b2", "b4"),
                ChessBoard.moveToIndex("d2", "d3"),
                ChessBoard.moveToIndex("d2", "d4"),
                ChessBoard.moveToIndex("d2", "c3"),
                ChessBoard.moveToIndex("g2", "g3"),
                ChessBoard.moveToIndex("g2", "g4"),
                ChessBoard.moveToIndex("h2", "h3"),
                ChessBoard.moveToIndex("h2", "h4")
            );
            PseudoMoves.pseudoPawnMoves(
                "r1bqk1nr/pppp1ppp/2n5/4p3/4P3/P1b2N2/1PPP1PPP/R1BQKB1R w KQkq - 0 1"
            ) should contain allElementsOf (correctMoves3);
        }

        "pseudo pawn moves for black" in {
            val correctBlackPawnMoves1: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a7", "a6"),
                ChessBoard.moveToIndex("a7", "a5"),
                ChessBoard.moveToIndex("b7", "b6"),
                ChessBoard.moveToIndex("b7", "b5"),
                ChessBoard.moveToIndex("c7", "c6"),
                ChessBoard.moveToIndex("c7", "c5"),
                ChessBoard.moveToIndex("d7", "d6"),
                ChessBoard.moveToIndex("d7", "d5"),
                ChessBoard.moveToIndex("e7", "e6"),
                ChessBoard.moveToIndex("e7", "e5"),
                ChessBoard.moveToIndex("f7", "f6"),
                ChessBoard.moveToIndex("f7", "f5"),
                ChessBoard.moveToIndex("g7", "g6"),
                ChessBoard.moveToIndex("g7", "g5"),
                ChessBoard.moveToIndex("h7", "h6"),
                ChessBoard.moveToIndex("h7", "h5")
            );

            PseudoMoves.pseudoPawnMoves(
                "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"
            ) should contain allElementsOf (correctBlackPawnMoves1);

            val correctBlackPawnMoves2: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a7", "a6"),
                ChessBoard.moveToIndex("a7", "a5"),
                ChessBoard.moveToIndex("b7", "b6"),
                ChessBoard.moveToIndex("b7", "b5"),
                ChessBoard.moveToIndex("c6", "c5"),
                ChessBoard.moveToIndex("d5", "d4"),
                ChessBoard.moveToIndex("d5", "e4"),
                ChessBoard.moveToIndex("g7", "g6"),
                ChessBoard.moveToIndex("h6", "g5"),
                ChessBoard.moveToIndex("h6", "h5")
            );

            PseudoMoves.pseudoPawnMoves(
                "rnbq1rk1/pp3pp1/2p2n1p/3pp1B1/1b2P3/3P1NP1/PPPNQP1P/2KR1B1R b KQkq - 0 1"
            ) should contain allElementsOf (correctBlackPawnMoves2);

            val correctBlackPawnMoves3: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a7", "a6"),
                ChessBoard.moveToIndex("a7", "a5"),
                ChessBoard.moveToIndex("c4", "d3"),
                ChessBoard.moveToIndex("e5", "d4"),
                ChessBoard.moveToIndex("e5", "e4"),
                ChessBoard.moveToIndex("e5", "f4"),
                ChessBoard.moveToIndex("g7", "g6"),
                ChessBoard.moveToIndex("g7", "g5"),
                ChessBoard.moveToIndex("h7", "h6"),
                ChessBoard.moveToIndex("h7", "h5")
            )

            PseudoMoves.pseudoPawnMoves(
                "rnbqkb1r/p4ppp/5n2/1p1pp3/1PpP1P2/2NQBN2/P1P1P1PP/2KR1B1R b KQkq - 0 1"
            ) should contain allElementsOf (correctBlackPawnMoves3);
        }

        "pseudo pawn moves for en passant with black and white" in {
            val correctBlackPawnMoves: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("e5", "e6"),
                ChessBoard.moveToIndex("e5", "f6")
            )
            PseudoMoves.pseudoPawnMoves(
                "8/8/8/4Pp2/8/8/8/8 w - f6 0 1"
            ) should contain allElementsOf (correctBlackPawnMoves);

            val correctMoves1: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a2", "a3"),
                ChessBoard.moveToIndex("a2", "a4"),
                ChessBoard.moveToIndex("b5", "b6"),
                ChessBoard.moveToIndex("b5", "c6"),
                ChessBoard.moveToIndex("d2", "d3"),
                ChessBoard.moveToIndex("d2", "d4"),
                ChessBoard.moveToIndex("f3", "f4"),
                ChessBoard.moveToIndex("g2", "g3"),
                ChessBoard.moveToIndex("g2", "g4"),
                ChessBoard.moveToIndex("h2", "h3"),
                ChessBoard.moveToIndex("h2", "h4")
            );
            PseudoMoves.pseudoPawnMoves(
                "rnbqkb1r/pp1p1ppp/3n4/1Pp1p3/8/2N2P2/P1PP2PP/R1BQKBNR w KQkq c6 0 1"
            ) should contain allElementsOf (correctMoves1);

            val correctMoves2: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a2", "a3"),
                ChessBoard.moveToIndex("a2", "a4"),
                ChessBoard.moveToIndex("b5", "b6"),
                ChessBoard.moveToIndex("b5", "a6"),
                ChessBoard.moveToIndex("c2", "c3"),
                ChessBoard.moveToIndex("c2", "c4"),
                ChessBoard.moveToIndex("f2", "f3"),
                ChessBoard.moveToIndex("f2", "f4"),
                ChessBoard.moveToIndex("g2", "g3"),
                ChessBoard.moveToIndex("g2", "g4"),
                ChessBoard.moveToIndex("h2", "h3"),
                ChessBoard.moveToIndex("h2", "h4")
            );
            PseudoMoves.pseudoPawnMoves(
                "rnbqk2r/1p1p1ppp/3b1n2/pPpPp3/4P3/8/P1P2PPP/RNBQKBNR w KQkq a6 0 1"
            ) should contain allElementsOf (correctMoves2);

            val correctMoves3: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a7", "a6"),
                ChessBoard.moveToIndex("a7", "a5"),
                ChessBoard.moveToIndex("b7", "b6"),
                ChessBoard.moveToIndex("b7", "b5"),
                ChessBoard.moveToIndex("c5", "c4"),
                ChessBoard.moveToIndex("c5", "d4"),
                ChessBoard.moveToIndex("f4", "f3"),
                ChessBoard.moveToIndex("f4", "g3"),
                ChessBoard.moveToIndex("g7", "g6"),
                ChessBoard.moveToIndex("g7", "g5"),
                ChessBoard.moveToIndex("h7", "h6"),
                ChessBoard.moveToIndex("h7", "h5")
            );
            PseudoMoves.pseudoPawnMoves(
                "rnbqkbnr/pp1p2pp/8/2p5/3NPpP1/2N5/PPP2P1P/R1BQKB1R b KQkq g3 0 1"
            ) should contain allElementsOf (correctMoves3);

            val correctEnPassantMoves4: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("a5", "a4"),
                ChessBoard.moveToIndex("a5", "b4"),
                ChessBoard.moveToIndex("b7", "b6"),
                ChessBoard.moveToIndex("b7", "b5"),
                ChessBoard.moveToIndex("c4", "b3"),
                ChessBoard.moveToIndex("d7", "d6"),
                ChessBoard.moveToIndex("d7", "d5"),
                ChessBoard.moveToIndex("e5", "d4"),
                ChessBoard.moveToIndex("e5", "e4"),
                ChessBoard.moveToIndex("f5", "f4"),
                ChessBoard.moveToIndex("g5", "g4"),
                ChessBoard.moveToIndex("g5", "h4"),
                ChessBoard.moveToIndex("h7", "h6"),
                ChessBoard.moveToIndex("h7", "h5")
            );

            PseudoMoves.pseudoPawnMoves(
                "rnbqkbnr/1p1p3p/8/p3ppp1/1PpP3P/2N2NP1/P1P1PP2/R1BQKB1R b KQkq b3 0 1"
            ) should contain allElementsOf (correctEnPassantMoves4);


        }

        "should return the correct Knight Moves" in {

            //print(PseudoMoves.pseudoKnightMoves("8/8/1P6/2P5/N7/2P2p2/8/8 w - - 0 1"))
            val correctKnightMoves1 : List [(Int, Int)] = List(
                ChessBoard.moveToIndex("a4", "b6"),
                ChessBoard.moveToIndex("a4", "c5"),
                ChessBoard.moveToIndex("a4", "c3"),
                ChessBoard.moveToIndex("e5", "d7"),
                ChessBoard.moveToIndex("e5", "f7"),
                ChessBoard.moveToIndex("e5", "g6"),
                ChessBoard.moveToIndex("e5", "g4"),
                ChessBoard.moveToIndex("e5", "f3"),
                ChessBoard.moveToIndex("e5", "d3"),
                ChessBoard.moveToIndex("e5", "c4"),
                ChessBoard.moveToIndex("e5", "c6")
            )
            PseudoMoves.pseudoKnightMoves("r1bq2nr/pp1ppkpp/2n2p2/2p1N3/Nb6/8/PPP2PPP/R1BQ1K1R w KQkq - 0 1") should contain allElementsOf(correctKnightMoves1)

            val correctKnightMoves2: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("b5", "a3"),
                ChessBoard.moveToIndex("b5", "c7"),
                ChessBoard.moveToIndex("b5", "d6"),
                ChessBoard.moveToIndex("b5", "d4"),
                ChessBoard.moveToIndex("b5", "c3"),
                ChessBoard.moveToIndex("e5", "f7"),
                ChessBoard.moveToIndex("e5", "g6"),
                ChessBoard.moveToIndex("e5", "g4"),
                ChessBoard.moveToIndex("e5", "f3"),
                ChessBoard.moveToIndex("e5", "d3"),
                ChessBoard.moveToIndex("e5", "c4"),
                ChessBoard.moveToIndex("e5", "c6"),
                ChessBoard.moveToIndex("e5", "d7")
            )
            PseudoMoves.pseudoKnightMoves("8/k1Q5/6p1/1n2n3/6r1/R2N4/8/7K b - - 0 1") should contain allElementsOf (correctKnightMoves2)
        }
    }
}

/*TODO: Listenlaenge vergleichen, damit die Listen genau gleich sind*/

