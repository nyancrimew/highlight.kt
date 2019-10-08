package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
 Language = ArcGIS Arcade
 Category = scripting
 Author = John Foster <jfoster@esri.com>
 Description = ArcGIS Arcade is an expression language used in many Esri ArcGIS products such as Pro, Online, Server, Runtime, JavaScript, and Python
*/
/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class ArcadeLanguage : LanguageBuilder {
    val IDENT_RE = "[A-Za-z_][0-9A-Za-z_]*"
    val KEYWORDS = keywordsJson(
        """
        keyword = "if for while val new function do return void else break",
        literal = "BackSlash DoubleQuote false ForwardSlash Infinity NaN NewLine null PI SingleQuote Tab TextFormatting true undefined",
        built_in = "Abs Acos Angle Attachments Area AreaGeodetic Asin Atan Atan2 Average Bearing Boolean Buffer BufferGeodetic Ceil Centroid Clip Console Constrain Contains Cos Count Crosses Cut Date DateAdd DateDiff Day Decode DefaultValue Dictionary Difference Disjoint Distance DistanceGeodetic Distinct DomainCode DomainName Equals Exp Extent Feature FeatureSet FeatureSetByAssociation FeatureSetById FeatureSetByPortalItem FeatureSetByRelationshipName FeatureSetByTitle FeatureSetByUrl Filter First Floor Geometry GroupBy Guid HasKey Hour IIf IndexOf Intersection Intersects IsEmpty IsNan IsSelfIntersecting Length LengthGeodetic Log Max Mean Millisecond Min Minute Month MultiPartToSinglePart Multipoint NextSequenceValue Now Number OrderBy Overlaps Point Polygon Polyline Portal Pow Random Relate Reverse RingIsClockWise Round Second SetGeometry Sin Sort Sqrt Stdev Sum SymmetricDifference Tan Text Timestamp Today ToLocal Top Touches ToUTC TrackCurrentTime TrackGeometryWindow TrackIndex TrackStartTime TrackWindow TypeOf Union UrlEncode Variance Weekday When Within Year "
        """.trimIndent()
    )
    val SYMBOL = Mode(
        className = "symbol",
        begin = "\\${'$'}[datastore|feature|layer|map|measure|sourcefeature|sourcelayer|targetfeature|targetlayer|value|view]+"
    )
    val NUMBER = Mode(
        className = "number",
        variants = listOf(
            Mode(begin = "\\b(0[bB][01]+)"),
            Mode(begin = "\\b(0[oO][0-7]+)"),
            Mode(begin = hljs.C_NUMBER_RE)
        ),
        relevance = 0
    )
    val SUBST = Mode(
        className = "subst",
        begin = "\\${'$'}\\{",
        end = "\\}",
        keywords = KEYWORDS,
        contains = listOf() // defined later
    )
    val TEMPLATE_STRING = Mode(
        className = "string",
        begin = "`",
        end = "`",
        contains = listOf(
            hljs.BACKSLASH_ESCAPE,
            SUBST
        )
    )
    /* SUBST.contains = listOf(
     hljs.APOS_STRING_MODE,
     hljs.QUOTE_STRING_MODE,
     TEMPLATE_STRING,
     NUMBER,
     hljs.REGEXP_MODE
     )*/
    val PARAMS_CONTAINS = SUBST.contains + listOf(
        hljs.C_BLOCK_COMMENT_MODE,
        hljs.C_LINE_COMMENT_MODE
    )

    override fun build() = Mode(
        aliases = listOf("arcade"),
        keywords = KEYWORDS,
        contains = listOf(
            hljs.APOS_STRING_MODE,
            hljs.QUOTE_STRING_MODE,
            TEMPLATE_STRING,
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            SYMBOL,
            NUMBER,
            Mode(// object attr container
                begin =
                    """[{,]\s*""",
                relevance = 0,
                contains = listOf(
                    Mode(
                        begin = IDENT_RE + "\\s*:",
                        returnBegin = true,
                        relevance = 0,
                        contains = listOf(
                            Mode(
                                className = "attr",
                                begin = IDENT_RE,
                                relevance = 0
                            )
                        )
                    )
                )
            ),
            Mode(// "value" container
                begin = "(|\\b(return)\\b)\\s*",
                keywords = keywords("return"),
                contains = listOf(
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE,
                    hljs.REGEXP_MODE,
                    Mode(
                        className = "function",
                        begin = "(\\(.*?\\)|)\\s*=>",
                        returnBegin = true,
                        end = "\\s*=>",
                        contains = listOf(
                            Mode(
                                className = "params",
                                variants = listOf(
                                    Mode(
                                        begin = IDENT_RE
                                    ),
                                    Mode(
                                        begin =
                                            """\(\s*\)"""
                                    ),
                                    Mode(
                                        begin =
                                            """\(""",
                                        end =
                                            """\)""",
                                        excludeBegin = true,
                                        excludeEnd = true,
                                        keywords = KEYWORDS,
                                        contains = PARAMS_CONTAINS
                                    )
                                )
                            )
                        )
                    )
                ),
                relevance = 0
            ),
            Mode(
                className = "function",
                beginKeywords = keywords("function"),
                end =
                    """\{""",
                excludeEnd = true,
                contains = listOf(
                    hljs.inherit(hljs.TITLE_MODE, Mode(begin = IDENT_RE)),
                    Mode(
                        className = "params",
                        begin =
                            """\(""",
                        end =
                            """\)""",
                        excludeBegin = true,
                        excludeEnd = true,
                        contains = PARAMS_CONTAINS
                    )
                ),
                illegal =
                    """\[|%"""
            ),
            Mode(
                begin =
                    """\${'$'}[(.]"""
            )
        ),
        illegal =
            """#(?!!)"""
    )
}
