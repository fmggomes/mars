package com.catawiki.mars


class ExecuteInstructions(robotRotator: RobotRotator = new RobotRotator, robotMover: RobotMover = new RobotMover){

  def apply(deploymentSetup: DeploymentSetup) = {
    deploymentSetup.robotSetups.map { robotSetup =>
      robotSetup.instructions.foldLeft(robotSetup.initialPosition) {
        (currentPosition, instruction) => {
          def isRotationInstruction(instruction: Instruction) = instruction == Left() || instruction == Right()

          instruction match {
            case rotationInstruction if (isRotationInstruction(rotationInstruction)) => robotRotator(rotationInstruction, currentPosition)
            case Move() => robotMover(currentPosition, deploymentSetup.plateauDimension)
          }
        }
      }
    }
  }
}

class RobotRotator {
  val orderedDirections = List(North(), East(), South(), West())

  def apply(instruction: Instruction, position: RobotPosition) = {
    val directionIndex = orderedDirections.indexOf(position.direction)
    val newDirectionIndex = instruction match {
      case Left() => (directionIndex + 3) % 4
      case Right() => (directionIndex + 1) % 4
    }
    position.copy(direction = orderedDirections(newDirectionIndex))
  }
}

class RobotMover {
  def apply(position: RobotPosition, plateauDimension: PlateauDimension) = {
    def isValid(position: RobotPosition) =
      position.x >= 0 && position.x <= plateauDimension.x && position.y >= 0 && position.y <= plateauDimension.y

    val newPosition = position.direction match {
      case North() => position.copy(y = position.y + 1)
      case South() => position.copy(y = position.y - 1)
      case East() => position.copy(x = position.x + 1)
      case West() => position.copy(x = position.x - 1)
    }
    if (isValid(newPosition)) newPosition else position
  }
}
