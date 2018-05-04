package org.pg4200.les08.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 04-May-18.
 */
public abstract class TextSearchTestTemplate {

    protected abstract TextSearch getNewInstance();

    protected abstract TextSearch getNewInstance(String token);

    private TextSearch ts;

    @BeforeEach
    public void init(){
        ts = getNewInstance();
    }

    @Test
    public void testTooLong(){
        int res = ts.findFirst("a", "ab");

        assertTrue(res < 0);
    }

    @Test
    public void testSimpleMismatch(){

        int res = ts.findFirst("a", "b");

        assertTrue(res < 0);
    }

    @Test
    public void testSimpleMatch(){

        int res = ts.findFirst("a", "a");

        assertEquals(0, res);
    }

    @Test
    public void testRepetition(){

        int res = ts.findFirst("caabab", "ab");

        assertEquals(2, res);
    }

    @Test
    public void testPartialMatchWithShift(){
        int res = ts.findFirst("aabaabaaaa", "aabaaa");

        assertEquals(3, res);
    }

    @Test
    public void testWorstCaseForBruteForce(){
        int res = ts.findFirst("aaaaaaaaab", "aab");

        assertEquals(7, res);
    }

    @Test
    public void testDefaultToken(){

        TextSearch x = getNewInstance("aacaa");

        int res = x.findFirst("aabraacadabraacaadabra");

        assertEquals(12, res);
    }

    @Test
    public void testSequenceOfCalls(){

        assertEquals(0, ts.findFirst("aba", "a"));
        assertEquals(2, ts.findFirst("ababac", "abac"));
        assertEquals(1, ts.findFirst("cat", "a"));
        assertEquals(7, ts.findFirst("aaaaabcabacaaab", "abac"));
    }
}