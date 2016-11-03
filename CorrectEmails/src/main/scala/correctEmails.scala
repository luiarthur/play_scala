package CorrectEmails

object Fun {

  def correctEmails(pattern: String, j: Int = 0): Int = {
    val subs = List('a','@','.')
    val rgx = """\A[a-e|?]+(\.?[a-e|?]+)*[@|?][a-e|?]+[.|?][a-e|?]+(\.?[a-e|?]+)*\z""".r
    def badEmail(s: String) = rgx.findFirstIn(s).isEmpty
    
    if (badEmail(pattern)) 0 else {
      val i = pattern.indexOf('?', j)
      if (i == -1) 1 else {
        subs.map(c => 
          (if (c=='a') 5 else 1) * 
          correctEmails(pattern.updated(i,c), i+1)
        ).sum
      }
    }
  }

}
