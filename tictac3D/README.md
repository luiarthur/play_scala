# 3D Tic-Tac-Toe in Scala

1. Build the game
  - In the tictac3D directory, run `sbt assembly` in the terminal. This will produce the jar file `tictac3D.jar`
2. To play the game, go to the directory with the jar and in the terminal execute:
   `java -classpath tictac3D.jar tictac.Main`

Here is a link to the compiled jar file called [`tictac3D.jar`][drive]. In the google drive directory, there are
other implementations of tic-tac-toe 3D. In the `bentictac` directory, there is an implementation which uses hard
coded searches for moves that lead to wins, and moves that lead to forks which lead to wins. My implementation 
for 1000 simulations (see below) is superior to that implementation in the sense that it has not lost to it.

The algorithm I use for play tic-tac-toe is simply 

1. At each move, simulate 10<sup>i</sup> games where i is the difficulty level (1-3). (For the remainder of this doc, we assume that the number of simulations is 1000. i.e. the difficulty is set to 3.)

2. The computer picks the move that maximizes the probability of winning (based on the simulations).

That's it. The code is relatively short. The speed depends on your machine. But
for Difficulty of 3, it shouldn't take more than a minute. (What do you
expect?)



[drive]: https://drive.google.com/open?id=0B7Ccueiur0BNbllhVExLTEExME0
