package com.catawiki.mars

import scala.io.Source


object RobotCommander {

  def main(args: Array[String]): Unit = {

    val deploymentSetup = InputParser(Source.fromInputStream(getClass.getResourceAsStream("/robotinstructions.txt")).getLines())
    val finalPositions = new ExecuteInstructions().apply(deploymentSetup)
    val outputString = OutputRenderer(finalPositions)
    println(outputString)

  }
}

