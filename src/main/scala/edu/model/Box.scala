package edu.model

import edu.model.Color.Color

trait Box

case class SubBox(value : Color) extends Box

case class Boxes[T](first : Box, second : Box, third : Box, fourth : Box) extends Box
