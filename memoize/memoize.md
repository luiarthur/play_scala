# Memoize in Scala

I started thinking about memoizing when I was implementing Dynamic
Linear Models (DLM) for my time series course at UC Santa Cruz.
DLMs make use of recursive definitions. Scala is great for recursion
and functional programming, and I think the language is elegant. 
Naturally, I wanted to write elegant and efficient code for DLMs in
Scala. Recursion can be sped up with memoization. The trade-off is storage
for speed. This led me to learn about memoization. Below is a more
concrete motivation for using memoization.

Observe the slow Fibonacci function below

```scala
val slowfib: Int => BigInt = { // a val function
  // the series of case statements is just an anonymous function
  case 0 => 0
  case 1 => 1
  case n => slowfib(n-1) + slowfib(n-2)
}
```

Note here, `slowfib` is defined as a val (not a def). The type
needs to be explicitly given `Int=>BigInt` since `slowfib`
is defined recursively. The series of case statements in
braces is a representation of an anonymous function. 

You use this function as follows:

```scala
slowfib(10) // returns 55
```

Note that this function will slow down for large arguments.
Memoization basically allows caching of values while computing
intermediates.

This is one way to memoize in Scala.

```scala
import scala.collection.mutable

def memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O] {
  override def apply(key: I): O = getOrElseUpdate(key, f(key))
}

val fib: Int => BigInt = memoize {
  case 0 => 0
  case 1 => 1
  case n => fib(n-1) + fib(n-2)
}

fib(10) // returns 55

```

`memoize` is a generic function that takes in a function and returns a 
mutable map. The mutable map's `apply` method is overridden so as to 
return the lookup value corresponding to an input key, or compute it
using `f`, then store the value in the map, then return it.

`fib` is again a function. It returns the `memoize` function which takes as an 
argument an anonymous function in the form of a series of case statements. 
And then it returns the answer when we apply the resulting function to 
an argument. That is we apply `fib` to an argument `x` by `fib(x)`.

That is `fib(10)` will now return the value we want (55).

Note that this will be faster for larger arguments. But, one should be careful
to start computing fib when it is small. A first call to fib of
`fib(5000)` will result in an error because the call is too recursive. 
(We did not and cannot use tail recursion for fib.) 

On StackOverflow, where I found this, I saw a discussion about thread-safety.
I'll refer you to the page below. But here, I'll provide the thread-safe
implementation of `memoize`:

```scala
def memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O] { self =>
  override def apply(key: I): O = self.synchronized( getOrElseUpdate(key, f(key)) )
}

```


### References

I used [this][1] reference for memoization and [this][2] for Partial Functions in
Scala.

[1]: http://stackoverflow.com/questions/16257378/is-there-a-generic-way-to-memoize-in-scala
[2]: http://blog.bruchez.name/2011/10/scala-partial-functions-without-phd.html
