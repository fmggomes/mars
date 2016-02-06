package com.catawiki.mars

import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, Matchers, FunSpec}

class RobotMoverTest extends FunSpec with Matchers with MockitoSugar with BeforeAndAfter {

  val robotMover = new RobotMover
  val someX = 1
  val someY = 2
  val plateauDimension = PlateauDimension(5, 5)

  describe("RobotMover") {
    it("moves North") {
      moveRobot(North()) should be (RobotPosition(someX, someY + 1, North()))
    }

    it("moves South") {
      moveRobot(South()) should be (RobotPosition(someX, someY - 1, South()))
    }

    it("moves East") {
      moveRobot(East()) should be (RobotPosition(someX + 1, someY, East()))
    }

    it("moves West") {
      moveRobot(West()) should be (RobotPosition(someX - 1, someY, West()))
    }

    it("doesn't moves North beyond the plateau") {
      moveRobot(North(), y = plateauDimension.y).y should be (plateauDimension.y)
    }

    it("doesn't moves South beyond the plateau") {
      moveRobot(South(), y = 0).y should be (0)
    }

    it("doesn't moves East beyond the plateau") {
      moveRobot(East(), x = plateauDimension.x).x should be (plateauDimension.x)
    }

    it("doesn't moves West beyond the plateau") {
      moveRobot(West(), x = 0).x should be (0)
    }
  }

  private def moveRobot(direction: Direction, x: Int = someX, y: Int = someY) = {
    robotMover.apply(RobotPosition(x, y, direction), plateauDimension)
  }

}
