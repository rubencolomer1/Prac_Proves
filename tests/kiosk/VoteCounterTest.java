package kiosk;

import Exceptions.NullException;
import Exceptions.NullPartyException;
import data.Party;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VoteCounterTest
{
    private Party partit1, partit2, partit3;
    private Set<Party> validParties;
    private VoteCounter v1;

    @BeforeEach
    void init() throws NullException
    {
        validParties = new HashSet<>();
        v1 = new VoteCounter(validParties);
        partit1 = new Party("partit1");
        partit2 = new Party("partit2");
        partit3 = new Party("partit3");
        validParties.add(partit1);
        validParties.add(partit2);
    }


    @Test
    void testCountParty() throws NullPartyException {
        v1.countParty(partit1);
        assertTrue(v1.getTotal() == 1 && partit1.getVotes() == 1);
    }

    @Test
    void testCountNull()
    {
        v1.countNull();
        assertTrue(v1.getNulls() == 1 && v1.getTotal() == 1);
    }

    @Test
    void testCountBlank()
    {
        v1.countBlank();
        assertTrue(v1.getBlanks() == 1 && v1.getTotal() == 1);
    }

    @Test
    void testScrutinize() throws NullException, NullPartyException {

        Party prova = new Party("hola");
        Party prova2 = new Party("");
        v1.scrutinize(prova);
        v1.scrutinize(prova2);
        v1.scrutinize(partit1);
        v1.scrutinize(partit1);
        v1.scrutinize(partit2);

        assertTrue(v1.getTotal() == 5);
        assertEquals(1, v1.getNulls());
        assertTrue(v1.getVotesFor(partit1) == 2);
        assertTrue(v1.getVotesFor(partit2) == 1);
        assertTrue(v1.getVotesFor(partit2) == 1);
        assertTrue(v1.getBlanks() == 1);

    }

    @Test
    void testGetVotesFor() {
    }

    @Test
    void testGetNulls() throws NullPartyException {
        v1.countNull();
        v1.countNull();
        v1.countBlank();
        v1.scrutinize(partit1);
        assertTrue(v1.getNulls() == 2);
        v1.countNull();
        v1.scrutinize(partit3);
        assertTrue(v1.getNulls() == 4);

    }

    @Test
    void testGetBlanks()
    {
        v1.countBlank();
        v1.countBlank();
        assertTrue(v1.getBlanks() == 2);
        v1.countNull();
        v1.countBlank();
        assertTrue(v1.getBlanks() == 3);
    }

    @Test
    void testGetTotal() throws NullPartyException {
        v1.countNull();
        v1.countNull();
        v1.countBlank();
        v1.scrutinize(partit1);
        assertTrue(v1.getTotal() == 4);
        v1.countNull();
        assertTrue(v1.getTotal() == 5);
    }
}