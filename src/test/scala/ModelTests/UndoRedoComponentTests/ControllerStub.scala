package ModelTests.UndoRedoComponentTests

import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.RealChessController.{ChessContext, Command, SetCommand, UndoInvoker}

class ControllerStub(override var fen : String) extends ControllerTrait {
    override var current_theme: Int = 0

    def play(move: (Int, Int)): Unit = {
        UndoInvoker.doStep(new SetCommand(move.productIterator.mkString("-"), fen, this))
    }

    def undo() : Unit = {UndoInvoker.undoStep()}

    def redo() : Unit = {UndoInvoker.redoStep()}

    def boardToString(): String = {fen}

    def createOutput(): String = {fen}

    def promotePawn(pieceKind: String): Unit = {}

    def squareClicked(clickedSquare: Int): Unit = {}

    def nextTheme(): Unit = {}

    def context: ChessContext = ???

    def context_=(value: ChessContext): Unit = {}
}
