package ModelTests.ChessComponentTests

import Model.ChessComponent.ChessBoard.*
import Model.ChessComponent.PseudoMoves.*
import Model.ChessComponent.{ChessBoard, PseudoMoves, Remis, LegalMoves}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class RemisSpec extends AnyWordSpec {
    "Remis should detect insufficent material correctly" in {
        //Remis.isMaterial(" ") should be (false)
        Remis.isMaterial("8/1p3k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("8/1P3k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("1R6/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("1Q6/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("1q6/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("1NR5/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)

        Remis.isMaterial("1N2n3/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("4n3/2N2k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("1N6/3b1k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("1n6/2B2k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)

        Remis.isMaterial("1B6/3B1k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("1b6/3b1k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("1b6/3B1k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (false)
        Remis.isMaterial("2B5/2b2k2/8/8/8/8/4K3/8 w KQkq - 0 1") should be (false)

        Remis.isMaterial("2n5/2b2k2/8/8/8/8/4K3/8 w KQkq - 0 1") should be (false)
        Remis.isMaterial("2N5/1B3k2/8/8/8/8/4K3/8 w KQkq - 0 1") should be (false)

        Remis.isMaterial("1N6/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (true)
        Remis.isMaterial("1NN5/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (true)
        Remis.isMaterial("1B6/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (true)

        Remis.isMaterial("1b6/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (true)
        Remis.isMaterial("1n6/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (true)
        Remis.isMaterial("1n1n4/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (true)

        Remis.isMaterial("8/5k2/8/8/8/8/4K3/8 b KQkq - 0 1") should be (true)

    }

    "realise a Patt correctly" in {
        Remis.isPatt("k7/8/1Q6/8/8/8/8/4K3 b - - 0 1", LegalMoves.getAllLegalMoves("k7/8/1Q6/8/8/8/8/4K3 b - - 0 1")) should be (true)
    }
}
