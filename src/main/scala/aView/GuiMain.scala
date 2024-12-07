package aView

import Controller.Controller
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.{Node, Scene}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{Background, BorderPane, FlowPane, HBox, Pane}
import scalafx.scene.paint.Color.*
import scalafx.scene.paint.*
import scalafx.scene.text.Text
import scalafx.scene.shape.Rectangle
import util.Observer

object GuiMain extends JFXApp3 {

    var controller : Option[Controller]= None
    def start(): Unit = {

        stage = new JFXApp3.PrimaryStage {
            
            title = "JP Morgan chess"
            scene = new Scene() {

                root = new BorderPane {
                    //padding
                    style = "-fx-background-color:BLACK"
                    left = new GuiBoard(controller)

                }
            }
            fullScreen = true
        }
    }

    def setController(controller: Controller) : Unit = {
        this.controller = Some(controller)
    }



    /*override def start(): Unit = {
        stage = new JFXApp3.PrimaryStage {
            //    initStyle(StageStyle.Unified)
            title = "ScalaFX Hello World"
            scene = new Scene {
                fill = Color.rgb(38, 38, 38)
                content = new HBox {
                    padding = Insets(50, 80, 50, 80)
                    children = Seq(
                        new Text {
                            text = "Scala"
                            style = "-fx-font: normal bold 100pt sans-serif"
                            fill = new LinearGradient(
                                endX = 0,
                                stops = Stops(Red, DarkRed))
                        },
                        new Text {
                            text = "FX"
                            style = "-fx-font: italic bold 100pt sans-serif"
                            fill = new LinearGradient(
                                endX = 0,
                                stops = Stops(White, DarkGray)
                            )
                            effect = new DropShadow {
                                color = DarkGray
                                radius = 15
                                spread = 0.25
                            }
                        }
                    )
                }
            }
        }
    } */
}