package de.scandio.atlassian.applib.utils;

/**
 * Compares versions.
 */
public class VersionUtils {

    public static boolean majorVersionIsGreaterOrEqualThan(String version, int greaterOrEqualThan) {
        String majorVersionString = version.contains(".") ?  version.substring(0, version.indexOf(".")) : version;

        try {
            return Integer.parseInt(majorVersionString) >= greaterOrEqualThan;
        } catch (Exception e) {
            return false;
        }
    }
}
