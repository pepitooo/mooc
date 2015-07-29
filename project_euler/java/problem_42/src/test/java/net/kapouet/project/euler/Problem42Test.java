package net.kapouet.project.euler;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by pepito on 29/07/2015.
 */
public class Problem42Test {

    private Problem42 pb = new Problem42();
    private List<String> names;

    @Before
    public void setUp() throws Exception {
        names = pb.loadFile();
    }

    @Test
    public void testGetNameValue() throws Exception {
        assertEquals(55, Problem42.getNameValue("SKY"));
    }

    @Test
    public void testTriangleNumber() {
        assertEquals(1, Problem42.getTriangleNumberOf(1));
        assertEquals(3, Problem42.getTriangleNumberOf(2));
        assertEquals(6, Problem42.getTriangleNumberOf(3));
        assertEquals(10, Problem42.getTriangleNumberOf(4));
        assertEquals(15, Problem42.getTriangleNumberOf(5));
        assertEquals(21, Problem42.getTriangleNumberOf(6));
        assertEquals(28, Problem42.getTriangleNumberOf(7));
        assertEquals(36, Problem42.getTriangleNumberOf(8));
        assertEquals(45, Problem42.getTriangleNumberOf(9));
        assertEquals(55, Problem42.getTriangleNumberOf(10));
    }

    @Test
    public void testTriangleListGeneration() {
        List<Long> triangleNumbers = pb.getTriangleNumbersUpTo(56);

        assertArrayEquals(
                new Long[]{1l, 3l, 6l, 10l, 15l, 21l, 28l, 36l, 45l, 55l, 66l},
                triangleNumbers.stream().toArray(Long[]::new));

        triangleNumbers = pb.getTriangleNumbersUpTo(55);

        assertArrayEquals(
                new Long[]{1l, 3l, 6l, 10l, 15l, 21l, 28l, 36l, 45l, 55l},
                triangleNumbers.stream().toArray(Long[]::new));
    }

    @Test
    public void checkSolution() {
        assertEquals(162, pb.getNmbeTriangleWord(names));
    }
}