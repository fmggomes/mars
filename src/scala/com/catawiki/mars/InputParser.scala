package com.catawiki.mars

import scala.collection.mutable


object InputParser {

  def apply(inputLines: Iterator[String]) = {
    val plateauDimension = {
      val dimensionArray = inputLines.next().split(' ')
      PlateauDimension(dimensionArray(0).toInt, dimensionArray(1).toInt)
    }

    val robotsSetup = new mutable.ListBuffer[RobotSetup]
    while (inputLines.hasNext) {
      val robotPositionArray = inputLines.next().split(' ')
      val initialPosition = {
        def directionFromLetter(letter: String) = Direction.all.find(_.stringRepresentation == letter.head).get
        RobotPosition(robotPositionArray(0).toInt, robotPositionArray(1).toInt, directionFromLetter(robotPositionArray(2)))
      }
      def instructionFromLetter(letter: Char) = letter match {
        case 'L' => Left()
        case 'R' => Right()
        case 'M' => Move()
      }
      val instructions = inputLines.next().map(instructionFromLetter).toList
      robotsSetup += RobotSetup(initialPosition, instructions)
    }
    DeploymentSetup(plateauDimension, robotsSetup.toList)
  }
}
