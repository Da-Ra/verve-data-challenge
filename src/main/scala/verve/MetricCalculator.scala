package verve

class MetricCalculator {
  def calculateMetrics(impressions: List[Impression], clicks: List[Click]): List[Map[String,Any]] = {
    val metrics = collection.mutable.Map.empty[(Int, Option[String]), (Int, Int, Double)]

    for (click <- clicks) {
      impressions.find(_.id == click.impression_id) match {
        case Some(impression) =>
          val key = (impression.app_id, impression.country_code)
          val (impressionsCount, clicksCount, revenueSum) = metrics.getOrElse(key, (0, 0, 0.0))
          metrics(key) = (impressionsCount, clicksCount + 1, revenueSum + click.revenue)
        case None =>
          // Handle missing impression event
      }
    }

    for (impression <- impressions) {
      val key = (impression.app_id, impression.country_code)
      val (impressionsCount, clicksCount, revenueSum) = metrics.getOrElse(key, (0, 0, 0.0))
      metrics(key) = (impressionsCount + 1, clicksCount, revenueSum)
    }

    metrics.toList.flatMap {
      case ((app_id, country_code), (impressionsCount, clicksCount, revenueSum)) =>
        val country = country_code.getOrElse("")
        Seq(
          Map(
          "app_id" -> app_id,
          "country_code" -> country,
          "impressions" -> impressionsCount,
          "clicks" -> clicksCount,
          "revenue" -> revenueSum
          )
        )
    }
  }
}

