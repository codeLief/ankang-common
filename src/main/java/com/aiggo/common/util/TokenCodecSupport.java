/**
 * 
 */
package com.aiggo.common.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.aiggo.common.util.constants.Constants;
import com.aiggo.common.util.exception.AuthException;

/**
 * 
 * 项目名称：auth-core 类名称：CookieCodecSupport 类描述： * token的组成： a. 版本（3个char） |
 * (密文信息（可变） 密文信息 | 数字签名（非对称加密 128个字节）)base64 b. 密文信息包括(对称加密)： Magic
 * Byte：0xb1（1）| * 有效信息载荷（变长） 有效信息载荷 c. 有效信息载荷（变长） 有效信息载荷 : key-value对
 * key-value对结构： key长度（1） |key(变长) ||value长度（1）|value（变长） 创建人：lijun
 * 创建时间：2015年3月27日 下午4:54:38 修改人：Administrator 修改时间：2015年3月27日 下午4:54:38 修改备注：
 * 
 * @version
 * 
 */
public class TokenCodecSupport {
	/**
	 * 
	 * @param map
	 * @return 由传入的map生成token，注意map的key和value必须是String类型的！
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	@SuppressWarnings("deprecation")
	public static String generateToken(Map<String, String> map)
			throws Exception {
		// 1 获取对称加密字节组
		byte[] datas = MapSerializeUtil.ConvertMap2Bytes(map);
		byte[] dest = new byte[datas.length + 1];
		System.arraycopy(datas, 0, dest, 1, datas.length);
		dest[0] = Constants.Magic;
		byte[] encryptDatas = DESUtil.encrypt(dest);

		// 2. 获取md5值对应的数字签名字节组
		String encCode = new String(encryptDatas);
		String md5 = MD5Util.md5(encCode);
		// System.out.println("md5:"+md5);
		byte[] digestbytes = RSAUtils.encryptByPrivateKey(md5.getBytes(),
				Constants.privatekey); // 128个字节数字签名
		// System.out.println("encrypt length:"+digestbytes.length);

		// 3. 获取版本号字节组
		String version = Constants.version;

		byte[] allbytes = new byte[encryptDatas.length + digestbytes.length];
		System.arraycopy(encryptDatas, 0, allbytes, 0, encryptDatas.length);
		System.arraycopy(digestbytes, 0, allbytes, encryptDatas.length,
				digestbytes.length);

		// 4. 字节组转base64
		String token = version + RSAUtils.base64Encode(allbytes);
		// System.out.println("base64 length:"+token.length());

		// 5 urlEncode 安全的base64
		token = prepareAfterBase64Encode(token);

		token = java.net.URLEncoder.encode(token, "UTF-8");
		return token;

	}

	/**
	 * 解析token生成map
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseToken(String token) throws Exception {
		// 1 判断版本号
		if (token == null || token.length() == 0) {
			throw new AuthException("token is null!");
		}

		token = java.net.URLDecoder.decode(token, "UTF-8");
		String version = token.substring(0, 3);
		if (!version.equals(Constants.version)) {
			throw new AuthException("token version is wrong!");
		}

		// 2 .base64转字节组
		token = prepareBeforeBase64Decode(token);
		byte[] allbytes = RSAUtils.decodeBase64(token.substring(3));

		// 3 128位数字签名
		byte[] digestbytes = new byte[128];
		System.arraycopy(allbytes, allbytes.length - 128, digestbytes, 0, 128);
		// 4.密文
		byte[] mapbytes = new byte[allbytes.length - 128];
		System.arraycopy(allbytes, 0, mapbytes, 0, allbytes.length - 128);
		String encCode = new String(mapbytes);

		// 计算md5
		String md5 = MD5Util.md5(encCode);
		// 根据数字签名反解md5值
		byte[] encMd5 = RSAUtils.decryptByPublicKey(digestbytes,
				Constants.publickey);// 数字签名
		String newFixedMd5 = new String(encMd5);
		if (!newFixedMd5.equals(md5)) {
			throw new Exception("token digest is wrong!");
		}

		byte[] decCode = DESUtil.decrypt(encCode.getBytes());

		if (decCode[0] != Constants.Magic) {
			throw new Exception("token magic is wrong!");
		}

		byte[] decData = new byte[decCode.length - 1];
		System.arraycopy(decCode, 1, decData, 0, decCode.length - 1);
		Map<String, String> map = MapSerializeUtil.ConvertBytes2Map(decData);
		return map;
	}

	/**
	 * The encodeBase64 algorithm update so we need a prepare method before
	 * decodeBase64
	 * 
	 * @param String
	 *            sub
	 */
	private static String prepareBeforeBase64Decode(String Token) {
		// decode的预处理:url安全的Base64编码,-替换成+,_替换成/,.替换成=
		Token = Token.replace("-", "+").replace("_", "/").replace(".", "=");
		return Token;
	}

	/**
	 * The encodeBase64 algorithm update so we need a prepare method after
	 * Base64Encode
	 * 
	 * @param Token
	 * @return
	 */
	private static String prepareAfterBase64Encode(String Token) {
		// decode的预处理:url安全的Base64编码,-替换成+,_替换成/,.替换成=
		Token = Token.replace("+", "-").replace("/", "_").replace("=", ".");
		return Token;
	}

}
