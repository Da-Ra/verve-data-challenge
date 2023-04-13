# Verve Data Engineering challenge

This is a pure Scala project to achieve these two goals:
- Read events stored in JSON files
- Calculate metrics for some dimensions

## Project Structure

- There are two case classes to have a schema and model of inputs:
  - Click.scala
  - Impression.scala

- EventReader.scala:
It has two functions to read the inputs.
As there is one record in `imressions.json` which doesn't comply the schema, we need to handle that (ignore it)

- MetricCalculator.scala: 
  - Calculate following metrics :
    - Count of impressions
    - Count of clicks
    - Sum of revenue

  - with these dimensions:
    - app_id
    - country_code

- Main.scala
It is the start point to call other classes and write the output in a file

## How to Run the Project

To run the project, you need to have sbt installed on your machine. You can follow the instructions on the [sbt website](https://www.scala-sbt.org/) to install and set up sbt.

Once sbt is installed, you can run the project by executing the following command in the project directory:

```shell
sbt compile
sbt run