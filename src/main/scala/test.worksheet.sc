import cController.ControllerComponent.Extra.ChessContext
import play.api.libs.json.*

val hallo = Json.toJson(0)

def to(context : ChessContext, fen : String) = {
    <box>
        <fen>
            {fen}
        </fen>
        <state>
            {context.state.ordinal}
        </state>
    </box>
}