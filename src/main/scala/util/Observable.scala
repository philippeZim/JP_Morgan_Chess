package util

import aView.GUIComponent.GuiBoard
import scalafx.application.Platform

trait Observer {
    def update: Unit
    
    def specialCase : Unit
}

class Observable {
    var subscribers: Vector[Observer] = Vector()

    def add(s: Observer): Unit = subscribers = subscribers :+ s

    def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

    def notifyObservers: Unit = subscribers.foreach(o =>
        if (o.isInstanceOf[GuiBoard]) {
            Platform.runLater(() => {
                o.update
            })
        } else {
            o.update
        }
    )
    
    def ringObservers : Unit = subscribers.foreach(o => o.specialCase)
}
