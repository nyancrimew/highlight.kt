package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
 Language = ArcGIS Arcade
 Category = scripting
 Author = John Foster <jfoster@esri.com>
 Description = ArcGIS Arcade is an expression language used in many Esri ArcGIS products such as Pro, Online, Server, Runtime, JavaScript, and Python
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/arcade.js MD5 <833ba17c4f7fe568576d7464b38990bf>
 */
internal fun arcade(): Mode {
    var IDENT_RE = "[A-Za-z_][0-9A-Za-z_]*"
    var KEYWORDS = listOf(
        Keyword(
            className = "keyword",
            value =
                "if for while var new function do return void else break"
        ),
        Keyword(
            className = "literal",
            value =
                "BackSlash DoubleQuote false ForwardSlash Infinity NaN NewLine null PI SingleQuote Tab TextFormatting true undefined"
        ),
        Keyword(
            className = "built_in",
            value =
                "Abs Acos Angle Attachments Area AreaGeodetic Asin Atan Atan2 Average Bearing Boolean Buffer BufferGeodetic Ceil Centroid Clip Console Constrain Contains Cos Count Crosses Cut Date DateAdd DateDiff Day Decode DefaultValue Dictionary Difference Disjoint Distance DistanceGeodetic Distinct DomainCode DomainName Equals Exp Extent Feature FeatureSet FeatureSetByAssociation FeatureSetById FeatureSetByPortalItem FeatureSetByRelationshipName FeatureSetByTitle FeatureSetByUrl Filter First Floor Geometry GroupBy Guid HasKey Hour IIf IndexOf Intersection Intersects IsEmpty IsNan IsSelfIntersecting Length LengthGeodetic Log Max Mean Millisecond Min Minute Month MultiPartToSinglePart Multipoint NextSequenceValue Now Number OrderBy Overlaps Point Polygon Polyline Portal Pow Random Relate Reverse RingIsClockWise Round Second SetGeometry Sin Sort Sqrt Stdev Sum SymmetricDifference Tan Text Timestamp Today ToLocal Top Touches ToUTC TrackCurrentTime TrackGeometryWindow TrackIndex TrackStartTime TrackWindow TypeOf Union UrlEncode Variance Weekday When Within Year "
        )
    )
    // var EXPRESSIONS
    var SYMBOL = Mode(
        className = "symbol",
        begin = "\\\$[datastore|feature|layer|map|measure|sourcefeature|sourcelayer|targetfeature|targetlayer|value|view]+"
    )
    var NUMBER = Mode(
        className = "number",
        variants = listOf(
            Mode(begin = "\\b(0[bB][01]+)"),
            Mode(begin = "\\b(0[oO][0-7]+)"),
            Mode(begin = hljs.C_NUMBER_RE)
        ),
        relevance = 0
    )
    var SUBST = Mode(
        className = "subst",
        begin = "\\\$\\{",
        end = "\\}",
        keywords = keywords(KEYWORDS),
        contains = listOf() // defined later
    )
    var TEMPLATE_STRING = Mode(
        className = "string",
        begin = "`",
        end = "`",
        contains = listOf(
            hljs.BACKSLASH_ESCAPE,
            SUBST
        )
    )
    SUBST.contains = listOf(
        hljs.APOS_STRING_MODE,
        hljs.QUOTE_STRING_MODE,
        TEMPLATE_STRING,
        NUMBER,
        hljs.REGEXP_MODE
    )
    var PARAMS_CONTAINS = SUBST.contains + listOf(
        hljs.C_BLOCK_COMMENT_MODE,
        hljs.C_LINE_COMMENT_MODE
    )
    return Mode(
        aliases = listOf("arcade"),
        keywords = keywords(KEYWORDS),
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
                begin = "(" +
                    hljs.RE_STARTERS_RE + "|\\b(return)\\b)\\s*",
                keywords = keywords("return"),
                contains = listOf(
                    hljs.C_LINE_COMMENT_MODE,
                    hljs.C_BLOCK_COMMENT_MODE,
                    hljs.REGEXP_MODE,
                    Mode(
                        className = "function",
                        begin = "(\\(.*?\\)|" +
                            IDENT_RE + ")\\s*=>",
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
                                        keywords = keywords(KEYWORDS),
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
