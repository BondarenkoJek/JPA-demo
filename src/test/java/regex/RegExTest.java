package regex;


import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {


    @Test
    public void phoneNumberTest() {
        String regex = "^((\\+38)[\\- ]?)?(\\(0\\d{2}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("(097)7109041");
        Assert.assertTrue(matcher.find());

        matcher = pattern.matcher("80977109041");
        Assert.assertFalse(matcher.find());
    }
    @Test
    public void ipTest() {
        String regex = "^(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]?[0-9])\\." +
                "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]?[0-9])\\." +
                "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]?[0-9])\\." +
                "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]?[0-9])$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("192.168.0.1");
        Assert.assertTrue(matcher.find());

        matcher = pattern.matcher("192.168.0.256");
        Assert.assertFalse(matcher.find());
    }
}
