package com.aiggo.common.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @Description: 映射加密 
 * @author: qd-ankang
 * @date: 2016-8-24 下午3:00:58
 */
public class CharMappingUtil {

	private static final byte sourceChar[] = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '|', '.', '-', '+', '/', ',', '!', '"', '#',
			'$', '%', '&', '\'', '(', ')', '*', '{', '}', '~', '[', '\\', ']',
			'^', '_', ':', ';', '<', '=', '>', '?', '@' };

	private static final byte targetChar[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '+', '#', '|', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '@', '$', '=', '"', '/', '!',
			'%', '&', '\'', '(', ')', '*', '-', '.', '{', '}', '~', '[', '\\',
			']', '^', '_', ':', ';', '<', '>', '?', ',' };

	private static final Map<Byte, Byte> encodeMap = new HashMap<Byte, Byte>();
	private static final Map<Byte, Byte> decodeMap = new HashMap<Byte, Byte>();

	static {
		for (int i = 0; i < sourceChar.length; i++) {
			if (encodeMap.containsKey(sourceChar[i])) {
				System.out.println((char) sourceChar[i]);
			}

			if (decodeMap.containsKey(targetChar[i])) {
				System.out.println((char) targetChar[i]);
			}

			encodeMap.put(sourceChar[i], targetChar[i]);
			decodeMap.put(targetChar[i], sourceChar[i]);
		}
	}

	public static String encode(String str) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(str)) {
			return str;
		}

		byte[] source = str.getBytes();
		byte[] target = new byte[source.length];
		for (int i = 0; i < source.length; i++) {
			Byte value = encodeMap.get(source[i]);

			if (value == null) {
				throw new UnsupportedEncodingException("CharMapping encode error, char must in [33, 126]");
			}

			target[i] = value;
		}

		return new String(target);
	}

	public static String decode(String str) throws UnsupportedEncodingException{
		if (StringUtils.isBlank(str)) {
			return str;
		}

		byte[] source = str.getBytes();
		byte[] target = new byte[source.length];
		for (int i = 0; i < source.length; i++) {
			Byte value = decodeMap.get(source[i]);

			if (value == null) {
				throw new UnsupportedEncodingException("CharMapping decode error, char must in [33, 126]");
			}

			target[i] = value;
		}

		return new String(target);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		System.out.println("n7olatrzr6imx8aq");
		String encode = CharMappingUtil.encode("n7olatrzr6imx8aq");
		System.out.println("encode:"+encode);
		System.out.println("decode:"+CharMappingUtil.decode(encode));
	}

}
