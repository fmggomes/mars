package com.catawiki.mars

import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, Matchers, FunSpec}

class RobotRotatorTest extends FunSpec with Matchers with MockitoSugar with BeforeAndAfter {

  val robotRotator = new RobotRotator
  val somePosition = RobotPosition(1, 1, North())

  describe("RobotRotator") {
    it("rotates Left") {
      robotRotator(Left(), somePosition) should be(somePosition.copy(direction = West()))
    }

    it("rotates Right") {
      robotRotator(Right(), somePosition) should be(somePosition.copy(direction = East()))
    }
  }
}
