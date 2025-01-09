package cController.ControllerComponent

import util.{Observable, Observer}

trait ControllerTrait extends Observable {
    def fen: String

    def fen_=(value: String): Unit

    def current_theme: Int

    def current_theme_=(value: Int): Unit

    def play(move : (Int, Int)) : Unit
    
    def undo() : Unit

    def redo() : Unit

    def createOutput() : String

    def promotePawn(pieceKind : String) : Unit

    def squareClicked(clickedSquare: Int) : Unit

    def nextTheme(): Unit

    def add(s: Observer): Unit
}


