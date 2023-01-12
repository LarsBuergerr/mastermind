/**
  * Observable.scala
  */

//****************************************************************************** PACKAGE
package de.htwg.se.mastermind
package util


//****************************************************************************** INTERFACE DEFINITION
trait Observer {
    def update: Unit
}


//****************************************************************************** CLASS DEFINITIONS
class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s 

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers: Unit = subscribers.foreach(o => o.update)
}
