package ModelTests.ChessComponentTests

import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.RealChessController.Command

class InvokerDummy {
    def doStep(command: Command): Unit = ???
    def newCommand (fen: String, exFen: String, controller: ControllerTrait): Command = ???
    def redoStep(): Unit = ???
    def undoStep(): Unit = ???
}
