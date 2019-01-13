package com.sys.base.tool;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 */
public class StringUtilParent {
	/**
	 * The empty String {@code ""}.
	 * 
	 * @since 2.0
	 */
	public static final String EMPTY = "";

	/**
	 * Represents a failed index search.
	 * 
	 * @since 2.1
	 */
	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * <p>
	 * The maximum size to which the padding constant(s) can expand.
	 * </p>
	 */
	private static final int PAD_LIMIT = 8192;

	/**
	 * A regex pattern for recognizing blocks of whitespace characters.
	 */
	private static final Pattern WHITESPACE_BLOCK = Pattern.compile("\\s+");

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty ("") and not null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null
	 * @since 3.0 Changed signature from isNotEmpty(String) to
	 *        isNotEmpty(CharSequence)
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return !StringUtil.isEmpty(cs);
	}

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isBlank(String) to
	 *        isBlank(CharSequence)
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || cs.toString().toLowerCase() == "null" || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(cs.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty (""), not null and not whitespace
	 * only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null and
	 *         not whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isNotBlank(String) to
	 *        isNotBlank(CharSequence)
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !StringUtil.isBlank(cs);
	}

	// Trim
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String,
	 * handling {@code null} by returning {@code null}.
	 * </p>
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use {@link #strip(String)}.
	 * </p>
	 * 
	 * <p>
	 * To trim your choice of characters, use the {@link #strip(String, String)}
	 * methods.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed string, {@code null} if null String input
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning {@code null} if the String is empty ("") after the trim or if
	 * it is {@code null}.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use
	 * {@link #stripToNull(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToNull(null)          = null
	 * StringUtils.trimToNull("")            = null
	 * StringUtils.trimToNull("     ")       = null
	 * StringUtils.trimToNull("abc")         = "abc"
	 * StringUtils.trimToNull("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed String, {@code null} if only chars &lt;= 32, empty or
	 *         null String input
	 * @since 2.0
	 */
	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning an empty String ("") if the String is empty ("") after the trim
	 * or if it is {@code null}.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use
	 * {@link #stripToEmpty(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = ""
	 * StringUtils.trimToEmpty("")            = ""
	 * StringUtils.trimToEmpty("     ")       = ""
	 * StringUtils.trimToEmpty("abc")         = "abc"
	 * StringUtils.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed String, or an empty String if {@code null} input
	 * @since 2.0
	 */
	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}

	// Stripping
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Strips whitespace from the start and end of a String.
	 * </p>
	 * 
	 * <p>
	 * This is similar to {@link #trim(String)} but removes whitespace.
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <p>
	 * A {@code null} input String returns {@code null}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.strip(null)     = null
	 * StringUtils.strip("")       = ""
	 * StringUtils.strip("   ")    = ""
	 * StringUtils.strip("abc")    = "abc"
	 * StringUtils.strip("  abc")  = "abc"
	 * StringUtils.strip("abc  ")  = "abc"
	 * StringUtils.strip(" abc ")  = "abc"
	 * StringUtils.strip(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str
	 *            the String to remove whitespace from, may be null
	 * @return the stripped String, {@code null} if null String input
	 */
	public static String strip(String str) {
		return strip(str, null);
	}

	/**
	 * <p>
	 * Strips whitespace from the start and end of a String returning
	 * {@code null} if the String is empty ("") after the strip.
	 * </p>
	 * 
	 * <p>
	 * This is similar to {@link #trimToNull(String)} but removes whitespace.
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripToNull(null)     = null
	 * StringUtils.stripToNull("")       = null
	 * StringUtils.stripToNull("   ")    = null
	 * StringUtils.stripToNull("abc")    = "abc"
	 * StringUtils.stripToNull("  abc")  = "abc"
	 * StringUtils.stripToNull("abc  ")  = "abc"
	 * StringUtils.stripToNull(" abc ")  = "abc"
	 * StringUtils.stripToNull(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be stripped, may be null
	 * @return the stripped String, {@code null} if whitespace, empty or null
	 *         String input
	 * @since 2.0
	 */
	public static String stripToNull(String str) {
		if (str == null) {
			return null;
		}
		str = strip(str, null);
		return str.length() == 0 ? null : str;
	}

	/**
	 * <p>
	 * Strips whitespace from the start and end of a String returning an empty
	 * String if {@code null} input.
	 * </p>
	 * 
	 * <p>
	 * This is similar to {@link #trimToEmpty(String)} but removes whitespace.
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripToEmpty(null)     = ""
	 * StringUtils.stripToEmpty("")       = ""
	 * StringUtils.stripToEmpty("   ")    = ""
	 * StringUtils.stripToEmpty("abc")    = "abc"
	 * StringUtils.stripToEmpty("  abc")  = "abc"
	 * StringUtils.stripToEmpty("abc  ")  = "abc"
	 * StringUtils.stripToEmpty(" abc ")  = "abc"
	 * StringUtils.stripToEmpty(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be stripped, may be null
	 * @return the trimmed String, or an empty String if {@code null} input
	 * @since 2.0
	 */
	public static String stripToEmpty(String str) {
		return str == null ? EMPTY : strip(str, null);
	}

	/**
	 * <p>
	 * Strips any of a set of characters from the start and end of a String.
	 * This is similar to {@link String#trim()} but allows the characters to be
	 * stripped to be controlled.
	 * </p>
	 * 
	 * <p>
	 * A {@code null} input String returns {@code null}. An empty string ("")
	 * input returns the empty string.
	 * </p>
	 * 
	 * <p>
	 * If the stripChars String is {@code null}, whitespace is stripped as
	 * defined by {@link Character#isWhitespace(char)}. Alternatively use
	 * {@link #strip(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.strip(null, *)          = null
	 * StringUtils.strip("", *)            = ""
	 * StringUtils.strip("abc", null)      = "abc"
	 * StringUtils.strip("  abc", null)    = "abc"
	 * StringUtils.strip("abc  ", null)    = "abc"
	 * StringUtils.strip(" abc ", null)    = "abc"
	 * StringUtils.strip("  abcyx", "xyz") = "  abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to remove characters from, may be null
	 * @param stripChars
	 *            the characters to remove, null treated as whitespace
	 * @return the stripped String, {@code null} if null String input
	 */
	public static String strip(String str, String stripChars) {
		if (isEmpty(str)) {
			return str;
		}
		str = stripStart(str, stripChars);
		return stripEnd(str, stripChars);
	}

	/**
	 * <p>
	 * Strips any of a set of characters from the start of a String.
	 * </p>
	 * 
	 * <p>
	 * A {@code null} input String returns {@code null}. An empty string ("")
	 * input returns the empty string.
	 * </p>
	 * 
	 * <p>
	 * If the stripChars String is {@code null}, whitespace is stripped as
	 * defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripStart(null, *)          = null
	 * StringUtils.stripStart("", *)            = ""
	 * StringUtils.stripStart("abc", "")        = "abc"
	 * StringUtils.stripStart("abc", null)      = "abc"
	 * StringUtils.stripStart("  abc", null)    = "abc"
	 * StringUtils.stripStart("abc  ", null)    = "abc  "
	 * StringUtils.stripStart(" abc ", null)    = "abc "
	 * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
	 * </pre>
	 * 
	 * @param str
	 *            the String to remove characters from, may be null
	 * @param stripChars
	 *            the characters to remove, null treated as whitespace
	 * @return the stripped String, {@code null} if null String input
	 */
	public static String stripStart(String str, String stripChars) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		int start = 0;
		if (stripChars == null) {
			while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
				start++;
			}
		} else if (stripChars.length() == 0) {
			return str;
		} else {
			while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND)) {
				start++;
			}
		}
		return str.substring(start);
	}

	/**
	 * <p>
	 * Strips any of a set of characters from the end of a String.
	 * </p>
	 * 
	 * <p>
	 * A {@code null} input String returns {@code null}. An empty string ("")
	 * input returns the empty string.
	 * </p>
	 * 
	 * <p>
	 * If the stripChars String is {@code null}, whitespace is stripped as
	 * defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripEnd(null, *)          = null
	 * StringUtils.stripEnd("", *)            = ""
	 * StringUtils.stripEnd("abc", "")        = "abc"
	 * StringUtils.stripEnd("abc", null)      = "abc"
	 * StringUtils.stripEnd("  abc", null)    = "  abc"
	 * StringUtils.stripEnd("abc  ", null)    = "abc"
	 * StringUtils.stripEnd(" abc ", null)    = " abc"
	 * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
	 * StringUtils.stripEnd("120.00", ".0")   = "12"
	 * </pre>
	 * 
	 * @param str
	 *            the String to remove characters from, may be null
	 * @param stripChars
	 *            the set of characters to remove, null treated as whitespace
	 * @return the stripped String, {@code null} if null String input
	 */
	public static String stripEnd(String str, String stripChars) {
		int end;
		if (str == null || (end = str.length()) == 0) {
			return str;
		}

		if (stripChars == null) {
			while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
				end--;
			}
		} else if (stripChars.length() == 0) {
			return str;
		} else {
			while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND)) {
				end--;
			}
		}
		return str.substring(0, end);
	}

	// StripAll
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Strips whitespace from the start and end of every String in an array.
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <p>
	 * A new array is returned each time, except for length zero. A {@code null}
	 * array will return {@code null}. An empty array will return itself. A
	 * {@code null} array entry will be ignored.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripAll(null)             = null
	 * StringUtils.stripAll([])               = []
	 * StringUtils.stripAll(["abc", "  abc"]) = ["abc", "abc"]
	 * StringUtils.stripAll(["abc  ", null])  = ["abc", null]
	 * </pre>
	 * 
	 * @param strs
	 *            the array to remove whitespace from, may be null
	 * @return the stripped Strings, {@code null} if null array input
	 */
	public static String[] stripAll(String... strs) {
		return stripAll(strs, null);
	}

	/**
	 * <p>
	 * Strips any of a set of characters from the start and end of every String
	 * in an array.
	 * </p>
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
	 * 
	 * <p>
	 * A new array is returned each time, except for length zero. A {@code null}
	 * array will return {@code null}. An empty array will return itself. A
	 * {@code null} array entry will be ignored. A {@code null} stripChars will
	 * strip whitespace as defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripAll(null, *)                = null
	 * StringUtils.stripAll([], *)                  = []
	 * StringUtils.stripAll(["abc", "  abc"], null) = ["abc", "abc"]
	 * StringUtils.stripAll(["abc  ", null], null)  = ["abc", null]
	 * StringUtils.stripAll(["abc  ", null], "yz")  = ["abc  ", null]
	 * StringUtils.stripAll(["yabcz", null], "yz")  = ["abc", null]
	 * </pre>
	 * 
	 * @param strs
	 *            the array to remove characters from, may be null
	 * @param stripChars
	 *            the characters to remove, null treated as whitespace
	 * @return the stripped Strings, {@code null} if null array input
	 */
	public static String[] stripAll(String[] strs, String stripChars) {
		int strsLen;
		if (strs == null || (strsLen = strs.length) == 0) {
			return strs;
		}
		String[] newArr = new String[strsLen];
		for (int i = 0; i < strsLen; i++) {
			newArr[i] = strip(strs[i], stripChars);
		}
		return newArr;
	}

	/**
	 * <p>
	 * Removes diacritics (~= accents) from a string. The case will not be
	 * altered.
	 * </p>
	 * <p>
	 * For instance, '&agrave;' will be replaced by 'a'.
	 * </p>
	 * <p>
	 * Note that ligatures will be left as is.
	 * </p>
	 * 
	 * <p>
	 * This method will use the first available implementation of: Java 6's
	 * {@link java.text.Normalizer}, Java 1.3&ndash;1.5's
	 * {@code sun.text.Normalizer}
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.stripAccents(null)                = null
	 * StringUtils.stripAccents("")                  = ""
	 * StringUtils.stripAccents("control")           = "control"
	 * StringUtils.stripAccents("&eacute;clair")     = "eclair"
	 * </pre>
	 * 
	 * @param input
	 *            String to be stripped
	 * @return input text with diacritics removed
	 * 
	 * @since 3.0
	 */
	// See also Lucene's ASCIIFoldingFilter (Lucene 2.9) that replaces accented
	// characters by their unaccented equivalent (and uncommitted bug fix:
	// https://issues.apache.org/jira/browse/LUCENE-1343?focusedCommentId=12858907&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel#action_12858907).
	public static String stripAccents(String input) {
		if (input == null) {
			return null;
		}
		try {
			String result = null;
			if (java6Available) {
				result = removeAccentsJava6(input);
			} else if (sunAvailable) {
				result = removeAccentsSUN(input);
			} else {
				throw new UnsupportedOperationException("The stripAccents(CharSequence) method requires at least Java 1.6 or a Sun JVM");
			}
			// Note that none of the above methods correctly remove ligatures...
			return result;
		} catch (IllegalArgumentException iae) {
			throw new RuntimeException("IllegalArgumentException occurred", iae);
		} catch (IllegalAccessException iae) {
			throw new RuntimeException("IllegalAccessException occurred", iae);
		} catch (InvocationTargetException ite) {
			throw new RuntimeException("InvocationTargetException occurred", ite);
		} catch (SecurityException se) {
			throw new RuntimeException("SecurityException occurred", se);
		}
	}

	/**
	 * Use {@code java.text.Normalizer#normalize(CharSequence, Normalizer.Form)}
	 * (but be careful, this class exists in Java 1.3, with an entirely
	 * different meaning!)
	 * 
	 * @param text
	 *            the text to be processed
	 * @return the processed string
	 * @throws IllegalAccessException
	 *             may be thrown by a reflection call
	 * @throws InvocationTargetException
	 *             if a reflection call throws an exception
	 * @throws IllegalStateException
	 *             if the {@code Normalizer} class is not available
	 */
	private static String removeAccentsJava6(CharSequence text) throws IllegalAccessException, InvocationTargetException {
		/*
		 * String decomposed = java.text.Normalizer.normalize(CharSequence,
		 * Normalizer.Form.NFD); return
		 * java6Pattern.matcher(decomposed).replaceAll("");//$NON-NLS-1$
		 */
		if (!java6Available || java6NormalizerFormNFD == null) {
			throw new IllegalStateException("java.text.Normalizer is not available");
		}
		String result;
		result = (String) java6NormalizeMethod.invoke(null, new Object[] { text, java6NormalizerFormNFD });
		result = java6Pattern.matcher(result).replaceAll("");//$NON-NLS-1$
		return result;
	}

	/**
	 * Use {@code sun.text.Normalizer#decompose(String, boolean, int)}
	 * 
	 * @param text
	 *            the text to be processed
	 * @return the processed string
	 * @throws IllegalAccessException
	 *             may be thrown by a reflection call
	 * @throws InvocationTargetException
	 *             if a reflection call throws an exception
	 * @throws IllegalStateException
	 *             if the {@code Normalizer} class is not available
	 */
	private static String removeAccentsSUN(CharSequence text) throws IllegalAccessException, InvocationTargetException {
		/*
		 * String decomposed = sun.text.Normalizer.decompose(text, false, 0);
		 * return sunPattern.matcher(decomposed).replaceAll("");//$NON-NLS-1$
		 */
		if (!sunAvailable) {
			throw new IllegalStateException("sun.text.Normalizer is not available");
		}
		String result;
		result = (String) sunDecomposeMethod.invoke(null, new Object[] { text, Boolean.FALSE, Integer.valueOf(0) });
		result = sunPattern.matcher(result).replaceAll("");//$NON-NLS-1$
		return result;
	}

	// SUN internal, Java 1.3 -> Java 5
	private static boolean sunAvailable = false;
	private static Method sunDecomposeMethod = null;
	private static final Pattern sunPattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");//$NON-NLS-1$
	// Java 6+
	private static boolean java6Available = false;
	private static Method java6NormalizeMethod = null;
	private static Object java6NormalizerFormNFD = null;
	private static final Pattern java6Pattern = sunPattern;

	static {
		try {
			// java.text.Normalizer.normalize(CharSequence,
			// Normalizer.Form.NFD);
			// Be careful not to get Java 1.3 java.text.Normalizer!
			Class<?> normalizerFormClass = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer$Form");//$NON-NLS-1$
			java6NormalizerFormNFD = normalizerFormClass.getField("NFD").get(null);//$NON-NLS-1$
			Class<?> normalizerClass = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer");//$NON-NLS-1$
			java6NormalizeMethod = normalizerClass.getMethod("normalize", new Class[] { CharSequence.class, normalizerFormClass });//$NON-NLS-1$
			java6Available = true;
		} catch (ClassNotFoundException e) {
			java6Available = false;
		} catch (NoSuchFieldException e) {
			java6Available = false;
		} catch (IllegalAccessException e) {
			java6Available = false;
		} catch (NoSuchMethodException e) {
			java6Available = false;
		}

		try {
			// sun.text.Normalizer.decompose(text, false, 0);
			Class<?> normalizerClass = Thread.currentThread().getContextClassLoader().loadClass("sun.text.Normalizer");//$NON-NLS-1$
			sunDecomposeMethod = normalizerClass.getMethod("decompose", new Class[] { String.class, Boolean.TYPE, Integer.TYPE });//$NON-NLS-1$
			sunAvailable = true;
		} catch (ClassNotFoundException e) {
			sunAvailable = false;
		} catch (NoSuchMethodException e) {
			sunAvailable = false;
		}
	}

	// Equals
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Compares two CharSequences, returning {@code true} if they are equal.
	 * </p>
	 * 
	 * <p>
	 * {@code null}s are handled without exceptions. Two {@code null} references
	 * are considered to be equal. The comparison is case sensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.equals(null, null)   = true
	 * StringUtils.equals(null, "abc")  = false
	 * StringUtils.equals("abc", null)  = false
	 * StringUtils.equals("abc", "abc") = true
	 * StringUtils.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @see java.lang.String#equals(Object)
	 * @param cs1
	 *            the first CharSequence, may be null
	 * @param cs2
	 *            the second CharSequence, may be null
	 * @return {@code true} if the CharSequences are equal, case sensitive, or
	 *         both {@code null}
	 * @since 3.0 Changed signature from equals(String, String) to
	 *        equals(CharSequence, CharSequence)
	 */
	public static boolean equals(CharSequence cs1, CharSequence cs2) {
		return cs1 == null ? cs2 == null : cs1.equals(cs2);
	}

	/**
	 * 半角转全角
	 * 
	 * @param input
	 * @return
	 */
	public static String toSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * 转半角
	 * 
	 * @param input
	 * @return
	 */
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 在目标字符串的左侧，使用指定的字符，填充指定的位数 如：placeholder:#; str:1; bitNum:8.
	 * 那么调用此函数后，返回的结果是#######1
	 * 
	 * @param placeholder
	 * @param str
	 * @param bitNum
	 * @return
	 */
	public static String fillBitsWithPlaceholder(String placeholder, String str, int bitNum) {
		if (str == null)
			throw new IllegalArgumentException("str must not be null");
		if (placeholder == null || bitNum <= 0)
			return str;
		if (str.length() < bitNum) {
			StringBuffer buffer = new StringBuffer();
			for (int index = str.length(); index < bitNum; index++) {
				buffer.append(placeholder);
			}
			buffer.append(str);
			return buffer.toString();
		}
		return str;
	}

	/**
	 * 将两个整型拼接成一个字符串 如：addend = 1, summand = 2. 结果为：12
	 * 
	 * @param addend
	 * @param summand
	 * @return
	 */
	public static String spliceIntegerToString(Integer addend, Integer summand) {
		if (addend == null || summand == null)
			throw new IllegalArgumentException("addend and summand arguments must not be null");
		return String.valueOf(addend) + String.valueOf(summand);
	}

	/**
	 * 如果字符串是<code>null</code>，则返回空字符串<code>""</code>，否则返回字符串本身。
	 * 
	 * <pre>
	 * StringUtil.defaultIfNull(null)  = ""
	 * StringUtil.defaultIfNull("")    = ""
	 * StringUtil.defaultIfNull("  ")  = "  "
	 * StringUtil.defaultIfNull("bat") = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 字符串本身或空字符串<code>""</code>
	 */
	public static String defaultIfNull(String str) {
		return str != null ? str : "";
	}

	/**
	 * 把字符串右填充成固定长度(长度按字节数计算)
	 * 
	 * @param str
	 *            待填充的字符串
	 * @param size
	 *            填充后的长度
	 * @param padChar
	 *            用来填充的字符(因为要按字节填充，只能是char)
	 * @param encoding
	 *            计算原字符串长度的编码方式
	 * @return
	 */
	public static String rightPadWithBytes(String str, int size, char padChar, String encoding) {
		return padWithBytes(str, size, padChar, encoding, false);
	}

	/**
	 * 把字符串左填充成固定长度(长度按字节数计算)
	 * 
	 * @param str
	 *            待填充的字符串
	 * @param size
	 *            填充后的长度
	 * @param padChar
	 *            用来填充的字符(因为要按字节填充，只能是char)
	 * @param encoding
	 *            计算原字符串长度的编码方式
	 * @return
	 */
	public static String leftPadWithBytes(String str, int size, char padChar, String encoding) {
		return padWithBytes(str, size, padChar, encoding, true);
	}

	private static String padWithBytes(String str, int size, char padChar, String encoding, boolean isLeft) {
		if (str == null) {
			return null;
		}
		int strLen;
		try {
			strLen = str.getBytes(encoding).length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncoding:" + encoding, e);
		}

		int pads = size - strLen;
		if (pads <= 0) {
			return str;
		}

		char[] padChars = new char[pads];
		for (int i = 0; i < padChars.length; i++) {
			padChars[i] = padChar;
		}
		if (isLeft) {
			return new String(padChars) + str;
		} else {
			return str + new String(padChars);
		}
	}

	/**
	 * 根据指定的字符替换指定位置字符串
	 * 
	 * @param beginLength
	 *            字符串前段保留长度
	 * @param endLength
	 *            字符串后段保留长度
	 * @param replaceChar
	 *            要替换的字符
	 * @param srcStr
	 *            源字符串
	 * @return
	 */
	public static String replaceStringByChar(int beginLength, int endLength, char replaceChar, String srcStr) {
		if (isBlank(srcStr)) {
			return null;
		}
		if (beginLength < 0) {
			beginLength = 0;
		}
		if (endLength < 0) {
			endLength = 0;
		}
		StringBuffer returnStr = new StringBuffer();
		int srcLength = srcStr.length();
		int replaceLength = srcLength - (beginLength + endLength);
		if (srcLength < beginLength || srcLength < endLength || replaceLength < 0) {
			for (int i = 0; i < srcLength; i++) {
				returnStr.append(replaceChar);
			}
			return returnStr.toString();
		} else {
			returnStr.append(srcStr.substring(0, beginLength));
			for (int i = 0; i < replaceLength; i++) {
				returnStr.append(replaceChar);
			}
			returnStr.append(srcStr.substring(srcStr.length() - endLength));
			return returnStr.toString();
		}
	}

}
