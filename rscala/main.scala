val R = org.ddahl.rscala.callback.RClient()

R.x = Array(.5,1.3,2.5,3.2,4.9) // sends the Scala array to R as "x"
R.eval("x") // doesn't return anything interesting

R.evalD0("x") // returns as Double the value stored at the first index 
R.evalI0("x") // returns as Int the value stored at the first index
R.evalD1("x") // returns as Double the entire array
R.evalI1("x") // returns as Int the entire array

R.eval("y <- cbind(x,x)")
R.evalD2("y") // returns y as as Array of Array of Double. 
R.evalI2("y") // returns y as as Array of Array of Int. 

// Invalid Commands:
R.evalD1("y") // returns error message. D1 cannot be used for returning matrices.
R.evalD2("x") // returns error message. D2 is used to return matrices only.
R.evalI2("x") // returns error message. D2 is used to return matrices only.
