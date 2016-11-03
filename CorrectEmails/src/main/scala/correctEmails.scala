package CorrectEmails

object Fun {
  def correctEmails(pattern: String, j: Int = 0): Int = {
    //val subs = List('a','b','c','d','e','@','.')
    val subs = List('a','@','.')
    val rgx = """\A[a-e|?]+(\.?[a-e|?]+)*[@|?][a-e|?]+[.|?][a-e|?]+(\.?[a-e|?]+)*\z""".r
    def validEmail(s: String) = rgx.findFirstIn(s).toArray.length>0
    
    if (validEmail(pattern)) {
      val i = pattern.indexOf('?', j)
      if (i > -1) {
        subs.map(c => 
          (if (c=='a') 5 else 1) * 
          correctEmails(pattern.updated(i,c), i+1)
        ).sum
      } else {
        1
      }
    } else 0
  }
}
