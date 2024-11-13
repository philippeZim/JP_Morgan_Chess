package Controller

import Model.{ChessBoard, Color, LegalMoves, PseudoMoves}
import util.Observable

class Controller(var fen : String) extends Observable{

    def boardToString() : String = {ChessBoard.getBoardString(ChessBoard.fenToBoard(fen))}

    def play(input : String) : Unit = {
        val move = ChessBoard.translateCastle(ChessBoard.fenToBoard(fen), ChessBoard.moveToIndex(input.substring(0, 2), input.substring(2, 4)));
        val legalMoves = LegalMoves.getAllLegalMoves(fen);
        if(legalMoves.isEmpty) {
            fen.split(" ")(1) match {
                case "w" => println("Spiel ist vorbei. Schwarz hat gewonnen")
                case "b" => println("Spiel ist vorbei. Weiß hat gewonnen")
            }
        }
        else if (!legalMoves.contains(move)) {
            println("Das kannste nicht machen Bro (kein legaler Zug)")
        } else{
            fen = ChessBoard.makeMove(fen, move)
            notifyObservers
        }
    }
}
