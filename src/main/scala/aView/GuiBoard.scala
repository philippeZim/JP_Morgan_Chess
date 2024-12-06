package aView

import Controller.{ChessContext, Controller}
import scalafx.stage.Stage
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Label}
import scalafx.scene.{Node, Scene}
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, ColumnConstraints, GridPane, HBox, Pane, Priority, RowConstraints, StackPane}
import scalafx.scene.paint.Color.*
import scalafx.scene.paint.*
import scalafx.scene.text.{Font, Text}
import javafx.stage.Screen
import scalafx.scene.layout.Priority.Always

class GuiBoard(controlololol: Option[Controller]) extends StackPane {

    val controller : Controller = controlololol match {
        case Some(a) => a
        case _ => new Controller("", new Controller.ChessContext(),"");
    }
    val screenBounds = Screen.getPrimary.getVisualBounds
    val screenHeight = screenBounds.getHeight
    val gridBoard = new GridPane()
    val wrapperPane = new BorderPane {
        center = gridBoard
    }
    val imageBoard = new ImageView("C:\\Software_Enginieering\\chess_IntelliJ\\bsp_chess_board_clipped_Again.jpg");
    imageBoard.fitWidth = screenHeight
    imageBoard.fitHeight = screenHeight * 0.95
    val button = new Button("Cklick me") {
        layoutX = 350
        layoutY = 250
        visible = false
    }
    for (row <- 0 until 8) {
        for (col <- 0 until 8) {
            val button = new Button(s"Button $row,$col")
            button.visible = false
            button.maxWidth = Double.PositiveInfinity
            button.maxHeight = Double.PositiveInfinity
            button.onAction = _ => {controller.squareClicked(row*8 + col)}
            gridBoard.add(button, col, row)
        }
    }
    //gridBoard.setPrefSize(screenBounds.getHeight, screenBounds.getHeight)
    for (row <- 0 until 8)
    {
        val rc = new RowConstraints();
        rc.setVgrow(Always); // allow row to grow
        rc.setFillHeight(true); // ask nodes to fill height for row
        // other settings as needed...
        gridBoard.getRowConstraints().add(rc);
    }
    for (col <- 0 until 8) {
        val cc = new ColumnConstraints();
        cc.setHgrow(Always); // allow column to grow
        cc.setFillWidth(true); // ask nodes to fill space for column
        // other settings as needed...
        gridBoard.getColumnConstraints().add(cc)
    }
    gridBoard.setPrefSize(screenHeight, screenHeight)
    val marginScreenHeight = screenHeight *0.05
    BorderPane.setMargin(gridBoard, Insets(marginScreenHeight, marginScreenHeight, marginScreenHeight, marginScreenHeight))
    children = Seq(imageBoard, wrapperPane)
}
