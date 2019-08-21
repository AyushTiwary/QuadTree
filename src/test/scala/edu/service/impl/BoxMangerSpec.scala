package edu.service.impl

import edu.model.{Boxes, Color, SubBox}
import edu.service.BoxManager
import org.scalatest.{FlatSpec, Matchers}

class BoxMangerSpec extends FlatSpec with Matchers {

    val boxManager : BoxManager = new BoxMangerImpl

    "BoxManager" should "merge the two boxes [case 1]" in {
        val boxOne = Boxes(SubBox(Color.White), SubBox(Color.White), SubBox(Color.Black), SubBox(Color.Black))
        val boxTwo = Boxes(SubBox(Color.Black), SubBox(Color.White), SubBox(Color.White), SubBox(Color.Black))
        val expectedResult = Boxes(SubBox(Color.Black),SubBox(Color.White),SubBox(Color.Black),SubBox(Color.Black))
        val result = boxManager.mergeBoxes(boxOne, boxTwo)
        assert(result == expectedResult)
    }

    it should "merge the two boxes [case 2]" in {
        val boxOne = Boxes(SubBox(Color.White), SubBox(Color.White), Boxes(SubBox(Color.Black), SubBox(Color.White), SubBox(Color.White), SubBox(Color.White)), SubBox(Color.White))
        val boxTwo = Boxes(SubBox(Color.Black), SubBox(Color.White), SubBox(Color.White), Boxes(SubBox(Color.White), SubBox(Color.White), SubBox(Color.Black), SubBox(Color.White)))
        val expectedResult = Boxes(SubBox(Color.Black),SubBox(Color.White),Boxes(SubBox(Color.Black),SubBox(Color.White),SubBox(Color.White),SubBox(Color.White)),Boxes(SubBox(Color.White),SubBox(Color.White),SubBox(Color.Black),SubBox(Color.White)))
        val result = boxManager.mergeBoxes(boxOne, boxTwo)
        assert(result == expectedResult)
    }

    it should "merge the two boxes [case 3]" in {
        val boxOne = Boxes(SubBox(Color.White), SubBox(Color.White), SubBox(Color.White), Boxes(SubBox(Color.White), SubBox(Color.White), SubBox(Color.White), SubBox(Color.White)))
        val boxTwo = Boxes(SubBox(Color.Black), SubBox(Color.White), SubBox(Color.White), Boxes(SubBox(Color.White), SubBox(Color.White), SubBox(Color.Black), SubBox(Color.White)))
        val expectedResult = Boxes(SubBox(Color.Black),SubBox(Color.White),SubBox(Color.White),Boxes(SubBox(Color.White),SubBox(Color.White),SubBox(Color.Black),SubBox(Color.White)))
        val result = boxManager.mergeBoxes(boxOne, boxTwo)
        assert(result == expectedResult)
    }

    it should "throw Exception if all the colors are not given" in {
        val boxOne = Boxes(SubBox(Color.White), SubBox(Color.White), SubBox(Color.Black), SubBox(Color.Black))
        val boxTwo = Boxes(SubBox(null), SubBox(Color.White), SubBox(Color.White), SubBox(Color.Black))
        val exception = intercept[Exception] {
            boxManager.mergeBoxes(boxOne, boxTwo)
        }
        assert(exception.getMessage == "All the colors must be defined either white or black")
    }

    it should "throw Exception if both the boxes are not given" in {
        val boxOne = Boxes(SubBox(Color.White), SubBox(Color.White), SubBox(Color.Black), SubBox(Color.Black))
        val boxTwo = null
        val exception = intercept [Exception] {
            boxManager.mergeBoxes(boxOne, boxTwo)
        }
        assert(exception.getMessage == "Both the boxes must be defined to merge")
    }
}
