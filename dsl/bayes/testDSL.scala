object myMCMC extends BayesDSL {
  def main(args:Array[String]) {

    Data (
      y: Vec[Double],
      X: Mat[Double]),

    Likelihood (
      Expr{ 'y | 'X, 'b ~ MvNormal('X 'b, 'sig2 'I) }
    )

    Priors {
      Expr{ 'b | 'sig2 ~ MvNormal(0, 'sig2*('X.T 'X)^(-1)) },
      Expr{ 'sig2 ~ IG('a_sig2, 'b_sig2) }
    }

    Init {
      'b = 0,
      'sig2 = 1
    }

    Proposal {
      Expr{ 'b_cand ~ MvNormal('b, 'CS_qb) },
      Expr{ log('sig2_cand) ~ Normal(log('sig2), 'cs_qsig2) }
    }

    Other {
      // update
      // printEvery
      // burn
      // B
      // thin
      // default hyper-parameters
    }

    GenerateCode
  } // end of main
}
