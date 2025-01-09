package cController.ControllerComponent.RealChessController

trait Command {
    def doStep:Unit
    def undoStep:Unit
    def redoStep:Unit
}


class SetCommand(fen : String, exFen : String, controller: Controller) extends Command {
    override def doStep: Unit = controller.fen = fen
    override def undoStep: Unit = controller.fen = exFen
    override def redoStep: Unit =  controller.fen = fen
}
