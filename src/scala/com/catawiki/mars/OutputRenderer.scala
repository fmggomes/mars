package com.catawiki.mars

object OutputRenderer {
  def apply(positions: List[RobotPosition]) = {
    val stringBuilder = new StringBuilder
    positions.foreach { position =>
      stringBuilder.append(position.x)
      stringBuilder.append(position.y)
      stringBuilder.append(position.direction.stringRepresentation)
      stringBuilder.append('\n')
    }
    stringBuilder.toString
  }

}
