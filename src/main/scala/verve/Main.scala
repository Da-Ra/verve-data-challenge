package verve

import java.io.FileWriter
import org.json4s._
import org.json4s.jackson.JsonMethods._


object Main {
  implicit val formats = DefaultFormats

  def main(args: Array[String]): Unit = {

    val eventReader = new EventReader("src/main/resources/impressions.json", "src/main/resources/clicks.json")
    val metricCalculator = new MetricCalculator()

    val impressions = eventReader.readImpressions()
    val clicks = eventReader.readClicks()

    val output = metricCalculator.calculateMetrics(impressions, clicks)

    val outputFile = new FileWriter("src/main/scala/outputs/output.json")
    val outputWriter = new java.io.PrintWriter(outputFile)
    try {
      val jsonOutput = Extraction.decompose(output)
      outputFile.write(pretty(jsonOutput))
    } finally {
      outputWriter.close()
    }


  }
}

