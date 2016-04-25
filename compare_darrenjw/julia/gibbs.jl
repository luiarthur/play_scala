using Distributions

function gibbs(B, thin) 
  x = 0.0
  y = 0.0
  for i in 1:B
    for j in 1:thin
      x = rand(Gamma(3.0, y*y+4))
      y = rand( Normal( 1/(x+1), 1/sqrt(2*x+2) ) )
    end
    println(x,y)
  end
end

@time gibbs(50000,1000)
