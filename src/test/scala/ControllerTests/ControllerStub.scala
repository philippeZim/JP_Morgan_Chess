package ControllerTests

import cController.ControllerComponent.ControllerTrait
import _root_.cController.ControllerComponent.Extra.{Command, SetCommand, UndoInvoker}

class ControllerStub(override var fen : String) extends ControllerTrait {
    val invoker = UndoInvoker
    override var current_theme: Int = 0

    def play(move: (Int, Int)): Unit = {invoker.doStep(new SetCommand(move.productIterator.mkString("-"), fen, this))}

    def undo() : Unit = {invoker.undoStep()}

    def redo() : Unit = {invoker.redoStep()}

    def boardToString(): String = {fen}

    def createOutput(): String = {fen}

    def promotePawn(pieceKind: String): Unit = {}

    def squareClicked(clickedSquare: Int): Unit = {}

    def nextTheme(): Unit = {}

    def context: cController. ControllerComponent. Extra. ChessContext = ???
    
    
    def context_=(value: cController. ControllerComponent. Extra. ChessContext): Unit = ???

    override def resetBoard(): Unit = ???
}
