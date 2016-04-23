name := "playing"

libraryDependencies  ++= Seq(
  "org.scalanlp" %% "breeze" % "0.12",
  "org.scalanlp" %% "breeze-natives" % "0.12",
  "org.scalanlp" %% "breeze-viz" % "0.12"
)


resolvers ++= Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)


scalaVersion := "2.11.8"


scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")


exportJars := true // packages and produces a jar file
// need to use assembly.sbt to package all dependencies also...


// These are not in standard repositories
unmanagedJars in Compile += file("/home/luiarthur/Libs/scala-2.11.8/lib/scala-jvmr.jar")
