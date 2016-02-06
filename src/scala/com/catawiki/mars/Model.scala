package com.catawiki.mars

case class PlateauDimension(x: Int, y: Int)

case class RobotPosition(x: Int, y: Int, direction: Direction)

object Direction {
  val all = Set(North(), South(), East(), West())

}
sealed trait Direction {
  val stringRepresentation: Char
}
case class North() extends Direction {
  val stringRepresentation = 'N'
}
case class South() extends Direction {
  val stringRepresentation = 'S'
}
case class East() extends Direction {
  val stringRepresentation = 'E'
}
case class West() extends Direction {
  val stringRepresentation = 'W'
}


sealed trait Instruction
case class Left() extends Instruction
case class Right() extends Instruction
case class Move() extends Instruction

case class RobotSetup(initialPosition: RobotPosition, instructions: List[Instruction])

case class DeploymentSetup(plateauDimension: PlateauDimension, robotSetups: List[RobotSetup])