package ModelTests.ChessComponentTests

import Model.ChessComponent.BasicChessComponent.StandartChess.{Color, Piece, PieceType}
import Model.ChessComponent.DevourChess.{DevourChessFacade, Remis}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class DevourChessFacadeSpec extends AnyWordSpec {
    val testInstance = DevourChessFacade()
    "DevourChessFacade" should {
        "return default board" in {
            val p = Piece(PieceType.PAWN, Color.BLACK);
            val r = Piece(PieceType.ROOK, Color.BLACK);
            val n = Piece(PieceType.KNIGHT, Color.BLACK);
            val b = Piece(PieceType.BISHOP, Color.BLACK);
            val q = Piece(PieceType.QUEEN, Color.BLACK);
            val k = Piece(PieceType.KING, Color.BLACK);

            val P = Piece(PieceType.PAWN, Color.WHITE);
            val R = Piece(PieceType.ROOK, Color.WHITE);
            val N = Piece(PieceType.KNIGHT, Color.WHITE);
            val B = Piece(PieceType.BISHOP, Color.WHITE);
            val Q = Piece(PieceType.QUEEN, Color.WHITE);
            val K = Piece(PieceType.KING, Color.WHITE);
            val point = Piece(PieceType.EMPTY, Color.EMPTY);
            val board: Vector[Piece] = Vector(
                r, n, b, q, k, b, n, r,
                p, p, p, p, p, p, p, p,
                point, point, point, point, point, point, point, point,
                point, point, point, point, point, point, point, point,
                point, point, point, point, point, point, point, point,
                point, point, point, point, point, point, point, point,
                P, P, P, P, P, P, P, P,
                R, N, B, Q, K, B, N, R)

            testInstance.getDefaultBoard() should equal(board);
        }
        "return the correct board string" in {
            testInstance.getBoardString(getDefaultBoard()) should be((
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "8   |  r  |  n  |  b  |  q  |  k  |  b  |  n  |  r  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "7   |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "6   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "5   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "4   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "3   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "2   |  P  |  P  |  P  |  P  |  P  |  P  |  P  |  P  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "1   |  R  |  N  |  B  |  Q  |  K  |  B  |  N  |  R  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "       a     b     c     d     e     f     g     h     "));
        }

        "return a correct Vector[Piece] board given a FEN" in {
            val testFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
            testInstance.fenToBoard(testFen) should equal(testInstance.getDefaultBoard());
        }

       
        "return the correct new Fen String after making a move" in {
            val oldfen1 = "rnbqkb1r/ppp2ppp/3p1n2/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 4"
            val newfen1 = "rnbqkb1r/ppp2ppp/3p1n2/4p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 0 4"
            testInstance.makeMove(oldfen1, (-1, -1)) should be(newfen1)

            val oldfen2 = "rnbqk2r/ppp1bppp/3p1n2/4p3/2B1P3/5N2/PPPP1PPP/RNB1QRK1 b kq - 0 5"
            val newfen2 = "rnbq1rk1/ppp1bppp/3p1n2/4p3/2B1P3/5N2/PPPP1PPP/RNB1QRK1 w - - 0 6"
            testInstance.makeMove(oldfen2, (-3, -1)) should be(newfen2)

            val oldfen3 = "rn2kbnr/pbpqpppp/1p6/3p4/3P1B2/2NQ4/PPP1PPPP/R3KBNR w KQkq - 0 5"
            val newfen3 = "rn2kbnr/pbpqpppp/1p6/3p4/3P1B2/2NQ4/PPP1PPPP/2KR1BNR b kq - 0 5"
            testInstance.makeMove(oldfen3, (-2, -1)) should be(newfen3)

            val oldfen4 = "r3kbnr/pbpqpppp/1pn5/3p4/3P1B2/2NQ1N2/PPP1PPPP/2KR1B1R b kq - 0 6"
            val newfen4 = "2kr1bnr/pbpqpppp/1pn5/3p4/3P1B2/2NQ1N2/PPP1PPPP/2KR1B1R w - - 0 7"
            testInstance.makeMove(oldfen4, (-4, -1)) should be(newfen4)

           

            val oldfen13 = "rnbq2nr/ppppkppp/8/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQ - 0 4"
            val newfen13 = "rnbq2nr/ppppkppp/8/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 b - - 0 4"
            testInstance.makeMove(oldfen13, (-1,-1)) should be(newfen13)

            val oldfen14 = "rnbq1bnr/pp1kp1pp/2p2p2/3p4/3P1B2/2NQ4/PPP1PPPP/R3KBNR w KQ - 0 5"
            val newfen14 = "rnbq1bnr/pp1kp1pp/2p2p2/3p4/3P1B2/2NQ4/PPP1PPPP/2KR1BNR b - - 0 5"
            testInstance.makeMove(oldfen14, (-2, -1)) should be(newfen14)

            val oldfen15 = "rnbqk2r/pppp1ppp/5n2/2b1p3/4P3/3P1N2/PPP1QPPP/RNB1KB1R b KQkq - 0 4"
            val newfen15 = "rnbq1rk1/pppp1ppp/5n2/2b1p3/4P3/3P1N2/PPP1QPPP/RNB1KB1R w KQ - 0 5"
            testInstance.makeMove(oldfen15, (-3, -1)) should be(newfen15)

            val oldfen16 = "r3kbnr/ppp1pppp/2nq4/3p1b2/4P3/N1PB1Q2/PP1P1PPP/R1B1K1NR b KQkq - 0 5"
            val newfen16 = "2kr1bnr/ppp1pppp/2nq4/3p1b2/4P3/N1PB1Q2/PP1P1PPP/R1B1K1NR w KQ - 0 6"
            testInstance.makeMove(oldfen16, (-4, -1)) should be(newfen16)

        }

        "return the correct moves for castling" in {
            val move1 : (Int, Int) = (-1, -1)
            val move2 : (Int, Int) = (-2,-1)
            val move3 : (Int, Int) = (-3,-1)
            val move4 : (Int, Int) = (-4,-1)
            val move5 : (Int, Int) = (4,5)
            val move6 : (Int, Int) = (60,61)
            val move7 : (Int, Int) = (0,1)
            val board = testInstance.fenToBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
            testInstance.translateCastle(board, (60,62)) should be (move1)
            testInstance.translateCastle(board, (60,58)) should be (move2)
            testInstance.translateCastle(board, (4,6)) should be (move3)
            testInstance.translateCastle(board, (4,2)) should be (move4)
            testInstance.translateCastle(board, (4,5)) should be (move5)
            testInstance.translateCastle(board, (60, 61)) should be(move6)
            testInstance.translateCastle(board, (0,1)) should be (move7)
        }

        "detect a possible promotion" in {
            val fenWhitePromotion = "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5"
            testInstance.canPromote(fenWhitePromotion) should be (1)

            val fenBlackPromotion = "rQbqkbnr/1pppppp1/8/8/8/8/P1PPPP1P/RNBQKBNp w Qkq - 0 6"
            testInstance.canPromote(fenBlackPromotion) should be (63)
        }

        "promote correctly" in {
            val possiblePromotionFen = "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5"
            testInstance.promote("Q", "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5", 1) should be ("rQbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
            testInstance.promote("q", "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5", 1) should be ("rQbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
            testInstance.promote("R", "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5", 1) should be ("rRbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
            testInstance.promote("r", "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5", 1) should be ("rRbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
            testInstance.promote("N", "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5", 1) should be ("rNbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
            testInstance.promote("n", "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5", 1) should be ("rNbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
            testInstance.promote("B", "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5", 1) should be ("rBbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
            testInstance.promote("b", "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5", 1) should be ("rBbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
        }
    }
    def getBoardString(board: Vector[Piece]): String = {
        testInstance.getBoardString(board)
    }

    def fenToBoard(fen: String): Vector[Piece] = {
        testInstance.fenToBoard(fen)
    }

    def getAllLegalMoves(fen: String): List[(Int, Int)] = {
        testInstance.getAllLegalMoves(fen)
    }

    def makeMove(fen: String, move: (Int, Int)): String = {
        testInstance.makeMove(fen, move)
    }

    def canPromote(fen: String): Int = {
        testInstance.canPromote(fen)
    }

    def promote(pieceName: String, fen: String, position: Int): String = {
        testInstance.promote(pieceName, fen, position)
    }

    def isColorPiece(fen: String, position: Int): Boolean = {
        testInstance.isColorPiece(fen, position)
    }

    def translateCastle(board: Vector[Piece], move: (Int, Int)): (Int, Int) = {
        testInstance.translateCastle(board, move)
    }

    def isRemis(fen: String, legalMoves: List[(Int, Int)]): Boolean = {
        Remis.isRemis(fen)
    }

    def getBestMove(fen: String, depth: Int): String = {
        ""
    }

    def translateMoveStringToInt(fen: String, move: String): (Int, Int) = {
        testInstance.translateMoveStringToInt(fen, move)
    }

    def getDefaultFen(): String = {
        testInstance.getDefaultFen()
    }

    def getDefaultBoard(): Vector[Piece] = {
        testInstance.getDefaultBoard()
    }
    

}
