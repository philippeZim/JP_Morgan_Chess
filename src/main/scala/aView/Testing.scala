package aView


import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, Pane}
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

object Testing extends JFXApp3 {

    override def start(): Unit = {
        stage = new JFXApp3.PrimaryStage {
            title.value = "Hello Stage"
            width = 600
            height = 450
            scene = new Scene {
                fill = LightGreen
                val imageBoard = new ImageView("bsp_chess_board_clipped_Again.jpg");
                root = new Pane {
                    //padding

                    children = imageBoard
                }
            }
        }
    }
}