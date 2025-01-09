package JP_Morgan_Chess.aView.GUIComponent

import JP_Morgan_Chess.ChessComponent.ChessTrait
import JP_Morgan_Chess.ControllerComponent.ControllerTrait
import JP_Morgan_Chess.ControllerComponent.DuoChessController.Controller
import JP_Morgan_Chess.ControllerComponent.Extra.ChessContext
import JP_Morgan_Chess.util.Observer
import javafx.stage.Screen
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.effect.DropShadow
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Font

class GuiMenu(option_controller: Option[ControllerTrait]) extends VBox{
    val controller: ControllerTrait = option_controller match {
        case Some(a) => a
        case _ => new Controller(using summon[ChessTrait], "", new ChessContext(), "");
    }

    val screenBounds = Screen.getPrimary.getVisualBounds
    val vw = screenBounds.getWidth
    val vh = screenBounds.getHeight

    val theme_button = new Button("Theme") {
        prefWidth = vh * 0.1
        prefHeight = vh * 0.05
        style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold;"
        // Custom font and text color
        background = new Background(
            Array(new BackgroundFill(Color.White, new CornerRadii(10), Insets.Empty)) // White background with rounded corners
        )
        effect = new DropShadow { // Add a subtle shadow
            color = Color.Gray
            radius = 5
            spread = 0.2
        }
        onAction = _ => controller.nextTheme()
    }

    val undo_button = new Button("Undo") {
        prefWidth = vh * 0.1
        prefHeight = vh * 0.05
        style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold;"
        // Custom font and text color
        background = new Background(
            Array(new BackgroundFill(Color.White, new CornerRadii(10), Insets.Empty)) // White background with rounded corners
        )
        effect = new DropShadow { // Add a subtle shadow
            color = Color.Gray
            radius = 5
            spread = 0.2
        }
        onAction = _ => controller.undo()

    }

    val redo_button = new Button("Redo") {
        prefWidth = vh * 0.1
        prefHeight = vh * 0.05
        style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold;"
        // Custom font and text color
        background = new Background(
            Array(new BackgroundFill(Color.White, new CornerRadii(10), Insets.Empty)) // White background with rounded corners
        )
        effect = new DropShadow { // Add a subtle shadow
            color = Color.Gray
            radius = 5
            spread = 0.2
        }
        onAction = _ => controller.redo()

    }
    alignment = Pos.Center
    spacing = vh * 0.05
    children = Seq(theme_button, undo_button, redo_button)

    this.setPrefSize(vh * 0.3, vh)
    val marginScreenHeight = vh * 0.05
    VBox.setMargin(this, Insets(marginScreenHeight, marginScreenHeight * 3, marginScreenHeight, marginScreenHeight * 3))
    style = s"-fx-background-color:black"

}
