package edu.service

import edu.model.Color.Color
import edu.model.{Box, Boxes}

trait BoxManager {

    def mergeBoxes(boxOne : Box, boxTwo : Box) : Boxes[Color]
}
