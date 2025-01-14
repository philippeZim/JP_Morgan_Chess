package cController.ControllerComponent.StateComponent.jsonSolution

import cController.ControllerComponent.Extra.{ChessContext, State}
import cController.ControllerComponent.StateComponent.{ApiFileTrait, DataWrapper}
import play.api.libs.json.*
import java.io.{File, PrintWriter}

class JSONApi extends ApiFileTrait {
    def to(context: ChessContext, fen: String): DataWrapper = {
        val stateNumber = context.state.ordinal
        val jsonData = Json.parse(s"{\"Box\": {\"fen\": \"$fen\", \"state\": $stateNumber}}")
        DataWrapper(None, Some(jsonData))
    }

    def from(data: DataWrapper): (String, State) = {
        val value: JsValue = data.getJson()
        val test = (value \ "Box" \ "fen").get
        val falseFen = Json.stringify((value \ "Box" \ "fen").get)
        val correctFen = falseFen.substring(1, falseFen.length - 1)
        (correctFen, State.fromOrdinal(Json.stringify((value \ "Box" \ "state").get).toInt))
    }

    def printTo(context: ChessContext, fen: String) = {
        val writer = new PrintWriter(new File("src/main/resources/GameState.json"))
        val data: JsValue  = to(context, fen).getJson()
        writer.write(Json.stringify(data))
        writer.close()
    }
}
