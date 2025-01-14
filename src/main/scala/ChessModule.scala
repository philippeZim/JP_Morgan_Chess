import Model.ChessComponent.ChessTrait
import Model.ChessComponent.DevourChess.DevourChessFacade
import Model.ChessComponent.RealChess.RealChessFacade
import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.DuoChessController.Controller
import cController.ControllerComponent.Extra.ChessContext
import cController.ControllerComponent.SoloChessController.EngineController
import com.google.inject.{AbstractModule, Provides}
import com.google.inject.name.{Named, Names}

class ChessModule extends AbstractModule {
    override def configure(): Unit = {
        bind(classOf[ChessTrait]).annotatedWith(Names.named("RealChess")).to(classOf[RealChessFacade])
        bind(classOf[ChessTrait]).annotatedWith(Names.named("DevourChess")).to(classOf[DevourChessFacade])
    }

    @Provides
    @Named("DuoChess") def provideServiceA1(@Named("RealChess") gameMode: ChessTrait): ControllerTrait = {
        val arg1 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        val arg2 = new ChessContext
        val arg3 = ""
        new Controller(gameMode, arg1, arg2, arg3)
    }

    @Provides
    @Named("EngineChess") def provideServiceA2(@Named("RealChess") gameMode: ChessTrait): ControllerTrait = {
        val arg1 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        val arg2 = new ChessContext
        val arg3 = ""
        val arg4 = 10
        new EngineController(gameMode, arg1, arg2, arg3, arg4)
    }

}