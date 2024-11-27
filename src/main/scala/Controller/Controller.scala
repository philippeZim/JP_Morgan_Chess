package Controller

import Model.{ChessBoard, Event, LegalMoves, PseudoMoves, Remis}
import util.Observable
import Controller.ChessContext
import Controller.State

class Controller(var fen : String, var context : ChessContext, var output : String) extends Observable{

    def boardToString() : String = {ChessBoard.getBoardString(ChessBoard.fenToBoard(fen))}

    def createOutput() : String = {output}

    def play(input : String) : Unit = {
        val move = ChessBoard.translateCastle(ChessBoard.fenToBoard(fen), ChessBoard.moveToIndex(input.substring(0, 2), input.substring(2, 4)));
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
                    fen = ChessBoard.makeMove(fen, move)
                    output = boardToString()
                }
        }
        /*if(legalMoves.isEmpty) {
            fen.split(" ")(1) match {
                case "w" => println("Spiel ist vorbei. Schwarz hat gewonnen")
                case "b" => println("Spiel ist vorbei. Weiß hat gewonnen")
            }
        }*/
        /*if (!legalMoves.contains(move)) {
            println("Das kannste nicht machen Bro (kein legaler Zug)")
        } else{ */
        //fen = ChessBoard.makeMove(fen, move)
        notifyObservers
    }
}

