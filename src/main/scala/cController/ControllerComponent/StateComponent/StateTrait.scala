package cController.ControllerComponent.StateComponent

trait StateTrait {
    def to() : Unit
    
    def from(data : Data_Wrapper) : Unit
    
    def printTo() : Unit
}
