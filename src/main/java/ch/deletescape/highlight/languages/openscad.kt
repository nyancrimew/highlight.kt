package ch.deletescape.highlight.languages
import ch.deletescape.highlight.core.*
/*
Language = OpenSCAD
Author = Dan Panzarella <alsoelp@gmail.com>
Description = OpenSCAD is a language for the 3D CAD modeling software of the same name.
Category = scientific
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/openscad.js MD5 <d4986c9572a30d4ae67139f584a34087>
 */
internal fun openscad(): Mode {
    var SPECIAL_VARS = Mode(
        className = "keyword",
        begin = "\\\$(f[asn]|t|vp[rtd]|children)"
    )
    var LITERALS = Mode(
        keywords = listOf(
            Keyword(
                className = "className",
                value = "literal"
            ),
            Keyword(
                className = "begin",
                value = "false|true|PI|undef"
            )
        )
    )
    var NUMBERS = Mode(
        className = "number",
        begin = "\\b\\d+(\\.\\d+)?(e-?\\d+)?",
        // adds 1e5, 1e-10
        relevance = 0
    )
    var STRING = hljs.inherit(hljs.QUOTE_STRING_MODE, Mode(illegal = null))
    var PREPRO = Mode(
        className = "meta",
        keywords = listOf(
            Keyword(
                className = "meta-keyword",
                value = "include use"
            )
        ),
        begin = "include|use <",
        end = ">"
    )
    var PARAMS = Mode(
        className = "params",
        begin = "\\(",
        end = "\\)",
        contains = listOf(
            hljs.SELF,
            NUMBERS, STRING, SPECIAL_VARS, LITERALS
        )
    )
    var MODIFIERS = Mode(
        begin = "[*!#%]",
        relevance = 0
    )
    var FUNCTIONS = Mode(
        className = "function",
        beginKeywords = keywords("module function"),
        end = "\\=|\\{",
        contains = listOf(PARAMS, hljs.UNDERSCORE_TITLE_MODE)
    )
    return Mode(
        aliases = listOf("scad"),
        keywords = listOf(
            Keyword(
                className = "keyword",
                value = "function module include use for intersection_for if else \\%"
            ),
            Keyword(
                className = "literal",
                value = "false true PI undef"
            ),
            Keyword(
                className = "built_in",
                value = "circle square polygon text sphere cube cylinder polyhedron translate rotate scale resize mirror multmatrix color offset hull minkowski union difference intersection abs sign sin cos tan acos asin atan atan2 floor round ceil ln log pow sqrt exp rands min max concat lookup str chr search version version_num norm cross parent_module echo import import_dxf dxf_linear_extrude linear_extrude rotate_extrude surface projection render children dxf_cross dxf_dim let assign"
            )
        ),
        contains = listOf(
            hljs.C_LINE_COMMENT_MODE,
            hljs.C_BLOCK_COMMENT_MODE,
            NUMBERS,
            PREPRO,
            STRING,
            SPECIAL_VARS,
            MODIFIERS,
            FUNCTIONS
        )
    )
}
