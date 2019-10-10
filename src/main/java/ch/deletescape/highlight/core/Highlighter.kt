package ch.deletescape.highlight.core;

import ch.deletescape.highlight.languages.*;

/*
 * Created by Sergej Kravcenko on 5/17/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */

/**
 * Main class for code syntax highlighting. Contains all supported languages
 * and two main methods. Use {@link Highlighter#highlight(String, String)} if
 * code language is known or use {@link Highlighter#highlightAuto(String, String[])}
 * to automatically detect code language.
 */
class Highlighter(val rendererFactory: StyleRendererFactory) {
    companion object {
        internal val languageMap = mutableMapOf<String, Mode>()
        internal val languages = mutableSetOf<String>()

        init {
            //START
            registerLanguage("lsl", lsl())
            registerLanguage("dsconfig", dsconfig())
            registerLanguage("brainfuck", brainfuck())
            registerLanguage("objectivec", objectivec())
            registerLanguage("openscad", openscad())
            registerLanguage("fortran", fortran())
            registerLanguage("julia", julia())
            registerLanguage("kotlin", kt())
            registerLanguage("puppet", puppet())
            registerLanguage("coffeescript", coffeescript())
            registerLanguage("1c", `1c`())
            registerLanguage("angelscript", angelscript())
            registerLanguage("livescript", livescript())
            registerLanguage("delphi", delphi())
            registerLanguage("cos", cos())
            registerLanguage("properties", properties())
            registerLanguage("elixir", elixir())
            registerLanguage("vhdl", vhdl())
            registerLanguage("vim", vim())
            registerLanguage("autoit", autoit())
            registerLanguage("cpp", cpp())
            registerLanguage("smalltalk", smalltalk())
            registerLanguage("markdown", markdown())
            registerLanguage("python", python())
            registerLanguage("scilab", scilab())
            registerLanguage("xquery", xquery())
            registerLanguage("cs", cs())
            registerLanguage("pony", pony())
            registerLanguage("powershell", powershell())
            registerLanguage("nginx", nginx())
            registerLanguage("llvm", llvm())
            registerLanguage("gams", gams())
            registerLanguage("ini", ini())
            registerLanguage("swift", swift())
            registerLanguage("scss", scss())
            registerLanguage("aspectj", aspectj())
            registerLanguage("rsl", rsl())
            registerLanguage("x86asm", x86asm())
            registerLanguage("ocaml", ocaml())
            registerLanguage("dart", dart())
            registerLanguage("ruleslanguage", ruleslanguage())
            registerLanguage("tap", tap())
            registerLanguage("clojure", clojure())
            registerLanguage("htmlbars", htmlbars())
            registerLanguage("arcade", arcade())
            registerLanguage("prolog", prolog())
            registerLanguage("clean", clean())
            registerLanguage("roboconf", roboconf())
            registerLanguage("dust", dust())
            registerLanguage("stan", stan())
            registerLanguage("makefile", makefile())
            registerLanguage("rust", rust())
            registerLanguage("haskell", haskell())
            registerLanguage("hsp", hsp())
            registerLanguage("xml", xml())
            registerLanguage("ada", ada())
            registerLanguage("sqf", sqf())
            registerLanguage("processing", processing())
            registerLanguage("hy", hy())
            registerLanguage("golo", golo())
            registerLanguage("zephir", zephir())
            registerLanguage("subunit", subunit())
            registerLanguage("typescript", typescript())
            registerLanguage("pf", pf())
            registerLanguage("elm", elm())
            registerLanguage("scheme", scheme())
            registerLanguage("erb", erb())
            registerLanguage("profile", profile())
            registerLanguage("awk", awk())
            registerLanguage("diff", diff())
            registerLanguage("http", http())
            registerLanguage("perl", perl())
            registerLanguage("excel", excel())
            registerLanguage("rib", rib())
            registerLanguage("apache", apache())
            registerLanguage("haxe", haxe())
            registerLanguage("protobuf", protobuf())
            registerLanguage("ceylon", ceylon())
            registerLanguage("dockerfile", dockerfile())
            registerLanguage("inform7", inform7())
            registerLanguage("mizar", mizar())
            registerLanguage("tcl", tcl())
            registerLanguage("smali", smali())
            registerLanguage("css", css())
            registerLanguage("cmake", cmake())
            registerLanguage("gradle", gradle())
            registerLanguage("mipsasm", mipsasm())
            registerLanguage("sas", sas())
            registerLanguage("q", q())
            registerLanguage("lua", lua())
            registerLanguage("scala", scala())
            registerLanguage("basic", basic())
            registerLanguage("taggerscript", taggerscript())
            registerLanguage("coq", coq())
            registerLanguage("leaf", leaf())
            registerLanguage("cal", cal())
            registerLanguage("go", go())
            registerLanguage("bash", bash())
            registerLanguage("handlebars", handlebars())
            registerLanguage("livecodeserver", livecodeserver())
            registerLanguage("php", php())
            registerLanguage("ldif", ldif())
            registerLanguage("ebnf", ebnf())
            registerLanguage("dns", dns())
            registerLanguage("purebasic", purebasic())
            registerLanguage("maxima", maxima())
            registerLanguage("thrift", thrift())
            registerLanguage("tp", tp())
            registerLanguage("jboss-cli", `jboss-cli`())
            registerLanguage("mel", mel())
            registerLanguage("plaintext", plaintext())
            registerLanguage("json", json())
            registerLanguage("lasso", lasso())
            registerLanguage("fsharp", fsharp())
            registerLanguage("java", java())
            registerLanguage("r", r())
            registerLanguage("javascript", javascript())
            registerLanguage("xl", xl())
            registerLanguage("julia-repl", `julia-repl`())
            registerLanguage("gml", gml())
            registerLanguage("gcode", gcode())
            registerLanguage("axapta", axapta())
            registerLanguage("autohotkey", autohotkey())
            registerLanguage("stata", stata())
            registerLanguage("d", d())
            registerLanguage("vbnet", vbnet())
            registerLanguage("groovy", groovy())
            registerLanguage("verilog", verilog())
            registerLanguage("matlab", matlab())
            registerLanguage("asciidoc", asciidoc())
            registerLanguage("armasm", armasm())
            registerLanguage("stylus", stylus())
            registerLanguage("sql", sql())
            registerLanguage("crmsh", crmsh())
            registerLanguage("abnf", abnf())
            registerLanguage("n1ql", n1ql())
            registerLanguage("clojure-repl", `clojure-repl`())
            registerLanguage("isbl", isbl())
            registerLanguage("accesslog", accesslog())
            registerLanguage("capnproto", capnproto())
            registerLanguage("irpf90", irpf90())
            registerLanguage("flix", flix())
            registerLanguage("monkey", monkey())
            registerLanguage("arduino", arduino())
            registerLanguage("applescript", applescript())
            registerLanguage("shell", shell())
            registerLanguage("django", django())
            registerLanguage("ruby", ruby())
            registerLanguage("qml", qml())
            registerLanguage("actionscript", actionscript())
            registerLanguage("avrasm", avrasm())
            registerLanguage("nsis", nsis())
            registerLanguage("fix", fix())
            registerLanguage("moonscript", moonscript())
            registerLanguage("glsl", glsl())
            registerLanguage("csp", csp())
            registerLanguage("yaml", yaml())
            registerLanguage("nimrod", nimrod())
            registerLanguage("step21", step21())
            registerLanguage("dts", dts())
            registerLanguage("twig", twig())
            registerLanguage("vala", vala())
            registerLanguage("mojolicious", mojolicious())
            registerLanguage("routeros", routeros())
            registerLanguage("haml", haml())
            registerLanguage("dos", dos())
            registerLanguage("tex", tex())
            registerLanguage("erlang", erlang())
            registerLanguage("mercury", mercury())
            registerLanguage("parser3", parser3())
            registerLanguage("vbscript-html", `vbscript-html`())
            registerLanguage("lisp", lisp())
            registerLanguage("bnf", bnf())
            registerLanguage("sml", sml())
            registerLanguage("nix", nix())
            registerLanguage("erlang-repl", `erlang-repl`())
            registerLanguage("vbscript", vbscript())
            registerLanguage("oxygene", oxygene())
            registerLanguage("gherkin", gherkin())
            //END
        }

        fun findLanguage(name: String) = languageMap[name]

        fun hasLanguage(name: String) = languageMap.containsKey(name)

        @JvmStatic
        private fun registerLanguage(name: String, language: Mode) {
            languages += name
            languageMap += name to language
            languageMap += language.aliases.associateWith { language }
        }
    }

    /**
     * Core highlighting function. Accepts a language name, or an alias, and a
     * string with the code to highlight.
     *
     * @param languageName language name
     * @param code code string to highlight
     *
     * @return the given code highlight result
     */
    fun highlight(languageName: String, code: String, graceful: Boolean = true): HighlightResult {
        // Find Language
        val language = findLanguage(languageName) ?: return HighlightResult(0, null, code)

        // Parse
        val renderer = rendererFactory.create(languageName)
        val parser = HighlightParser(language, rendererFactory, renderer)
        val relevance = parser.highlight(code, false, null, graceful)
        return HighlightResult(relevance, languageName, renderer.getResult())
    }

    /**
     * Highlighting with language detection. Accepts a string with the code to
     * highlight.
     *
     * @param code code string to highlight
     * @param languageSubset list of languages for checking or empty list for all known languages
     *
     * @return the given code highlight result
     */
    fun highlightAuto(code: String, languageSubset: List<String>? = null, graceful: Boolean = true): HighlightResult {
        val langs = if (languageSubset.isNullOrEmpty()) languages else languageSubset

        var bestRelevance = -1
        var bestLanguageName: String? = null
        var result: CharSequence? = null
        for (languageName in langs) {
            val language = findLanguage(languageName) ?: continue

            val renderer = rendererFactory.create(languageName)
            val parser = HighlightParser(language, rendererFactory, renderer)
            val relevance = parser.highlight(code, false, null, graceful)
            if (relevance > bestRelevance) {
                bestRelevance = relevance
                bestLanguageName = languageName
                result = renderer.getResult()
            }
        }

        return HighlightResult(bestRelevance, bestLanguageName, result ?: code)
    }

    /**
     * Result of code syntax highlighting
     */
    data class HighlightResult(
        val relevance: Int,
        val language: String?,
        val result: CharSequence
    )
}
