#=
  Implement ticta3D in Julia. TCO can be acheived by `using Lazy`.
=#

using Lazy

function logfact(n)
 @bounce function loop(N, acc) 
   N == 0? acc : loop(N-1, log(N) + acc)
 end
 loop(n,0)
end

function logfact_woTCO(n)
 function loop(N, acc) 
   N == 0? acc : loop(N-1, log(N) + acc)
 end
 loop(n,0)
end

#=
  B = 10000000
  logfact(3) == log(6)
  logfact_woTCO(3) == log(6)

  logfact(B)
  logfact_woTCO(B)

  @>> (1:10) map(x -> x*2) filter(iseven)
=#
