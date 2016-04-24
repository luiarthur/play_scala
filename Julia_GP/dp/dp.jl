using Distributions

function rdir(N,a)
  K = length(a)

  x = zeros(N,K)
  for j in 1:K
    x[:,j] = rand(Gamma(a[j],1),N) # shape and scale
  end
  rowsums = x * ones(K)
  out = x ./ rowsums

  return out
end

x = collect(1:10)
temp1(n) = rdir(n,1:10)
temp2(n) = rand(Dirichlet(x),n)
@time x1 = temp1(100000)
@time x2 = temp2(100000) # BUILT-IN version is faster

mean(x1,1)
mean(x2,2)
size(x2)

g = rand(Gamma(2,6),10000) # shape, scale

fit(Gamma,g)
fit(Dirichlet,x2)


rand(D,3)
