package Model.ChessComponent

import Model.ChessComponent.DevourChess.DevourChessFacade
import Model.ChessComponent.RealChess.RealChessFacade
import com.google.inject.AbstractModule
import com.google.inject.name.Names

class ChessModule extends AbstractModule {
    override def configure(): Unit = {
        bind(classOf[ChessTrait])
            .annotatedWith(Names.named("RealChess"))
            .to(classOf[RealChessFacade])

        bind(classOf[ChessTrait])
            .annotatedWith(Names.named("DevourChess"))
            .to(classOf[DevourChessFacade])
    }
}
