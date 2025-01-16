package ModelTests.ChessComponentTests

import Model.ChessComponent.RealChess.RealChessFacade
import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.RealChessController.{ChessContext, UndoInvoker}
import util.Observable

class ControllerFakeSpy(var fen : String) extends Observable with ControllerTrait {
    var activeSquare : Int = -5;
    var invoker = new InvokerDummy
    var counter : Int = 0;
    val chessFacade = new RealChessFacade()

    def play(move : (Int, Int)) : Unit = {
        
        val legalMoves = chessFacade.getAllLegalMoves(fen);
            if (!legalMoves.contains(move)) {
                counter += 1
            } else {
                fen = chessFacade.makeMove(fen, move)
                if (chessFacade.canPromote(fen) != -1) {
                    promotePawn("Q")
                }
            }
    }

    def promotePawn(pieceKind: String): Unit = {
        fen = chessFacade.promote(pieceKind, fen, chessFacade.canPromote(fen));
    }

    def squareClicked(clickedSquare: Int): Unit = {
        if (chessFacade.isColorPiece(fen, clickedSquare)) {
            activeSquare = clickedSquare
        } else if (!chessFacade.isColorPiece(fen, clickedSquare) && activeSquare != -5) {
            play(chessFacade.translateCastle(chessFacade.fenToBoard(fen), (activeSquare, clickedSquare)))
            activeSquare = -5
        }
    }
    def boardToString(): String = ???

    def createOutput(): String = ???

    def current_theme: Int = ???

    def current_theme_=(value: Int): Unit = ???

    def nextTheme(): Unit = ???

    def undo(): Unit = ???

    def redo(): Unit = ???

    def context: ChessContext = ???

    def context_=(value: ChessContext): Unit = ???
}
