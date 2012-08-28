/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinv;

/**
 *
 * @author Smile42RU
 */
public class Additional {
    static String replace(String str, String pattern, String replace) {
        int start = 0;
        int end = 0;
        StringBuffer result = new StringBuffer();
        while ((end = str.indexOf(pattern, start)) >= 0) {
            result.append(str.substring(start, end));
            result.append(replace);
        start = end+pattern.length();
        }
            result.append(str.substring(start));
        str=result.toString();
        return str;
    } 
}
