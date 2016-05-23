name := "tictac3D"
assemblyJarName in assembly := "tictac3D.jar"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
)

/** Notes:
 *  1. sbt assembly: builds the jar file
 *  2. To play the game, go to the directory with the jar and execute:
 *     java -classpath tictac3D.jar tictac.Main
 */
