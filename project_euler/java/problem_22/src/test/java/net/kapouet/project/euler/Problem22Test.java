package net.kapouet.project.euler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by pepito on 28/07/2015.
 */
public class Problem22Test {

    private Problem22 pb = new Problem22();
    private List<String> names;

    @Before
    public void setUp() throws Exception {
        names = pb.loadFile();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calculateNameValue() {
        Assert.assertEquals(53, Problem22.getNameValue("COLIN"));
    }

    @Test
    public void checkRightPositionOfColin() throws IOException, URISyntaxException {
        Assert.assertEquals("COLIN", names.get(938));
    }

    @Test
    public void checkNamesListSize() {
        Assert.assertTrue(5000 < names.size());
    }

    @Test
    public void checkSolution() {
        Assert.assertEquals(871198282, pb.getNamesScore(names));
    }
}