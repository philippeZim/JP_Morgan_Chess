package aView.GUIComponent

import Model.ChessComponent.ChessTrait
import Model.ChessComponent.RealChess.RealChessFacade
import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.Extra.{ChessContext, State}
import cController.ControllerComponent.RealChessController.Controller
import javafx.stage.Screen
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label}
import scalafx.scene.effect.DropShadow
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{Background, BackgroundFill, CornerRadii, GridPane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import util.Observer

class GuiMenu(option_controller: Option[ControllerTrait]) extends VBox, Observer {
    
    val controller: ControllerTrait = option_controller match {
        case Some(a) => a
        case _ => null
    }

    controller.add(this)

    override def update: Unit = {
        println("test test")
        controller.context.state match {
            case State.remisState => {
                val infoLabel = new Label("Remis") {
                    style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-color: #F5F5DC;"
                    wrapText = true
                }
                children = Seq(theme_button, undo_button, redo_button, infoLabel)
            }
            case State.whiteWonState => {
                val infoLabel = new Label("Schwarz wurde vernichtend geschlagen") {
                    style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-color: #F5F5DC;"
                    wrapText = true
                }
                children = Seq(theme_button, undo_button, redo_button, infoLabel)
            }
            case State.blackWonState => {
                val infoLabel = new Label("Weiß wurde vernichtend geschlagen") {
                    style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-color: #F5F5DC;"
                    wrapText = true
                }
                children = Seq(theme_button, undo_button, redo_button, infoLabel)
            }
            case _ => children = Seq(theme_button, undo_button, redo_button)
        }
    }

    override def specialCase: Unit = ()

    val screenBounds = Screen.getPrimary.getVisualBounds
    val vw = screenBounds.getWidth
    val vh = screenBounds.getHeight

    val theme_button = new Button("Theme") {
        prefWidth = vh * 0.1
        prefHeight = vh * 0.05
        style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-color: #F5F5DC;"
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
        style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-color: #F5F5DC;"
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
        style = "-fx-font-size: 16px; -fx-font-family: 'Roboto'; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-color: #F5F5DC;"
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

    children = Seq(theme_button, undo_button, redo_button)

    alignment = Pos.Center
    spacing = vh * 0.05


    this.setPrefSize(vh * 0.3, vh)
    val marginScreenHeight = vh * 0.05
    VBox.setMargin(this, Insets(marginScreenHeight, marginScreenHeight * 3, marginScreenHeight, marginScreenHeight * 3))
    style = "-fx-background-color: #A9A9A9;"

}
