# Memoize in Scala

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

// specifically, it returns a hashmap (which has type I=>O)
// memoize(f)(key) will return the lookup value of the hashmap (if it exists) 
// or compute it with f (if it doesn't already exist).
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

`memoize` is a partial function that takes a function of arity one and returns
a new partial function. Specifically, a mutable hashmap is returned.
Note these functions all have type `I => O`. For partial functions,
an `apply` method is defined to dictate what applying an argument would do.
In this case, the partial function has type mutable hashmap, so the 
apply method is already defined, but it can be overridden. The return value
given an argument is not the lookup value at the key, or is computed on the
fly using `f`.

`fib` is again a function. It returns the `memoize` which takes as an argument
the anonymous function. And then it returns the answer when we apply an argument.

That is `fib(10)` will now return the value we want.

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
