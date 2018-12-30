package kiosk;

import Exceptions.NullException;
import data.Party;
import java.util.Set;

/**
 * A class that represents the result in an election site
 */

public class VoteCounter {

   private int votes;
   private int nulls;
   private int blanks;
   private int total;

    public VoteCounter(Set<Party> validParties)
    {

    }

    public void countParty(Party party) { ???}

    public void countNull() { ???}

    public void countBlank() { }

    public void scrutinize(Party party) { ???}

    public int getVotesFor(Party party) {
      return votes;
    }

    public int getNulls() {
      return nulls;
    }

    public int getBlanks() {
      return blanks;
    }

    public int getTotal() {
     return total;
    }
}
