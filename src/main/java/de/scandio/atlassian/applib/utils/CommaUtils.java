package de.scandio.atlassian.applib.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Joins and splits comma separated lists.
 */
public class CommaUtils {

    public static List<String> split(String commaSeparatedString) {
        List<String> list;

        if (commaSeparatedString == null || commaSeparatedString.isEmpty()) {
            list = Collections.emptyList();
        } else {
            list = Arrays.stream(StringUtils.split(commaSeparatedString, ","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        return list;
    }

    public static String join(List<String> list) {
        String commaSeparatedString;

        if (list == null || list.isEmpty()) {
            commaSeparatedString = null;
        } else {
            commaSeparatedString = StringUtils.join(list
                    .stream()
                    .map(String::trim)
                    .collect(Collectors.toList()), ",");
        }
        return commaSeparatedString;
    }

    public static String clean(String commaSeparatedString) {
        return join(split(commaSeparatedString));
    }

    public static List<List<String>> splitMultiple(String newLineAndCommaSeparatedString) {
        List<List<String>> listOfLists;

        if (newLineAndCommaSeparatedString == null || newLineAndCommaSeparatedString.isEmpty()) {
            listOfLists = Collections.emptyList();
        } else {
            listOfLists = Arrays.stream(StringUtils.split(newLineAndCommaSeparatedString, "\n"))
                    .map(commaSeparatedString -> new ArrayList<>(Arrays.stream(StringUtils.split(commaSeparatedString, ","))
                            .map(String::trim)
                            .collect(Collectors.toList())))
                    .filter(CollectionUtils::isNotEmpty)
                    .collect(Collectors.toList());
        }
        return listOfLists;
    }

    public static String joinMultiple(List<List<String>> listOfLists) {
        String newLineAndCommaSeparatedString;

        if (listOfLists == null) {
            newLineAndCommaSeparatedString = null;
        } else {
            listOfLists = listOfLists
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(CollectionUtils::isNotEmpty)
                    .collect(Collectors.toList());
            if (listOfLists.isEmpty()) {
                newLineAndCommaSeparatedString = null;
            } else {
                newLineAndCommaSeparatedString = StringUtils.join(listOfLists
                        .stream()
                        .map(list -> StringUtils.join(list
                                .stream()
                                .map(String::trim)
                                .collect(Collectors.toList()), ","))
                        .collect(Collectors.toList()), "\n");
            }
        }
        return newLineAndCommaSeparatedString;
    }

    public static String cleanMultiple(String newLineAndCommaSeparatedString) {
        return joinMultiple(splitMultiple(newLineAndCommaSeparatedString));
    }
}
