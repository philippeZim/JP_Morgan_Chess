package Controller

import util.Observable
import Controller.ChessContext
import Controller.State
import Model.ChessComponent.ChessConcrete
import Model.UndoRedoComponent.UndoInvoker

class Controller(var fen : String, var context : ChessContext, var output : String) extends Observable{
    val invoker : UndoInvoker = new UndoInvoker
    var activeSquare : Int = -5;
    var current_theme: Int = 0;
    //val stockfish = new Stockfish()

    def boardToString() : String = {ChessConcrete.getBoardString(ChessConcrete.fenToBoard(fen))}

    def createOutput() : String = {output}

    def play(move : (Int, Int)) : Unit = {
        val legalMoves = ChessConcrete.getAllLegalMoves(fen);
        val event : Event = Event(legalMoves.isEmpty, fen, ChessConcrete.isRemis(fen, legalMoves))
        context.handle(event)
        context.state match {
            case State.remisState => output = "Remis"
            case State.whiteWonState => output = "Schwarz wurde vernichtend geschlagen"
            case State.blackWonState => output = "Weiß wurde vernichtend geschlagen"
            case _ =>
                if (!legalMoves.contains(move)) {
                    output = "Das kannste nicht machen Bro (kein legaler Zug)"
                } else {
                    invoker.doStep(invoker.newCommand(ChessConcrete.makeMove(fen, move), fen , this))
                    if(ChessConcrete.canPromote(fen) != -1) {
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
        fen = ChessConcrete.promote(pieceKind, fen, ChessConcrete.canPromote(fen));
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
        if(ChessConcrete.isColorPiece(fen, clickedSquare)) {
            activeSquare = clickedSquare
        } else if (!ChessConcrete.isColorPiece(fen, clickedSquare) && activeSquare != -5) {
            play(ChessConcrete.translateCastle(ChessConcrete.fenToBoard(fen), (activeSquare, clickedSquare)))
            activeSquare = -5
        }
    }

    def nextTheme(): Unit = {
        current_theme = (current_theme + 1) % 19
        notifyObservers
    }
}

