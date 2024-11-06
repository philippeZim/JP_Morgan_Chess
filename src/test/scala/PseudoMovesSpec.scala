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
                ChessBoard.moveToIndex("f5", "f6"),
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
            val correctKnightMoves1: List[(Int, Int)] = List(
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
            val ourMoves1 = PseudoMoves.pseudoKnightMoves(List(), "r1bq2nr/pp1ppkpp/2n2p2/2p1N3/Nb6/8/PPP2PPP/R1BQ1K1R w KQkq - 0 1");
            ourMoves1 should contain allElementsOf (correctKnightMoves1);
            ourMoves1.length should be(correctKnightMoves1.length);

            val correctKnightMoves2: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("b5", "a3"),
                ChessBoard.moveToIndex("b5", "c7"),
                ChessBoard.moveToIndex("b5", "d6"),
                ChessBoard.moveToIndex("b5", "d4"),
                ChessBoard.moveToIndex("b5", "c3"),
                ChessBoard.moveToIndex("e5", "f7"),
                ChessBoard.moveToIndex("e5", "f3"),
                ChessBoard.moveToIndex("e5", "d3"),
                ChessBoard.moveToIndex("e5", "c4"),
                ChessBoard.moveToIndex("e5", "c6"),
                ChessBoard.moveToIndex("e5", "d7")
            )
            val ourMoves2 = PseudoMoves.pseudoKnightMoves(List(), "8/k1Q5/6p1/1n2n3/6r1/R2N4/8/7K b - - 0 1");
            ourMoves2 should contain allElementsOf (correctKnightMoves2);
            ourMoves2.length should be(correctKnightMoves2.length);
        }

        "should return the correct King Moves" in {
            val correctKingMoves: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("d2", "d3"),
                ChessBoard.moveToIndex("d2", "e3"),
                ChessBoard.moveToIndex("d2", "e1"),
                ChessBoard.moveToIndex("d2", "d1"),
                ChessBoard.moveToIndex("d2", "c1"),
                ChessBoard.moveToIndex("d2", "c2"))
            val ourMoves1 = PseudoMoves.pseudoKingMoves(List(), "4k3/3ppR2/8/8/8/2P5/3KN3/2n5 w - - 0 1");
            ourMoves1 should contain allElementsOf (correctKingMoves);
            ourMoves1.length should be(correctKingMoves.length);

            val correctKingMoves2: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("e8", "d8"),
                ChessBoard.moveToIndex("e8", "f7"),
                ChessBoard.moveToIndex("e8", "f8")
            )
            val ourMoves2 = PseudoMoves.pseudoKingMoves(List(), "4k3/3ppR2/8/8/8/2P5/3KN3/2n5 b - - 0 1");
            ourMoves2 should contain allElementsOf (correctKingMoves2);
            ourMoves2.length should be(correctKingMoves2.length);
        }

        "should detect castling moves" in {
            /*
            'K' -> (-1, -1),
            'Q' -> (-2, -1),
            'k' -> (-3, -1),
            'q' -> (-4, -1)
             */ val correctKingMoves: List[(Int, Int)] = List(ChessBoard.moveToIndex("e1", "f1"), ChessBoard.moveToIndex("e1", "d1"), ChessBoard.moveToIndex("e1", "d2"), (-1, -1), (-2, -1))

            val ourMoves1 = PseudoMoves.pseudoKingMoves(List(), "rnbqk2r/2p2ppp/1p1p4/p3p2n/2B1P3/NP1P1N2/PBP1QPPP/R3K2R w KQkq - 2 9");
            ourMoves1 should contain allElementsOf (correctKingMoves);
            ourMoves1.length should be(correctKingMoves.length);

            val correctKingMoves2: List[(Int, Int)] = List(ChessBoard.moveToIndex("e8", "f8"), ChessBoard.moveToIndex("e8", "d7"), ChessBoard.moveToIndex("e8", "e7"), (-3, -1))

            val ourMoves2 = PseudoMoves.pseudoKingMoves(List(), "rnbqk2r/2p2ppp/1p1p1n2/p3p3/2B1P3/NP1P1N2/PBP1QPPP/R3K2R b KQkq - 1 8");
            ourMoves2 should contain allElementsOf (correctKingMoves2);
            ourMoves2.length should be(correctKingMoves2.length);
        }
        /*
        "should return the correct Rook and Queen straight moves" in {
            
            val correctRookMoves1: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("h4", "h5"),
                ChessBoard.moveToIndex("h4", "h6"),
                ChessBoard.moveToIndex("h4", "h7"),
                ChessBoard.moveToIndex("h4", "g4"), 
                ChessBoard.moveToIndex("h4", "f4"),
                ChessBoard.moveToIndex("h4", "e4"),
                ChessBoard.moveToIndex("h4", "d4"),
                ChessBoard.moveToIndex("h4", "c4"),
                ChessBoard.moveToIndex("c5", "c4"),
                ChessBoard.moveToIndex("c5", "d5"),
                ChessBoard.moveToIndex("c5", "c6"),
                ChessBoard.moveToIndex("c5", "c7"),
                ChessBoard.moveToIndex("c5", "b5"),
                ChessBoard.moveToIndex("c5", "a5")
            )
            val ourMoves1 = PseudoMoves.pseudoRookAndQueenMoves2()Moves("rnbqk2r/ppppp1pp/5n2/2R1Bp2/1Pb4R/7P/P1PPP3/1N1QKBN1 w KQkq - 0 1")
            ourMoves1 should contain allElementsOf (correctRookMoves1);
            ourMoves1.length should be(correctRookMoves1.length);

            val correctRookMoves2: List[(Int, Int)] = List(
                ChessBoard.moveToIndex("b6", "a6"),
                ChessBoard.moveToIndex("b6", "c6"),
                ChessBoard.moveToIndex("b6", "d6"),
                ChessBoard.moveToIndex("b6", "e6"),
                ChessBoard.moveToIndex("b6", "b5"),
                ChessBoard.moveToIndex("b6", "b4"),
                ChessBoard.moveToIndex("f3", "g3"),
                ChessBoard.moveToIndex("f3", "h3"),
                ChessBoard.moveToIndex("f3", "f2"),
                ChessBoard.moveToIndex("f3", "f1"),
                ChessBoard.moveToIndex("f3", "f4"),
                ChessBoard.moveToIndex("f3", "e3")
            )

            val ourMoves2 = PseudoMoves.pseudoRookMoves("1nbqk3/ppppp1pp/1r3n2/2R1Bp2/1P5R/N2b1r1P/P1PPP1B1/3QK1N1 b KQkq - 0 1")
            ourMoves2 should contain allElementsOf (correctRookMoves2);
            ourMoves2.length should be(correctRookMoves2.length);
        }
        
        ""
        
         */
    }
    
         
}

