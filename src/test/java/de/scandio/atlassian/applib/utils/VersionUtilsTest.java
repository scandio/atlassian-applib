package de.scandio.atlassian.applib.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VersionUtilsTest {

    @Test
    public void majorVersionIsGreaterOrEqualThan() throws Exception {
        assertTrue(VersionUtils.majorVersionIsGreaterOrEqualThan("3.0.0-m001", 3));
        assertTrue(VersionUtils.majorVersionIsGreaterOrEqualThan("3.0.0", 3));
        assertTrue(VersionUtils.majorVersionIsGreaterOrEqualThan("3.0", 3));
        assertTrue(VersionUtils.majorVersionIsGreaterOrEqualThan("3", 3));
        assertTrue(VersionUtils.majorVersionIsGreaterOrEqualThan("4.0.0-m001", 3));
        assertTrue(VersionUtils.majorVersionIsGreaterOrEqualThan("4.0.0", 3));
        assertTrue(VersionUtils.majorVersionIsGreaterOrEqualThan("4.0", 3));
        assertTrue(VersionUtils.majorVersionIsGreaterOrEqualThan("4", 3));
        assertFalse(VersionUtils.majorVersionIsGreaterOrEqualThan("1.0.0-m001", 3));
        assertFalse(VersionUtils.majorVersionIsGreaterOrEqualThan("1.0.0", 3));
        assertFalse(VersionUtils.majorVersionIsGreaterOrEqualThan("1.0", 3));
        assertFalse(VersionUtils.majorVersionIsGreaterOrEqualThan("1", 3));
    }

}