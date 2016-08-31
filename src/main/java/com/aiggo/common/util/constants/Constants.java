package com.aiggo.common.util.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {

	
	public static byte Magic =(byte) 0xb1;
	
	public static String version ="v1_";
	
	public static String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX9JMSjzTKK6K5SfEMp4nhVK5jeSTlrqheInoAxrDne3BqXRzT78KxtHQsThMrO82c2fYb8IfkBARvHtQ9tbi39DEVc3hwQqKQLtrGP7WY8vI0b8Mq9mPBkOdMcHs9sezJff8nfKKzKhpu0N70SgraQr71+yxFHYgntanmI7tgTQIDAQAB";
	
	public static String privatekey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJf0kxKPNMororlJ8QynieFUrmN5JOWuqF4iegDGsOd7cGpdHNPvwrG0dCxOEys7zZzZ9hvwh+QEBG8e1D21uLf0MRVzeHBCopAu2sY/tZjy8jRvwyr2Y8GQ50xwez2x7Ml9/yd8orMqGm7Q3vRKCtpCvvX7LEUdiCe1qeYju2BNAgMBAAECgYBZzgWNlm0QfrFw6C452Fo4fZ+C0nnn9E01NRL9mm+6ns1VTypWZwCdnZR7Crlxd6qQ5I/6+zCUpXspYQL2ZYuA3QMZWoTOXqIxyY+6ivV6p7qYjD+GubLIiRC0bh7AspAJm92wpfjHcomKz3VScDXEmXwB0b2+nchcQj6FVARVgQJBAPdY23FAE7OdlE+0+nBic8gabKn3nMLzUmmob6RjRPFWpP/f8TQ/zKNaSNWH8ZPVSTtZWtwcRMEGmnL6Huq0iAkCQQCdRW08KH4qSWBdTNgxCjEcALsrV+VHp4Qy8w3613GyphVlq9v49PYn4lmwJiq6mLr9t2+5kc9f7F/m5tM8Pb8lAkAWf22OUn64Qqf/y/1PhZT0aXrixtPqY4lmdNyavjDCJthhMbRVxUvOu+KOEYxmnurHTCxYgvW5/OjFdnyjaoJJAkAr8GeydQW6vsFjj8KfAN/zmOiyFV6DhHPU4D85o9OqO+iifT958MaNIho4spEhjOZBWGyohepQPHQWyMqffAjZAkBQHzJKCI4vlyQqyodFqq0eDFI5t/ZT9bRtTkhorkgiY3tBHKoxJDFjHt/pezONibRCZaQGg2LhN6290rzJ2zUz";
	
	 /**
     * utf8编码
     */
    public static final String CODE_UTF8 = "UTF-8";

    /**
     * MD5编码
     */
    public static final String CODE_MD5 = "MD5";

    /**
     * 竖线分隔符[|]
     */
    public static final String VERTICALLINE = "|";

    /**
     * 逗号分隔符 [,]
     */
    public static final String COMMA = ",";

    /**
     * 斜线分隔符[/]
     */
    public static final String SLASH = "/";

    /**
     * 等号分割符[=]
     */
    public static final String EQUAL = "=";

    /**
     * 竖线分隔符的正则表达式
     */
    public static final String VERTICALLINE_REGEX = "\\|";
    
    /**
     * A-Z的不可变集合
     */
    public static final List<String> UPPER_LETTER = Collections.unmodifiableList(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
}
