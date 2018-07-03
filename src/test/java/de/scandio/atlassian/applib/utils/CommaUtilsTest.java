package de.scandio.atlassian.applib.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CommaUtilsTest {

    @Test
    public void split() {
        assertEquals(Collections.emptyList(), CommaUtils.split(null));
        assertEquals(Collections.emptyList(), CommaUtils.split(""));
        assertEquals(Collections.singletonList("a"), CommaUtils.split("a"));
        assertEquals(Arrays.asList("a", "b"), CommaUtils.split("a,b"));
    }

    @Test
    public void join() {
        assertNull(CommaUtils.join(null));
        assertNull(CommaUtils.join(Collections.emptyList()));
        assertEquals("a", CommaUtils.join(Collections.singletonList("a")));
        assertEquals("a,b", CommaUtils.join(Arrays.asList("a", "b")));
    }

    @Test
    public void splitMultiple() {
        assertEquals(Collections.emptyList(), CommaUtils.splitMultiple(null));
        assertEquals(Collections.emptyList(), CommaUtils.splitMultiple(""));
        assertEquals(Collections.singletonList(Collections.singletonList("a")), CommaUtils.splitMultiple("a"));
        assertEquals(Collections.singletonList(Arrays.asList("a", "b")), CommaUtils.splitMultiple("a,b"));
        assertEquals(Arrays.asList(Collections.singletonList("a"), Collections.singletonList("b")), CommaUtils.splitMultiple("a\nb"));
        assertEquals(Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d")), CommaUtils.splitMultiple("a,b\nc,d"));
    }

    @Test
    public void joinMultiple() {
        assertNull(CommaUtils.joinMultiple(null));
        assertNull(CommaUtils.joinMultiple(Collections.emptyList()));
        assertNull(CommaUtils.joinMultiple(Collections.singletonList(Collections.emptyList())));
        assertEquals("a", CommaUtils.joinMultiple(Collections.singletonList(Collections.singletonList("a"))));
        assertEquals("a,b", CommaUtils.joinMultiple(Collections.singletonList(Arrays.asList("a", "b"))));
        assertEquals("a\nb", CommaUtils.joinMultiple(Arrays.asList(Collections.singletonList("a"), Collections.singletonList("b"))));
        assertEquals("a,b\nc,d", CommaUtils.joinMultiple(Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d"))));
    }
}