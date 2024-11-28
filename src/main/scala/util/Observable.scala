package util

trait Observer {
    def update: Unit
    
    def specialCase : Unit
}

class Observable {
    var subscribers: Vector[Observer] = Vector()

    def add(s: Observer): Unit = subscribers = subscribers :+ s

    def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

    def notifyObservers: Unit = subscribers.foreach(o => o.update)
    
    def ringOverservers : Unit = subscribers.foreach(o => o.specialCase)
}
