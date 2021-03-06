package uk.co.harcourtprogramming.docitten.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Utility class for decoding HTML character entities to unicode character
 * points</p>
 *
 * @author Benedict Harcourt / javajawa
 */
public final class HtmlEntities
{

	/**
	 * <p>Map of the named entities to their unicode character points</p>
	 */
	private final static Map<String, Character> mappings =
			new HashMap<String, Character>();

	/**
	 * <p>Pattern that matches all forms of an XML/HTML character entity</p>
	 */
	private final static Pattern numbericCodePointPattern =
			Pattern.compile("&(#?)([0-9a-z]+);", Pattern.CASE_INSENSITIVE);

	/**
	 * <p>Private constructor for utility class</p>
	 */
	private HtmlEntities()
	{
		// Nothing to see here. Move along, citizen!
	}

	/**
	 * <p>Replaces HTML entities in a string with their unicode character
	 * equivalents</p>
	 * @param data the string with entities
	 * @return the decoding string
	 */
	public static String decode(final String data)
	{
		final Matcher numericMatcher = numbericCodePointPattern.matcher(data);
		final StringBuffer result = new StringBuffer();

		while (numericMatcher.find())
		{
			String entity = numericMatcher.group(2);

			if (numericMatcher.group(1).equalsIgnoreCase("#")) // Numeric entity
			{
				final int radix;
				if (entity.startsWith("x"))
				{
					entity = entity.substring(1);
					radix = 16;
				}
				else
				{
					radix = 10;
				}

				try
				{
					int uniChar = Integer.parseInt(entity, radix);
					numericMatcher.appendReplacement(result, Character.toString((char)uniChar));
				}
				catch (NumberFormatException ex)
				{
					numericMatcher.appendReplacement(result, numericMatcher.group(0));
				}
			}
			else // HTML named entity
			{
				if (mappings.containsKey(entity))
				{
					entity = mappings.get(entity).toString();
					numericMatcher.appendReplacement(result, entity);
				}
				else
				{
					numericMatcher.appendReplacement(result, numericMatcher.group(0));
				}
			}
		}
		numericMatcher.appendTail(result);
		return result.toString();
	}

	/**
	 * Initialises the map
	 */
	static
	{
		mappings.put("quot", '\u0022');
		mappings.put("amp", '\u0026');
		mappings.put("apos", '\'');
		mappings.put("lt", '\u003C');
		mappings.put("gt", '\u003E');
		mappings.put("nbsp", '\u00A0');
		mappings.put("iexcl", '\u00A1');
		mappings.put("cent", '\u00A2');
		mappings.put("pound", '\u00A3');
		mappings.put("curren", '\u00A4');
		mappings.put("yen", '\u00A5');
		mappings.put("brvbar", '\u00A6');
		mappings.put("sect", '\u00A7');
		mappings.put("uml", '\u00A8');
		mappings.put("copy", '\u00A9');
		mappings.put("ordf", '\u00AA');
		mappings.put("laquo", '\u00AB');
		mappings.put("not", '\u00AC');
		mappings.put("shy", '\u00AD');
		mappings.put("reg", '\u00AE');
		mappings.put("macr", '\u00AF');
		mappings.put("deg", '\u00B0');
		mappings.put("plusmn", '\u00B1');
		mappings.put("sup2", '\u00B2');
		mappings.put("sup3", '\u00B3');
		mappings.put("acute", '\u00B4');
		mappings.put("micro", '\u00B5');
		mappings.put("para", '\u00B6');
		mappings.put("middot", '\u00B7');
		mappings.put("cedil", '\u00B8');
		mappings.put("sup1", '\u00B9');
		mappings.put("ordm", '\u00BA');
		mappings.put("raquo", '\u00BB');
		mappings.put("frac14", '\u00BC');
		mappings.put("frac12", '\u00BD');
		mappings.put("frac34", '\u00BE');
		mappings.put("iquest", '\u00BF');
		mappings.put("Agrave", '\u00C0');
		mappings.put("Aacute", '\u00C1');
		mappings.put("Acirc", '\u00C2');
		mappings.put("Atilde", '\u00C3');
		mappings.put("Auml", '\u00C4');
		mappings.put("Aring", '\u00C5');
		mappings.put("AElig", '\u00C6');
		mappings.put("Ccedil", '\u00C7');
		mappings.put("Egrave", '\u00C8');
		mappings.put("Eacute", '\u00C9');
		mappings.put("Ecirc", '\u00CA');
		mappings.put("Euml", '\u00CB');
		mappings.put("Igrave", '\u00CC');
		mappings.put("Iacute", '\u00CD');
		mappings.put("Icirc", '\u00CE');
		mappings.put("Iuml", '\u00CF');
		mappings.put("ETH", '\u00D0');
		mappings.put("Ntilde", '\u00D1');
		mappings.put("Ograve", '\u00D2');
		mappings.put("Oacute", '\u00D3');
		mappings.put("Ocirc", '\u00D4');
		mappings.put("Otilde", '\u00D5');
		mappings.put("Ouml", '\u00D6');
		mappings.put("times", '\u00D7');
		mappings.put("Oslash", '\u00D8');
		mappings.put("Ugrave", '\u00D9');
		mappings.put("Uacute", '\u00DA');
		mappings.put("Ucirc", '\u00DB');
		mappings.put("Uuml", '\u00DC');
		mappings.put("Yacute", '\u00DD');
		mappings.put("THORN", '\u00DE');
		mappings.put("szlig", '\u00DF');
		mappings.put("agrave", '\u00E0');
		mappings.put("aacute", '\u00E1');
		mappings.put("acirc", '\u00E2');
		mappings.put("atilde", '\u00E3');
		mappings.put("auml", '\u00E4');
		mappings.put("aring", '\u00E5');
		mappings.put("aelig", '\u00E6');
		mappings.put("ccedil", '\u00E7');
		mappings.put("egrave", '\u00E8');
		mappings.put("eacute", '\u00E9');
		mappings.put("ecirc", '\u00EA');
		mappings.put("euml", '\u00EB');
		mappings.put("igrave", '\u00EC');
		mappings.put("iacute", '\u00ED');
		mappings.put("icirc", '\u00EE');
		mappings.put("iuml", '\u00EF');
		mappings.put("eth", '\u00F0');
		mappings.put("ntilde", '\u00F1');
		mappings.put("ograve", '\u00F2');
		mappings.put("oacute", '\u00F3');
		mappings.put("ocirc", '\u00F4');
		mappings.put("otilde", '\u00F5');
		mappings.put("ouml", '\u00F6');
		mappings.put("divide", '\u00F7');
		mappings.put("oslash", '\u00F8');
		mappings.put("ugrave", '\u00F9');
		mappings.put("uacute", '\u00FA');
		mappings.put("ucirc", '\u00FB');
		mappings.put("uuml", '\u00FC');
		mappings.put("yacute", '\u00FD');
		mappings.put("thorn", '\u00FE');
		mappings.put("yuml", '\u00FF');
		mappings.put("OElig", '\u0152');
		mappings.put("oelig", '\u0153');
		mappings.put("Scaron", '\u0160');
		mappings.put("scaron", '\u0161');
		mappings.put("Yuml", '\u0178');
		mappings.put("fnof", '\u0192');
		mappings.put("circ", '\u02C6');
		mappings.put("tilde", '\u02DC');
		mappings.put("Alpha", '\u0391');
		mappings.put("Beta", '\u0392');
		mappings.put("Gamma", '\u0393');
		mappings.put("Delta", '\u0394');
		mappings.put("Epsilon", '\u0395');
		mappings.put("Zeta", '\u0396');
		mappings.put("Eta", '\u0397');
		mappings.put("Theta", '\u0398');
		mappings.put("Iota", '\u0399');
		mappings.put("Kappa", '\u039A');
		mappings.put("Lambda", '\u039B');
		mappings.put("Mu", '\u039C');
		mappings.put("Nu", '\u039D');
		mappings.put("Xi", '\u039E');
		mappings.put("Omicron", '\u039F');
		mappings.put("Pi", '\u03A0');
		mappings.put("Rho", '\u03A1');
		mappings.put("Sigma", '\u03A3');
		mappings.put("Tau", '\u03A4');
		mappings.put("Upsilon", '\u03A5');
		mappings.put("Phi", '\u03A6');
		mappings.put("Chi", '\u03A7');
		mappings.put("Psi", '\u03A8');
		mappings.put("Omega", '\u03A9');
		mappings.put("alpha", '\u03B1');
		mappings.put("beta", '\u03B2');
		mappings.put("gamma", '\u03B3');
		mappings.put("delta", '\u03B4');
		mappings.put("epsilon", '\u03B5');
		mappings.put("zeta", '\u03B6');
		mappings.put("eta", '\u03B7');
		mappings.put("theta", '\u03B8');
		mappings.put("iota", '\u03B9');
		mappings.put("kappa", '\u03BA');
		mappings.put("lambda", '\u03BB');
		mappings.put("mu", '\u03BC');
		mappings.put("nu", '\u03BD');
		mappings.put("xi", '\u03BE');
		mappings.put("omicron", '\u03BF');
		mappings.put("pi", '\u03C0');
		mappings.put("rho", '\u03C1');
		mappings.put("sigmaf", '\u03C2');
		mappings.put("sigma", '\u03C3');
		mappings.put("tau", '\u03C4');
		mappings.put("upsilon", '\u03C5');
		mappings.put("phi", '\u03C6');
		mappings.put("chi", '\u03C7');
		mappings.put("psi", '\u03C8');
		mappings.put("omega", '\u03C9');
		mappings.put("thetasym", '\u03D1');
		mappings.put("upsih", '\u03D2');
		mappings.put("piv", '\u03D6');
		mappings.put("ensp", '\u2002');
		mappings.put("emsp", '\u2003');
		mappings.put("thinsp", '\u2009');
		mappings.put("zwnj", '\u200C');
		mappings.put("zwj", '\u200D');
		mappings.put("lrm", '\u200E');
		mappings.put("rlm", '\u200F');
		mappings.put("ndash", '\u2013');
		mappings.put("mdash", '\u2014');
		mappings.put("lsquo", '\u2018');
		mappings.put("rsquo", '\u2019');
		mappings.put("sbquo", '\u201A');
		mappings.put("ldquo", '\u201C');
		mappings.put("rdquo", '\u201D');
		mappings.put("bdquo", '\u201E');
		mappings.put("dagger", '\u2020');
		mappings.put("Dagger", '\u2021');
		mappings.put("bull", '\u2022');
		mappings.put("hellip", '\u2026');
		mappings.put("permil", '\u2030');
		mappings.put("prime", '\u2032');
		mappings.put("Prime", '\u2033');
		mappings.put("lsaquo", '\u2039');
		mappings.put("rsaquo", '\u203A');
		mappings.put("oline", '\u203E');
		mappings.put("frasl", '\u2044');
		mappings.put("euro", '\u20AC');
		mappings.put("image", '\u2111');
		mappings.put("weierp", '\u2118');
		mappings.put("real", '\u211C');
		mappings.put("trade", '\u2122');
		mappings.put("alefsym", '\u2135');
		mappings.put("larr", '\u2190');
		mappings.put("uarr", '\u2191');
		mappings.put("rarr", '\u2192');
		mappings.put("darr", '\u2193');
		mappings.put("harr", '\u2194');
		mappings.put("crarr", '\u21B5');
		mappings.put("lArr", '\u21D0');
		mappings.put("uArr", '\u21D1');
		mappings.put("rArr", '\u21D2');
		mappings.put("dArr", '\u21D3');
		mappings.put("hArr", '\u21D4');
		mappings.put("forall", '\u2200');
		mappings.put("part", '\u2202');
		mappings.put("exist", '\u2203');
		mappings.put("empty", '\u2205');
		mappings.put("nabla", '\u2207');
		mappings.put("isin", '\u2208');
		mappings.put("notin", '\u2209');
		mappings.put("ni", '\u220B');
		mappings.put("prod", '\u220F');
		mappings.put("sum", '\u2211');
		mappings.put("minus", '\u2212');
		mappings.put("lowast", '\u2217');
		mappings.put("radic", '\u221A');
		mappings.put("prop", '\u221D');
		mappings.put("infin", '\u221E');
		mappings.put("ang", '\u2220');
		mappings.put("and", '\u2227');
		mappings.put("or", '\u2228');
		mappings.put("cap", '\u2229');
		mappings.put("cup", '\u222A');
		mappings.put("int", '\u222B');
		mappings.put("there4", '\u2234');
		mappings.put("sim", '\u223C');
		mappings.put("cong", '\u2245');
		mappings.put("asymp", '\u2248');
		mappings.put("ne", '\u2260');
		mappings.put("equiv", '\u2261');
		mappings.put("le", '\u2264');
		mappings.put("ge", '\u2265');
		mappings.put("sub", '\u2282');
		mappings.put("sup", '\u2283');
		mappings.put("nsub", '\u2284');
		mappings.put("sube", '\u2286');
		mappings.put("supe", '\u2287');
		mappings.put("oplus", '\u2295');
		mappings.put("otimes", '\u2297');
		mappings.put("perp", '\u22A5');
		mappings.put("sdot", '\u22C5');
		mappings.put("lceil", '\u2308');
		mappings.put("rceil", '\u2309');
		mappings.put("lfloor", '\u230A');
		mappings.put("rfloor", '\u230B');
		mappings.put("lang", '\u2329');
		mappings.put("rang", '\u232A');
		mappings.put("loz", '\u25CA');
		mappings.put("spades", '\u2660');
		mappings.put("clubs", '\u2663');
		mappings.put("hearts", '\u2665');
		mappings.put("diams", '\u2666');
	}
}
