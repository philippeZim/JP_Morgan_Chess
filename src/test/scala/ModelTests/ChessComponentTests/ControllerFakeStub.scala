package ModelTests.ChessComponentTests

import Model.ChessComponent.ChessConcrete
import Model.UndoRedoComponent.UndoInvoker
import util.Observable

class ControllerFakeStub(var fen : String) extends Observable {
    var activeSquare : Int = -5;

    def play(move : (Int, Int)) : Unit = {
        val legalMoves = ChessConcrete.getAllLegalMoves(fen);
            if (!legalMoves.contains(move)) {
                //hier nicht relevant
            } else {
                fen = ChessConcrete.makeMove(fen, move)
                if (ChessConcrete.canPromote(fen) != -1) {
                    promotePawn("Q")
                }
            }
    }

    def promotePawn(pieceKind: String): Unit = {
        fen = ChessConcrete.promote(pieceKind, fen, ChessConcrete.canPromote(fen));
    }

    def squareClicked(clickedSquare: Int): Unit = {
        if (ChessConcrete.isColorPiece(fen, clickedSquare)) {
            activeSquare = clickedSquare
        } else if (!ChessConcrete.isColorPiece(fen, clickedSquare) && activeSquare != -5) {
            play(ChessConcrete.translateCastle(ChessConcrete.fenToBoard(fen), (activeSquare, clickedSquare)))
            activeSquare = -5
        }
    }
}
