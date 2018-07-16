import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.ml.recommendation._
import scala.util.Random
object MoiveLensALS {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Movie_Lens_ALS")
    val sc = new SparkContext(conf)

    val schema = StructType(Array(
     StructField("uid", IntegerType, true),
     StructField("mid", IntegerType, true),
     StructField("rating", DoubleType, true))
    )

    val raw_data = spark.read.format("csv").option("header", "true").schema(schema).load("hdfs:///dataset/ratings.csv")

    val model = new ALS().
       setSeed(Random.nextLong()).
       setImplicitPrefs(true).
       setRank(10).
       setRegParam(0.01).
       setAlpha(1.0).
       setMaxIter(5).
       setUserCol("uid").
       setItemCol("mid").
       setRatingCol("rating").
       setPredictionCol("prediction").
       setNonnegative(true).
       fit(raw_data)
  }
}
