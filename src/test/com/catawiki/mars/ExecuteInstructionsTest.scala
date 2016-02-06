package com.catawiki.mars

import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, Matchers, FunSpec}
import org.mockito.Mockito._
import org.mockito.Matchers._


class ExecuteInstructionsTest extends FunSpec with Matchers with MockitoSugar with BeforeAndAfter {

  val robotRotator = mock[RobotRotator]
  val robotMover = mock[RobotMover]
  val executeInstructions = new ExecuteInstructions(robotRotator, robotMover)
  val somePlateauDimension = PlateauDimension(5, 5)
  val somePosition = RobotPosition(1, 1, North())
  val someFinalPosition = RobotPosition(2, 2, South())

  before {
    reset(robotMover, robotRotator)
  }

  describe("ExecuteInstruction") {
    it("should return no final positions if there is no robot") {
      executeInstructions(DeploymentSetup(somePlateauDimension, List())) should be (List())
    }

    it("should return the initial positions if there are no commands") {
      val robot1 = RobotSetup(RobotPosition(1,1,North()), List())
      val robot2 = RobotSetup(RobotPosition(2,2,South()), List())
      executeInstructions(DeploymentSetup(somePlateauDimension, List(robot1, robot2))) should be (List(robot1.initialPosition, robot2.initialPosition))
    }

    it("should call the robot rotator for left command") {
      when(robotRotator.apply(Left(), somePosition)).thenReturn(someFinalPosition)
      execute(List(Left())) should be (List(someFinalPosition))
    }

    it("should call the robot rotator for right command") {
      when(robotRotator.apply(Right(), somePosition)).thenReturn(someFinalPosition)
      execute(List(Right())) should be (List(someFinalPosition))
    }

    it("should call the robot mover for move command") {
      when(robotMover.apply(somePosition, somePlateauDimension)).thenReturn(someFinalPosition)
      execute(List(Move())) should be (List(someFinalPosition))
    }

    it("should support multiple commands in the correct order") {
      when(robotMover.apply(any(), any())).thenReturn(somePosition)
      when(robotRotator.apply(any(), any())).thenReturn(somePosition)
      execute(List(Left(), Right(), Move()))

      val order = org.mockito.Mockito.inOrder(robotMover, robotRotator)
      order.verify(robotRotator).apply(Left(), somePosition)
      order.verify(robotRotator).apply(Right(), somePosition)
      order.verify(robotMover).apply(somePosition, somePlateauDimension)
    }
  }

  private def execute(instructions: List[Instruction]) = {
    executeInstructions(DeploymentSetup(somePlateauDimension, List(RobotSetup(somePosition, instructions))))

  }
}
