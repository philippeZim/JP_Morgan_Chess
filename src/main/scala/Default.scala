import JP_Morgan_Chess.ChessComponent.ChessTrait
import JP_Morgan_Chess.ChessComponent.Model.DevourChess.DevourChessFacade
import JP_Morgan_Chess.ChessComponent.RealChess.RealChessFacade
import JP_Morgan_Chess.ControllerComponent.ControllerTrait
import JP_Morgan_Chess.ControllerComponent.DuoChessController.Controller
import JP_Morgan_Chess.ControllerComponent.Extra.ChessContext
import JP_Morgan_Chess.ControllerComponent.SoloChessController.EngineController

object Default {
    given ChessTrait = RealChessFacade()

    val gameMode: ChessTrait = summon[ChessTrait]

    given ControllerTrait = EngineController(using gameMode, gameMode.getDefaultFen(), new ChessContext, "")
}