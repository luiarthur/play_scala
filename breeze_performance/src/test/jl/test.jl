# Julia v0.4.5

function test()
  X = rand(500,500)
  [X'X for i in 1:10000]
end

@time test() # around 50 seconds
