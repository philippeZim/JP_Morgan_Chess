package ModelTests.ChessComponentTests

import Model.ChessComponent.RealChess.RealChessFacade
import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.Extra.UndoInvoker
import util.Observable

class ControllerFakeSpy(var fen : String) extends Observable with ControllerTrait {
    var activeSquare : Int = -5;
    var counter : Int = 0;
    val chessRules = new RealChessFacade

    def play(move : (Int, Int)) : Unit = {
        val legalMoves = chessRules.getAllLegalMoves(fen);
            if (!legalMoves.contains(move)) {
                counter += 1
            } else {
                fen = chessRules.makeMove(fen, move)
                if (chessRules.canPromote(fen) != -1) {
                    promotePawn("Q")
                }
            }
    }

    def promotePawn(pieceKind: String): Unit = {
        fen = chessRules.promote(pieceKind, fen, chessRules.canPromote(fen));
    }

    def squareClicked(clickedSquare: Int): Unit = {
        if (chessRules.isColorPiece(fen, clickedSquare)) {
            activeSquare = clickedSquare
        } else if (!chessRules.isColorPiece(fen, clickedSquare) && activeSquare != -5) {
            play(chessRules.translateCastle(chessRules.fenToBoard(fen), (activeSquare, clickedSquare)))
            activeSquare = -5
        }
    }

    override def resetBoard(): Unit = ???
    def boardToString(): String = ???

    def createOutput(): String = ???

    def current_theme: Int = ???

    def current_theme_=(value: Int): Unit = ???

    def nextTheme(): Unit = ???

    def undo(): Unit = ???

    def redo(): Unit = ???

    def context: cController. ControllerComponent. Extra. ChessContext = ???
    
    def context_=(value: cController. ControllerComponent. Extra. ChessContext): Unit = ???
}
