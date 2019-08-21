package edu.service.impl

import edu.model.Color.Color
import edu.model.{Box, Boxes, Color, SubBox}
import edu.service.BoxManager

class BoxMangerImpl  extends BoxManager {

    def mergeBoxes(boxOne : Box, boxTwo : Box) : Boxes[Color] = {
        (boxOne, boxTwo) match {
            case (Boxes(firstBoxOne, secondBoxOne, thirdBoxOne, forthBoxOne), Boxes(firstBoxTwo, secondBoxTwo, thirdBoxTwo, forthBoxTwo)) =>
                val firstBox = getValue(firstBoxOne, firstBoxTwo)
                val secondBox = getValue(secondBoxOne, secondBoxTwo)
                val thirdBox = getValue(thirdBoxOne, thirdBoxTwo)
                val forthBox = getValue(forthBoxOne, forthBoxTwo)
                Boxes(firstBox, secondBox, thirdBox, forthBox)

            case (subBoxOne @ SubBox(_), Boxes(firstBoxTwo, secondBoxTwo, thirdBoxTwo, forthBoxTwo)) =>
                val firstBox = getValue(subBoxOne, firstBoxTwo)
                val secondBox = getValue(subBoxOne, secondBoxTwo)
                val thirdBox = getValue(subBoxOne, thirdBoxTwo)
                val forthBox = getValue(subBoxOne, forthBoxTwo)
                Boxes(firstBox, secondBox, thirdBox, forthBox)

            case (Boxes(firstBoxOne, secondBoxOne, thirdBoxOne, forthBoxOne), subBoxTwo @ SubBox(_)) =>
                val firstBox = getValue(firstBoxOne, subBoxTwo)
                val secondBox = getValue(secondBoxOne, subBoxTwo)
                val thirdBox = getValue(thirdBoxOne, subBoxTwo)
                val forthBox = getValue(forthBoxOne, subBoxTwo)
                Boxes(firstBox, secondBox, thirdBox, forthBox)

            case (_, _) => throw new Exception("Both the boxes must be defined to merge")
        }
    }

    private def getValue(first : Box, second : Box) = {
        (first, second) match {
            case (SubBox(valueOne), SubBox(valueTwo)) =>
                val color = addColor(valueOne, valueTwo)
                SubBox(color)

            case (boxOne, boxTwo) => mergeBoxes(boxOne, boxTwo)
        }
    }

    private def addColor(colorOne : Color.Value, colorTwo : Color.Value) =
        (colorOne, colorTwo) match {
            case (Color.White, Color.White) => Color.White
            case (Color.Black, Color.Black) => Color.Black
            case (Color.Black, Color.White) => Color.Black
            case (Color.White, Color.Black) => Color.Black
            case (_, _) => throw new Exception("All the colors must be defined either white or black")
        }
}
