/**
  * Command.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util

import model.Field

trait Command:
    def execute: Field
    def undoStep: Field
    def redoStep: Field