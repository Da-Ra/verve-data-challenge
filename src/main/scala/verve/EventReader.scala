package verve

import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._


class EventReader(impressionsFile: String, clicksFile: String) {
  implicit val formats = DefaultFormats

  def readImpressions(): List[Impression] = {
    var impressionsList = List.empty[Impression]

    val impressionsLines = Source.fromFile(impressionsFile).mkString
    val parsedImpressions: List[Impression] = try {
        parse(impressionsLines).extract[List[JValue]](formats, manifest[List[JValue]])
        .flatMap {
            impressionJson => 
                try {
                    Some(impressionJson.extract[Impression])
                } catch {
                    case e: Exception =>
                        println(s"Error parsing JSON: $impressionJson\n${e.getMessage}")
                        None
                }
        }
    } catch {
        case e: Exception => 
            println(s"Error parsing JSON: ${e.getMessage}")
            Nil
    }

    val allImpressions = impressionsList ++ parsedImpressions
    allImpressions
    
  }

  def readClicks(): List[Click] = {
    val clicksJson = Source.fromFile(clicksFile).mkString
    parse(clicksJson).extract[List[Click]]
  }
}