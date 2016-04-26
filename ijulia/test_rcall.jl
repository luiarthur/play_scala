using RCall

# This works because "$x" is the string expression of x, which is literally "1:10"
# So, this makes sense in the R interpreter. If X = [1 2 3; 4 5 6], this wouldn't work
# The second and third line are the same
x = 1:10
reval("plot($x,10:1)")
R"plot($x,10:1)"


# Result stored an an ROject, not julia variable
y = R"1+1"


# These two lines are the same. They load the library 'maps' into Julia. 
# Requires that you have installed the library in R.
R"library(maps)"
@rlibrary("maps")
R"map('county')"

# reval, plotting, creating functions, etc.
X = [1 2; 4 5; 7 8]
r_X = RObject(X)

myrplot = reval("function(x,...) plot(x,...)")
myrplot2 = reval("function(x,y=NULL,...) plot(x,y,...)")
rplot = reval("plot")
rapply = reval("apply")

rapply(X,2,mean)
rapply(r_X,2,mean)

rplot(x=randn(100),pch=20,cex=2,fg="grey",bty="n") # WEIRD?! it's the ylab...
rplot(x=randn(100),pch=20,cex=2,fg="grey",bty="n",ylab="",main="Hallelujah!")
rplot(x=randn(100,2),pch=20,cex=2,fg="grey",bty="n") # WEIRD?!
myrplot(reval("rnorm(100)"),pch=20,cex=3)
myrplot2(linspace(100,1,100),1:100,pch=20,cex=3,bty="n")


# lm
b = reshape([2.0, 3.0], 2,1)
X = randn(3,2)
y = X * b + randn(3,1)
@rput y # sends Julia variable "y" to R with the same name ("y")
@rput X
reval(rparse("mod <- lm(y ~ X-1)"))
reval("summary(mod)")

R_lm(some_str_expr) = reval(rparse("lm(" * some_str_expr * ")"))
R_summary = R"summary"
mod = R_lm("y ~ X-1")
R_summary(mod)
