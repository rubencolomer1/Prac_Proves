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
   private Set<Party> validParties;

    public VoteCounter(Set<Party> validParties)
    {
        this.validParties = validParties;
    }

    public void countParty(Party party)
    {
        this.votes = getVotesFor(party) + 1;
        this.total = getTotal() + 1;
    }

    public void countNull()
    {
        this.nulls = getNulls() + 1;
        this.total = getTotal() + 1;
    }

    public void countBlank()
    {
        this.blanks = getBlanks() + 1;
        this.total = getTotal() + 1;
    }

    public void scrutinize(Party party)
    {
        if (validParties.contains(party.getName()))
        {
            countParty(party);
        }
        else if ( party.equals(""))
        {
            countBlank();
        }
        else
        {
            countNull();
        }
    }

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
