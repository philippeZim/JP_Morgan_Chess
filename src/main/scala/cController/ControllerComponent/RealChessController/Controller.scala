package cController.ControllerComponent.RealChessController

import Model.ChessComponent.DevourChess.ChessFacade
import cController.ControllerComponent.ControllerTrait
import util.Observable

class Controller(override var fen : String, var context : ChessContext, var output : String) extends Observable with ControllerTrait {
    var activeSquare : Int = -5;
    var current_theme: Int = 0;
    
    def boardToString() : String = {ChessFacade.getBoardString(ChessFacade.fenToBoard(fen))}

    def createOutput() : String = {output}

    def play(move : (Int, Int)) : Unit = {
        val legalMoves = ChessFacade.getAllLegalMoves(fen);
        val event: Event = Event(legalMoves.isEmpty, fen, ChessFacade.isRemis(fen, legalMoves))
        context.handle(event)
        context.state match {
            case State.remisState => output = "Remis"
            case State.whiteWonState => output = "Schwarz wurde vernichtend geschlagen"
            case State.blackWonState => output = "Weiß wurde vernichtend geschlagen"
            case _ => if (!legalMoves.contains(move)) {
                output = "Das kannste nicht machen Bro (kein legaler Zug)"
            } else {
                UndoInvoker.doStep(new SetCommand(ChessFacade.makeMove(fen, move), fen, this))
                if (ChessFacade.canPromote(fen) != -1) {
                    ringObservers
                }
                output = boardToString()
            }
        }
        notifyObservers
    }
    
    def promotePawn(pieceKind : String) : Unit = {
        fen = ChessFacade.promote(pieceKind, fen, ChessFacade.canPromote(fen));
    }

    def undo(): Unit = {
        UndoInvoker.undoStep()
        output = boardToString()
        notifyObservers
    }

    def redo() : Unit = {
        UndoInvoker.redoStep()
        output = boardToString()
        notifyObservers
    }

    def squareClicked(clickedSquare: Int) : Unit = {
        if(ChessFacade.isColorPiece(fen, clickedSquare)) {
            activeSquare = clickedSquare
        } else if (!ChessFacade.isColorPiece(fen, clickedSquare) && activeSquare != -5) {
            play(ChessFacade.translateCastle(ChessFacade.fenToBoard(fen), (activeSquare, clickedSquare)))
            activeSquare = -5
        }
    }

    def nextTheme(): Unit = {
        current_theme = (current_theme + 1) % 19
        notifyObservers
    }
}

