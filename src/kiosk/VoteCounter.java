package kiosk;

import Exceptions.NullPartyException;
import data.Party;

import java.util.Arrays;
import java.util.Set;

/**
 * A class that represents the result in an election site
 */

public class VoteCounter {

   private int nulls;
   private int blanks;
   private int total;
   private Set<Party> validParties;

    public VoteCounter(Set<Party> validParties)
    {
        this.validParties = validParties;
    }

    public void countParty(Party party) throws NullPartyException
    {
        if (party == null)
        {
            throw new NullPartyException("Null Party!");
        }
        getValidParty(party).addVote();
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

    public void scrutinize(Party party) throws NullPartyException
    {
        if (party == null)
        {
            throw new NullPartyException("Null Party!");
        }



        if (validParties.contains(party.getName()))
        {
            countParty(party);
        }
        else if ( party.getName() == "")
        {
            countBlank();
        }
        else
        {

            countNull();
        }
    }

    public int getVotesFor(Party party) {
        Party p = getValidParty(party);
        if(p != null)
            return p.getVotes();
        return 0;
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

    public Party getValidParty(Party party) {
        for(Party p : validParties) {
            if(p.equals(party)) {
                return p;
            }
        }
        return null;
    }
}
