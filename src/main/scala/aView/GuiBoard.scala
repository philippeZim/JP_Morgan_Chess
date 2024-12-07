package aView

import Controller.{ChessContext, Controller}
import Model.{Piece, PieceType, ChessBoard}
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

import java.nio.file.Paths
import java.nio.file.Paths.*
import scala.annotation.tailrec

class GuiBoard(controlololol: Option[Controller]) extends StackPane {

    val controller : Controller = controlololol match {
        case Some(a) => a
        case _ => new Controller("", new Controller.ChessContext(),"");
    }
    val screenBounds = Screen.getPrimary.getVisualBounds
    val screenHeight = screenBounds.getHeight
    val gridBoard = updateGrid()
    val wrapperPane = new BorderPane {
        center = gridBoard
    }
    val currentPath = get("").toAbsolutePath.toString
    println(s"Current folder: $currentPath")
    val imageBoard = new ImageView("file:src\\images\\bsp_chess_board_clipped_Again.jpg");
    imageBoard.fitWidth = screenHeight
    imageBoard.fitHeight = screenHeight * 0.95

    /*
    for (row <- 0 until 8) {
        for (col <- 0 until 8) {
            val stackP = new StackPane()
            val button = new Button(s"Button $row,$col")
            button.visible = false
            button.maxWidth = Double.PositiveInfinity
            button.maxHeight = Double.PositiveInfinity
            button.onAction = _ => {controller.squareClicked(row*8 + col)}
            stackP.children += button
            gridBoard.add(stackP, col, row)
        }
    }

     */

    def updateGrid(): GridPane = {


        val pieceMap = Map(
            "p" -> "black-pawn",
            "r" -> "black-rook",
            "n" -> "black-knight",
            "b" -> "black-bishop",
            "q" -> "black-queen",
            "k" -> "black-king",
            "P" -> "white-pawn",
            "R" -> "white-rook",
            "N" -> "white-knight",
            "B" -> "white-bishop",
            "Q" -> "white-queen",
            "K" -> "white-king"

        )

        @tailrec
        def loopChildren(li: List[(Piece, Int)], acc: List[Node]): List[Node] = {
            li match {
                case Nil => acc
                case h :: t => h match {
                    case (e: Piece, i: Int) => {
                        val sp: StackPane = new StackPane {
                            val button = new Button()
                            button.visible = false
                            button.maxWidth = Double.PositiveInfinity
                            button.maxHeight = Double.PositiveInfinity
                            button.onAction = _ => {
                                controller.squareClicked(i)
                            }
                            if (e.toString() == ".") {
                                children = button
                            } else {
                                val path = "file:src\\images\\pieces\\" + pieceMap(e.toString()) + ".png"
                                val img = ImageView(path)
                                children = List(button, img)
                            }


                        }
                        loopChildren(t, sp::acc)
                    }
                    case _ => loopChildren(t, acc)
                }
            }
        }
        val new_children = loopChildren(ChessBoard.fenToBoard(controller.fen).toList.zipWithIndex, List())


        @tailrec
        def addAllToGrid(li: List[(Node, Int)], grid: GridPane): GridPane = {
            li match {
                case Nil => grid
                case h :: t => h match {
                    case (e: Node, i: Int) =>
                        val row: Int = i / 8
                        val col: Int = i % 8
                        grid.add(e, col, row)
                        addAllToGrid(t, grid)
                    case _ => addAllToGrid(t, grid)
                }
            }
        }

        addAllToGrid(new_children.zipWithIndex, new GridPane())
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
