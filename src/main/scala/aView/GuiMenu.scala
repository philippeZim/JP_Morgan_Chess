package aView

import Controller.Controller
import javafx.stage.Screen
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import util.Observer

class GuiMenu(option_controller: Option[Controller]) extends VBox, Observer{
    val controller: Controller = option_controller match {
        case Some(a) => a
        case _ => new Controller("", new Controller.ChessContext(), "");
    }
    override def update: Unit = ()

    override def specialCase: Unit = ()

    val screenBounds = Screen.getPrimary.getVisualBounds
    val vw = screenBounds.getWidth
    val vh = screenBounds.getHeight

    val b1 = new Button("Theme") {
        prefWidth = vh * 0.1
        prefHeight = vh * 0.1
        onAction = _ => controller.nextTheme()
    }
    children = b1

}
