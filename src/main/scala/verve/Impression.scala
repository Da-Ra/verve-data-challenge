package verve

case class Impression(id: String = "", app_id: Int = 0, country_code: Option[String], advertiser_id: Option[Int])
