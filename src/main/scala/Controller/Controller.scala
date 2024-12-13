package Controller

import Model.{ChessBoard, Event, LegalMoves, PseudoMoves, Remis, SetCommand, UndoInvoker}
import util.Observable
import Controller.ChessContext
import Controller.State

class Controller(var fen : String, var context : ChessContext, var output : String) extends Observable{
    val invoker : UndoInvoker = new UndoInvoker
    var activeSquare : Int = 0;
    var current_theme: Int = 0;
    //val stockfish = new Stockfish()

    def boardToString() : String = {ChessBoard.getBoardString(ChessBoard.fenToBoard(fen))}

    def createOutput() : String = {output}

    def play(move : (Int, Int)) : Unit = {
        val legalMoves = LegalMoves.getAllLegalMoves(fen);
        val event : Event = Event(legalMoves.isEmpty, fen, Remis.isRemis(fen, legalMoves))
        context.handle(event)
        context.state match {
            case State.remisState => output = "Remis"
            case State.whiteWonState => output = "Schwarz wurde vernichtend geschlagen"
            case State.blackWonState => output = "Weiß wurde vernichtend geschlagen"
            case _ =>
                if (!legalMoves.contains(move)) {
                    output = "Das kannste nicht machen Bro (kein legaler Zug)"
                } else {
                    invoker.doStep(new SetCommand(ChessBoard.makeMove(fen, move), fen , this))
                    if(ChessBoard.canPromote(fen) != -1) {
                        ringObservers
                    }
                    output = boardToString()
                }
        }
        notifyObservers
        if (!output.contains("Das")) {

        }

        //val bestMove = Stockfish.bestMove(fen, 15)
        //println(bestMove)
        //playEngineMove(ChessBoard.translateMoveStringToInt(fen, bestMove))
    }
    
    def promotePawn(pieceKind : String) : Unit = {
        fen = ChessBoard.promote(pieceKind, fen, ChessBoard.canPromote(fen));
    }

    def undo(): Unit = {
        invoker.undoStep
        output = boardToString()
        notifyObservers
    }

    def redo() : Unit = {
        invoker.redoStep
        output = boardToString()
        notifyObservers
    }

    def squareClicked(clickedSquare: Int) : Unit = {
        play(ChessBoard.translateCastle(ChessBoard.fenToBoard(fen), (activeSquare, clickedSquare)))
        activeSquare = clickedSquare
    }

    def nextTheme(): Unit = {
        current_theme = (current_theme + 1) % 19
        notifyObservers
    }
}

