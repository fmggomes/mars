package com.catawiki.mars

import com.catawiki.mars.RobotCommander._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, Matchers, FunSpec}

import scala.io.Source


class ExecuteInstructionsIntegrationTest extends FunSpec with Matchers with MockitoSugar with BeforeAndAfter {

  describe("ExecuteInstruction") {
    it("should pass the acceptance test") {
      val deploymentSetup = InputParser(Source.fromInputStream(getClass.getResourceAsStream("/robotinstructions.txt")).getLines())
      val finalPositions = new ExecuteInstructions().apply(deploymentSetup)
      OutputRenderer(finalPositions) should be (
        """|13N
           |51E
           |""".stripMargin)
    }
  }
}
