package Model.ChessComponent

import Model.ChessComponent.DevourChess.DevourChessFacade
import Model.ChessComponent.RealChess.RealChessFacade

object Default {
    given ChessTrait = DevourChessFacade()
}
