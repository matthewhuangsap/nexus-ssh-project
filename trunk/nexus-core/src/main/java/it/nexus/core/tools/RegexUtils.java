package it.nexus.core.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-11
 * Time: 14:58:24
 * To change this template use File | Settings | File Templates.
 */
public final class RegexUtils {

    public static boolean match(String str, String reg) {
         Pattern pattern =Pattern.compile(reg);
         Matcher matcher=pattern.matcher(str);
        return matcher.matches();
    }
}
