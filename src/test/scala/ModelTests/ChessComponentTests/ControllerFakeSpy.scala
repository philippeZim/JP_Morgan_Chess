package ModelTests.ChessComponentTests

import Model.ChessComponent.RealChess.ChessFacade
import Model.UndoRedoComponent.UndoRedoTrait
import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.RealChessController.UndoInvoker
import util.Observable

class ControllerFakeSpy(var fen : String) extends Observable with ControllerTrait {
    var activeSquare : Int = -5;
    var invoker : UndoRedoTrait = new InvokerDummy
    var counter : Int = 0;

    def play(move : (Int, Int)) : Unit = {
        val legalMoves = ChessFacade.getAllLegalMoves(fen);
            if (!legalMoves.contains(move)) {
                counter += 1
            } else {
                fen = ChessFacade.makeMove(fen, move)
                if (ChessFacade.canPromote(fen) != -1) {
                    promotePawn("Q")
                }
            }
    }

    def promotePawn(pieceKind: String): Unit = {
        fen = ChessFacade.promote(pieceKind, fen, ChessFacade.canPromote(fen));
    }

    def squareClicked(clickedSquare: Int): Unit = {
        if (ChessFacade.isColorPiece(fen, clickedSquare)) {
            activeSquare = clickedSquare
        } else if (!ChessFacade.isColorPiece(fen, clickedSquare) && activeSquare != -5) {
            play(ChessFacade.translateCastle(ChessFacade.fenToBoard(fen), (activeSquare, clickedSquare)))
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
}
