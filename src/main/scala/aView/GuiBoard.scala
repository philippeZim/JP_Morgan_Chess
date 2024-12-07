package aView

import Controller.{ChessContext, Controller}
import Model.{ChessBoard, Piece, PieceType}
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
import scalafx.event.ActionEvent
import scalafx.geometry.Pos.Center
import scalafx.scene.layout.Priority.Always
import scalafx.scene.shape.Rectangle
import util.Observer

import java.nio.file.Paths
import java.nio.file.Paths.*
import scala.annotation.tailrec

class GuiBoard(controlololol: Option[Controller]) extends GridPane, Observer{
    override def update: Unit = {
        updateGrid()
    }

    override def specialCase: Unit = ()
    val controller : Controller = controlololol match {
        case Some(a) => a
        case _ => new Controller("", new Controller.ChessContext(),"");
    }
    controller.add(this)
    val screenBounds = Screen.getPrimary.getVisualBounds
    val vw = screenBounds.getWidth
    val vh = screenBounds.getHeight
    updateGrid()

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

    def updateGrid(): Unit = {


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
                            val rect_bg = new Rectangle() {
                                if ((i + (i / 8)) % 2 == 0) {
                                    fill = Paint.valueOf("#F0C1E1")
                                } else {
                                    fill = Paint.valueOf("FFF9BF")
                                }

                                width = vh * 0.1
                                height = vh * 0.1
                            }
                            val button1: Button = new Button() {
                                style = "-fx-background-color: transparent; -fx-border-color: transparent; -fx-padding: 0;"
                                onAction = (_ => controller.squareClicked(63 - i))
                                focusWithin.apply()

                            }
                            button1.setPrefSize(vh * 0.1, vh * 0.1)


                            if (e.toString() == ".") {
                                children = Seq(rect_bg, button1)
                            } else {
                                val path = "/pieces/" + pieceMap(e.toString()) + ".png"
                                val img = new ImageView {
                                    //padding = Insets(vh * 0.2, 0, 0, 0)
                                    image = new Image(path)
                                    fitWidth = vh * 0.07
                                    preserveRatio = true
                                    alignmentInParent = Center
                                    effect = new DropShadow {
                                        color = Color.Black
                                        radius = 10
                                        spread = 0.2
                                    }
                                }
                                children = List(rect_bg, img, button1)
                            }


                        }
                        loopChildren(t, sp::acc)
                    }
                    case _ => loopChildren(t, acc)
                }
            }
        }
        val new_children = loopChildren(ChessBoard.fenToBoard(controller.fen).toList.reverse.zipWithIndex, List())


        @tailrec
        def addAllToGrid(li: List[(Node, Int)]): Unit = {
            li match {
                case Nil => ()
                case h :: t => h match {
                    case (e: Node, i: Int) =>
                        val row: Int = i / 8
                        val col: Int = i % 8
                        this.add(e, col, row)
                        addAllToGrid(t)
                    case _ => addAllToGrid(t)
                }
            }
        }

        addAllToGrid(new_children.zipWithIndex)
    }

    //gridBoard.setPrefSize(screenBounds.getHeight, screenBounds.getHeight)
    for (row <- 0 until 8)
    {
        val rc = new RowConstraints();
        rc.setVgrow(Always); // allow row to grow
        rc.setFillHeight(true); // ask nodes to fill height for row
        // other settings as needed...
        this.getRowConstraints().add(rc);
    }
    for (col <- 0 until 8) {
        val cc = new ColumnConstraints();
        cc.setHgrow(Always); // allow column to grow
        cc.setFillWidth(true); // ask nodes to fill space for column
        // other settings as needed...
        this.getColumnConstraints().add(cc)
    }
    this.setPrefSize(vh * 0.9, vh)
    val marginScreenHeight = vh *0.05
    BorderPane.setMargin(this, Insets(marginScreenHeight, marginScreenHeight * 3, marginScreenHeight, marginScreenHeight * 3))
    style = "-fx-background-color:rgb(203, 157, 240)"
}
