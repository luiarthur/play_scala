package CorrectEmails

object Fun {
  def correctEmails(pattern: String): Int = {
    val subs = List('a','b','c','d','e','@','.')
    val rgx = """\A[a-e|?]+(\.*[a-e|?])*[@|?][a-e|?]+[.|?][a-e|?]+(\.*[a-e|?]+)*\z""".r
    def validEmail(s: String) = rgx.findFirstIn(s).toArray.length>0
    
    if (validEmail(pattern)) {
      val i = pattern.indexOf('?')
      if (i > -1) {
        subs.par.map(c => correctEmails(pattern.updated(i,c))).sum
      } else {
        1
      }
    } else 0
  }
}
