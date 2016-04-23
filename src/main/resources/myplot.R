myplot <- function(n=1,m=0,s=1,...) {
  x <- rnorm(n,m,s)
  plot(x,fg="grey",bty="n",pch=20,col="red",...)
}
