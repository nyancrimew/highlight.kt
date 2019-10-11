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
            registerLanguage("cpp", cpp())
            registerLanguage("1c", `1c`())
            registerLanguage("abnf", abnf())
            registerLanguage("accesslog", accesslog())
            registerLanguage("actionscript", actionscript())
            registerLanguage("ada", ada())
            registerLanguage("angelscript", angelscript())
            registerLanguage("apache", apache())
            registerLanguage("applescript", applescript())
            registerLanguage("arcade", arcade())
            registerLanguage("arduino", arduino())
            registerLanguage("armasm", armasm())
            registerLanguage("asciidoc", asciidoc())
            registerLanguage("aspectj", aspectj())
            registerLanguage("autohotkey", autohotkey())
            registerLanguage("autoit", autoit())
            registerLanguage("avrasm", avrasm())
            registerLanguage("awk", awk())
            registerLanguage("axapta", axapta())
            registerLanguage("bash", bash())
            registerLanguage("basic", basic())
            registerLanguage("bnf", bnf())
            registerLanguage("brainfuck", brainfuck())
            registerLanguage("cal", cal())
            registerLanguage("capnproto", capnproto())
            registerLanguage("ceylon", ceylon())
            registerLanguage("clean", clean())
            registerLanguage("clojure", clojure())
            registerLanguage("clojure-repl", `clojure-repl`())
            registerLanguage("cmake", cmake())
            registerLanguage("coffeescript", coffeescript())
            registerLanguage("coq", coq())
            registerLanguage("cos", cos())
            registerLanguage("crmsh", crmsh())
            registerLanguage("cs", cs())
            registerLanguage("csp", csp())
            registerLanguage("css", css())
            registerLanguage("d", d())
            registerLanguage("dart", dart())
            registerLanguage("delphi", delphi())
            registerLanguage("diff", diff())
            registerLanguage("django", django())
            registerLanguage("dns", dns())
            registerLanguage("dockerfile", dockerfile())
            registerLanguage("dos", dos())
            registerLanguage("dsconfig", dsconfig())
            registerLanguage("dts", dts())
            registerLanguage("dust", dust())
            registerLanguage("ebnf", ebnf())
            registerLanguage("elixir", elixir())
            registerLanguage("elm", elm())
            registerLanguage("erb", erb())
            registerLanguage("erlang", erlang())
            registerLanguage("erlang-repl", `erlang-repl`())
            registerLanguage("excel", excel())
            registerLanguage("fix", fix())
            registerLanguage("flix", flix())
            registerLanguage("fortran", fortran())
            registerLanguage("fsharp", fsharp())
            registerLanguage("gams", gams())
            registerLanguage("gcode", gcode())
            registerLanguage("gherkin", gherkin())
            registerLanguage("glsl", glsl())
            registerLanguage("gml", gml())
            registerLanguage("go", go())
            registerLanguage("golo", golo())
            registerLanguage("gradle", gradle())
            registerLanguage("groovy", groovy())
            registerLanguage("haml", haml())
            registerLanguage("handlebars", handlebars())
            registerLanguage("haskell", haskell())
            registerLanguage("haxe", haxe())
            registerLanguage("hsp", hsp())
            registerLanguage("htmlbars", htmlbars())
            registerLanguage("http", http())
            registerLanguage("hy", hy())
            registerLanguage("inform7", inform7())
            registerLanguage("ini", ini())
            registerLanguage("irpf90", irpf90())
            registerLanguage("isbl", isbl())
            registerLanguage("java", java())
            registerLanguage("javascript", javascript())
            registerLanguage("jboss-cli", `jboss-cli`())
            registerLanguage("json", json())
            registerLanguage("julia", julia())
            registerLanguage("julia-repl", `julia-repl`())
            registerLanguage("kotlin", kt())
            registerLanguage("lasso", lasso())
            registerLanguage("ldif", ldif())
            registerLanguage("leaf", leaf())
            registerLanguage("lisp", lisp())
            registerLanguage("livecodeserver", livecodeserver())
            registerLanguage("livescript", livescript())
            registerLanguage("llvm", llvm())
            registerLanguage("lsl", lsl())
            registerLanguage("lua", lua())
            registerLanguage("makefile", makefile())
            registerLanguage("markdown", markdown())
            registerLanguage("matlab", matlab())
            registerLanguage("maxima", maxima())
            registerLanguage("mel", mel())
            registerLanguage("mercury", mercury())
            registerLanguage("mipsasm", mipsasm())
            registerLanguage("mizar", mizar())
            registerLanguage("mojolicious", mojolicious())
            registerLanguage("monkey", monkey())
            registerLanguage("moonscript", moonscript())
            registerLanguage("n1ql", n1ql())
            registerLanguage("nginx", nginx())
            registerLanguage("nimrod", nimrod())
            registerLanguage("nix", nix())
            registerLanguage("nsis", nsis())
            registerLanguage("objectivec", objectivec())
            registerLanguage("ocaml", ocaml())
            registerLanguage("openscad", openscad())
            registerLanguage("oxygene", oxygene())
            registerLanguage("parser3", parser3())
            registerLanguage("perl", perl())
            registerLanguage("pf", pf())
            registerLanguage("php", php())
            registerLanguage("plaintext", plaintext())
            registerLanguage("pony", pony())
            registerLanguage("powershell", powershell())
            registerLanguage("processing", processing())
            registerLanguage("profile", profile())
            registerLanguage("prolog", prolog())
            registerLanguage("properties", properties())
            registerLanguage("protobuf", protobuf())
            registerLanguage("puppet", puppet())
            registerLanguage("purebasic", purebasic())
            registerLanguage("python", python())
            registerLanguage("q", q())
            registerLanguage("qml", qml())
            registerLanguage("r", r())
            registerLanguage("rib", rib())
            registerLanguage("roboconf", roboconf())
            registerLanguage("routeros", routeros())
            registerLanguage("rsl", rsl())
            registerLanguage("ruby", ruby())
            registerLanguage("ruleslanguage", ruleslanguage())
            registerLanguage("rust", rust())
            registerLanguage("sas", sas())
            registerLanguage("scala", scala())
            registerLanguage("scheme", scheme())
            registerLanguage("scilab", scilab())
            registerLanguage("scss", scss())
            registerLanguage("shell", shell())
            registerLanguage("smali", smali())
            registerLanguage("smalltalk", smalltalk())
            registerLanguage("sml", sml())
            registerLanguage("sqf", sqf())
            registerLanguage("sql", sql())
            registerLanguage("stan", stan())
            registerLanguage("stata", stata())
            registerLanguage("step21", step21())
            registerLanguage("stylus", stylus())
            registerLanguage("subunit", subunit())
            registerLanguage("swift", swift())
            registerLanguage("taggerscript", taggerscript())
            registerLanguage("tap", tap())
            registerLanguage("tcl", tcl())
            registerLanguage("tex", tex())
            registerLanguage("thrift", thrift())
            registerLanguage("tp", tp())
            registerLanguage("twig", twig())
            registerLanguage("typescript", typescript())
            registerLanguage("vala", vala())
            registerLanguage("vbnet", vbnet())
            registerLanguage("vbscript", vbscript())
            registerLanguage("vbscript-html", `vbscript-html`())
            registerLanguage("verilog", verilog())
            registerLanguage("vhdl", vhdl())
            registerLanguage("vim", vim())
            registerLanguage("x86asm", x86asm())
            registerLanguage("xl", xl())
            registerLanguage("xml", xml())
            registerLanguage("xquery", xquery())
            registerLanguage("yaml", yaml())
            registerLanguage("zephir", zephir())
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
