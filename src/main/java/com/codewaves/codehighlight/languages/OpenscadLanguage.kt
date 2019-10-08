package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = OpenSCAD
Author = Dan Panzarella <alsoelp@gmail.com>
Description = OpenSCAD is a language for the 3D CAD modeling software of the same name.
Category = scientific
*/

/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class OpenscadLanguage : LanguageBuilder  {
 val SPECIAL_VARS = Mode(
		className = "keyword",

		begin = "\\$(f[asn]|t|vp[rtd]|children)"
	),
	LITERALS = Mode(
		className = "literal",

		begin = "false|true|PI|undef"
	),
	NUMBERS = Mode(
		className = "number",

		begin = "\\b\\d+(\\.\\d+)?(e-?\\d+)?",
 //adds 1e5, 1e-10
		relevance = 0
	),
	STRING = hljs.inherit(hljs.QUOTE_STRING_MODE,Mode(illegal = null)),
	PREPRO = Mode(
		className = "meta",

		keywords = keywords(listOf(Keyword(className = "meta-keyword",

 value = "include use"))),
		begin = "include|use <",

		end = ">"
	),
	PARAMS = Mode(
		className = "params",

		begin = "\\(",

 end = "\\)",

		contains = listOf(
 NUMBERS, STRING, SPECIAL_VARS, LITERALS)
	),
	MODIFIERS = Mode(
		begin = "[*!#%]",

		relevance = 0
	),
	FUNCTIONS = Mode(
		className = "function",

		beginKeywords = keywords("module function"),
		end = "\\=|\\{",

		contains = listOf(PARAMS, hljs.UNDERSCORE_TITLE_MODE)
	);

 override fun build() = Mode(
		aliases = listOf("scad"),
		keywords = listOf(
			Keyword(className = "keyword",

 value = "function module include use for intersection_for if else \\%"),
			Keyword(className = "literal",

 value = "false true PI undef"),
			Keyword(className = "built_in",

 value = "circle square polygon text sphere cube cylinder polyhedron translate rotate scale resize mirror multmatrix color offset hull minkowski union difference intersection abs sign sin cos tan acos asin atan atan2 floor round ceil ln log pow sqrt exp rands min max concat lookup str chr search version version_num norm cross parent_module echo import import_dxf dxf_linear_extrude linear_extrude rotate_extrude surface projection render children dxf_cross dxf_dim let assign"
		)),
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
