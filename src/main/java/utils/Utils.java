package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils<T> {
    @SafeVarargs
    public static <T> List<T> toList(T... input) {
        List<T> list = new ArrayList<>(Arrays.asList(input));
        return list;
    }
}
