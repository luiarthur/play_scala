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

// Scala 2.9.2 is still supported for 0.2.1, but is dropped afterwards.
scalaVersion := "2.11.8"

unmanagedJars in Compile += file("/home/luiarthur/Libs/scala-2.11.8/lib/scala-jvmr.jar")
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
exportJars := true // packages and produces a jar file
