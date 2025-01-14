package aView.GUIComponent

import Model.ChessComponent.BasicChessComponent.StandartChess.{ChessBoard, Piece, PieceType}
import Model.ChessComponent.ChessTrait
import Model.ChessComponent.RealChess.RealChessFacade
import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.Extra.ChessContext
import cController.ControllerComponent.DuoChessController.Controller
import javafx.stage.Screen
import scalafx.application.JFXApp3
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.geometry.Pos.Center
import scalafx.scene.control.{Button, Label}
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.Priority.Always
import scalafx.scene.layout.*
import scalafx.scene.paint.*
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}
import scalafx.scene.{Node, Scene}
import scalafx.stage.Stage
import util.Observer

import java.nio.file.Paths
import java.nio.file.Paths.*
import scala.annotation.tailrec

class GuiBoard(option_controller: Option[ControllerTrait]) extends GridPane, Observer{
    override def update: Unit = {
        updateGrid()
    }

    override def specialCase: Unit = ()
    val controller : ControllerTrait = option_controller match {
        case Some(a) => a
        case _ => new Controller(new RealChessFacade(), "", new ChessContext(),"");
    }
    controller.add(this)
    val screenBounds = Screen.getPrimary.getVisualBounds
    val vw = screenBounds.getWidth
    val vh = screenBounds.getHeight

    val color_pallets: Vector[(String, String, String)] = Vector(
        ("#cb9df0", "#F0C1E1", "#FFF9BF"),
        ("#9AA6B2", "#BCCCDC", "#D9EAFD"),
        ("#493628", "#AB886D", "#D6C0B3"),
        ("#FFB0B0", "#FFD09B", "#FFECC8"),
        ("#7E60BF", "#E4B1F0", "#FFE1FF"),
        ("#921A40", "#C75B7A", "#F4D9D0"),
        ("#508D4E", "#80AF81", "#D6EFD8"),
        ("#F19ED2", "#E8C5E5", "#F7F9F2"),
        ("#55AD9B", "#95D2B3", "#D8EFD3"),
        ("#FF7D29", "#FFBF78", "#FFEEA9"),
        ("#A7D477", "#F72C5B", "#FF748B"),
        ("#481E14", "#9B3922", "#F2613F"),
        ("#D20062", "#D6589F", "#D895DA"),
        ("#41C9E2", "#ACE2E1", "#F7EEDD"),
        ("#31363F", "#76ABAE", "#EEEEEE"),
        ("#503C3C", "#7E6363", "#A87C7C"),
        ("#424769", "#7077A1", "#F6B17A"),
        ("#DC84F3", "#E9A8F2", "#F3CCF3"),
        ("#5C5470", "#B9B4C7", "#FAF0E6")
    )

    updateGrid()

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
                                    val lcol1 = color_pallets(controller.current_theme)._3
                                    fill = Paint.valueOf(lcol1)
                                } else {
                                    val lcol2 = color_pallets(controller.current_theme)._2
                                    fill = Paint.valueOf(lcol2)
                                }

                                width = vh * 0.1
                                height = vh * 0.1
                                arcWidth = vh * 0.02
                                arcHeight = vh * 0.02
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
                                    val lcol3 = color_pallets(controller.current_theme)._1
                                    style = s"-fx-effect: dropshadow(gaussian, $lcol3, 10, 0.8, 0, 0);"
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
        children = Seq()
        addAllToGrid(new_children.zipWithIndex)
        val lcol3 = color_pallets(controller.current_theme)._1
        this.style = s"-fx-background-color:$lcol3"
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

}
