package JP_Morgan_Chess.ControllerComponent.DuoChessController

import JP_Morgan_Chess.ChessComponent.ChessTrait
import JP_Morgan_Chess.ControllerComponent.ControllerTrait
import JP_Morgan_Chess.ControllerComponent.Extra.{ChessContext, Event, SetCommand, State, UndoInvoker}
import JP_Morgan_Chess.util.Observable

class Controller(using val gameMode : ChessTrait, override var fen : String, var context : ChessContext, var output : String) extends Observable with ControllerTrait {
    var activeSquare : Int = -5;
    var current_theme: Int = 0;
    
    def boardToString() : String = {gameMode.getBoardString(gameMode.fenToBoard(fen))}

    def createOutput() : String = {output}

    def play(move : (Int, Int)) : Unit = {
        val legalMoves = gameMode.getAllLegalMoves(fen);
        if (!legalMoves.contains(move)) {
                output = "Das kannste nicht machen Bro (kein legaler Zug)"
        } else {
            UndoInvoker.doStep(new SetCommand(gameMode.makeMove(fen, move), fen, this))
            if (gameMode.canPromote(fen) != -1) {
                ringObservers
            }
            output = boardToString()
        }
        checkGameState(legalMoves)
        notifyObservers
    }

    def checkGameState(legalMoves: List[(Int, Int)]): Boolean = {
        val event: Event = Event(legalMoves.isEmpty, fen, gameMode.isRemis(fen, legalMoves))
        context.handle(event)
        context.state match {
            case State.remisState => output += "\n \nRemis"
                false
            case State.whiteWonState => output += "\n \nSchwarz wurde vernichtend geschlagen"
                false
            case State.blackWonState => output += "\n \nWeiß wurde vernichtend geschlagen"
                false
            case _ => true
        }
    }
    
    def promotePawn(pieceKind : String) : Unit = {
        fen = gameMode.promote(pieceKind, fen, gameMode.canPromote(fen));
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
        if(gameMode.isColorPiece(fen, clickedSquare)) {
            activeSquare = clickedSquare
        } else if (!gameMode.isColorPiece(fen, clickedSquare) && activeSquare != -5) {
            play(gameMode.translateCastle(gameMode.fenToBoard(fen), (activeSquare, clickedSquare)))
            activeSquare = -5
        }
    }

    def nextTheme(): Unit = {
        current_theme = (current_theme + 1) % 19
        notifyObservers
    }
}

