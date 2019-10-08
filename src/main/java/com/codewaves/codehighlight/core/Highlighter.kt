package com.codewaves.codehighlight.core;

import com.codewaves.codehighlight.languages.*;

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
            registerLanguage("1c", `1cLanguage`().build());
            registerLanguage("abnf", AbnfLanguage().build());
            registerLanguage("accesslog", AccesslogLanguage().build());
            registerLanguage("actionscript", ActionscriptLanguage().build());
            registerLanguage("ada", AdaLanguage().build());
            registerLanguage("angelscript", AngelscriptLanguage().build());
            registerLanguage("apache", ApacheLanguage().build());
            registerLanguage("applescript", ApplescriptLanguage().build());
            registerLanguage("arcade", ArcadeLanguage().build());
            registerLanguage("arduino", ArduinoLanguage().build());
            registerLanguage("armasm", ArmasmLanguage().build());
            registerLanguage("asciidoc", AsciidocLanguage().build());
            registerLanguage("aspectj", AspectjLanguage().build());
            registerLanguage("autohotkey", AutohotkeyLanguage().build());
            registerLanguage("autoit", AutoitLanguage().build());
            registerLanguage("avrasm", AvrasmLanguage().build());
            registerLanguage("awk", AwkLanguage().build());
            registerLanguage("axapta", AxaptaLanguage().build());
            registerLanguage("bash", BashLanguage().build());
            registerLanguage("basic", BasicLanguage().build());
            registerLanguage("bnf", BnfLanguage().build());
            registerLanguage("brainfuck", BrainfuckLanguage().build());
            registerLanguage("cal", CalLanguage().build());
            registerLanguage("capnproto", CapnprotoLanguage().build());
            registerLanguage("ceylon", CeylonLanguage().build());
            registerLanguage("clean", CleanLanguage().build());
            registerLanguage("clojure-repl", ClojurereplLanguage().build());
            registerLanguage("clojure", ClojureLanguage().build());
            registerLanguage("cmake", CmakeLanguage().build());
            registerLanguage("coffeescript", CoffeescriptLanguage().build());
            registerLanguage("coq", CoqLanguage().build());
            registerLanguage("cos", CosLanguage().build());
            registerLanguage("cpp", CppLanguage().build());
            registerLanguage("crmsh", CrmshLanguage().build());
            registerLanguage("crystal", CrystalLanguage().build());
            registerLanguage("cs", CsLanguage().build());
            registerLanguage("csp", CspLanguage().build());
            registerLanguage("css", CssLanguage().build());
            registerLanguage("d", DLanguage().build());
            registerLanguage("dart", DartLanguage().build());
            registerLanguage("delphi", DelphiLanguage().build());
            registerLanguage("diff", DiffLanguage().build());
            registerLanguage("django", DjangoLanguage().build());
            registerLanguage("dns", DnsLanguage().build());
            registerLanguage("dockerfile", DockerfileLanguage().build());
            registerLanguage("dos", DosLanguage().build());
            registerLanguage("dsconfig", DsconfigLanguage().build());
            registerLanguage("dts", DtsLanguage().build());
            registerLanguage("dust", DustLanguage().build());
            registerLanguage("ebnf", EbnfLanguage().build());
            registerLanguage("elixir", ElixirLanguage().build());
            registerLanguage("elm", ElmLanguage().build());
            registerLanguage("erb", ErbLanguage().build());
            registerLanguage("erlang-repl", ErlangreplLanguage().build());
            registerLanguage("erlang", ErlangLanguage().build());
            registerLanguage("excel", ExcelLanguage().build());
            registerLanguage("fix", FixLanguage().build());
            registerLanguage("flix", FlixLanguage().build());
            registerLanguage("fortran", FortranLanguage().build());
            registerLanguage("fsharp", FsharpLanguage().build());
            registerLanguage("gams", GamsLanguage().build());
            registerLanguage("gauss", GaussLanguage().build());
            registerLanguage("gcode", GcodeLanguage().build());
            registerLanguage("gherkin", GherkinLanguage().build());
            registerLanguage("glsl", GlslLanguage().build());
            registerLanguage("gml", GmlLanguage().build());
            registerLanguage("go", GoLanguage().build());
            registerLanguage("golo", GoloLanguage().build());
            registerLanguage("gradle", GradleLanguage().build());
            registerLanguage("groovy", GroovyLanguage().build());
            registerLanguage("haml", HamlLanguage().build());
            registerLanguage("handlebars", HandlebarsLanguage().build());
            registerLanguage("haskell", HaskellLanguage().build());
            registerLanguage("haxe", HaxeLanguage().build());
            registerLanguage("hsp", HspLanguage().build());
            registerLanguage("htmlbars", HtmlbarsLanguage().build());
            registerLanguage("http", HttpLanguage().build());
            registerLanguage("hy", HyLanguage().build());
            registerLanguage("inform7", Inform7Language().build());
            registerLanguage("ini", IniLanguage().build());
            registerLanguage("irpf90", Irpf90Language().build());
            registerLanguage("isbl", IsblLanguage().build());
            registerLanguage("java", JavaLanguage().build());
            registerLanguage("javascript", JavascriptLanguage().build());
            registerLanguage("jboss-cli", JbosscliLanguage().build());
            registerLanguage("json", JsonLanguage().build());
            registerLanguage("julia-repl", JuliareplLanguage().build());
            registerLanguage("julia", JuliaLanguage().build());
            registerLanguage("kotlin", KotlinLanguage().build());
            registerLanguage("lasso", LassoLanguage().build());
            registerLanguage("ldif", LdifLanguage().build());
            registerLanguage("leaf", LeafLanguage().build());
            registerLanguage("less", LessLanguage().build());
            registerLanguage("lisp", LispLanguage().build());
            registerLanguage("livecodeserver", LivecodeserverLanguage().build());
            registerLanguage("livescript", LivescriptLanguage().build());
            registerLanguage("llvm", LlvmLanguage().build());
            registerLanguage("lsl", LslLanguage().build());
            registerLanguage("lua", LuaLanguage().build());
            registerLanguage("makefile", MakefileLanguage().build());
            registerLanguage("markdown", MarkdownLanguage().build());
            registerLanguage("mathematica", MathematicaLanguage().build());
            registerLanguage("matlab", MatlabLanguage().build());
            registerLanguage("maxima", MaximaLanguage().build());
            registerLanguage("mel", MelLanguage().build());
            registerLanguage("mercury", MercuryLanguage().build());
            registerLanguage("mipsasm", MipsasmLanguage().build());
            registerLanguage("mizar", MizarLanguage().build());
            registerLanguage("mojolicious", MojoliciousLanguage().build());
            registerLanguage("monkey", MonkeyLanguage().build());
            registerLanguage("moonscript", MoonscriptLanguage().build());
            registerLanguage("n1ql", N1qlLanguage().build());
            registerLanguage("nginx", NginxLanguage().build());
            registerLanguage("nimrod", NimrodLanguage().build());
            registerLanguage("nix", NixLanguage().build());
            registerLanguage("nsis", NsisLanguage().build());
            registerLanguage("objectivec", ObjectivecLanguage().build());
            registerLanguage("ocaml", OcamlLanguage().build());
            registerLanguage("openscad", OpenscadLanguage().build());
            registerLanguage("oxygene", OxygeneLanguage().build());
            registerLanguage("parser3", Parser3Language().build());
            registerLanguage("perl", PerlLanguage().build());
            registerLanguage("pf", PfLanguage().build());
            registerLanguage("pgsql", PgsqlLanguage().build());
            registerLanguage("php", PhpLanguage().build());
            registerLanguage("plaintext", PlaintextLanguage().build());
            registerLanguage("pony", PonyLanguage().build());
            registerLanguage("powershell", PowershellLanguage().build());
            registerLanguage("processing", ProcessingLanguage().build());
            registerLanguage("profile", ProfileLanguage().build());
            registerLanguage("prolog", PrologLanguage().build());
            registerLanguage("properties", PropertiesLanguage().build());
            registerLanguage("protobuf", ProtobufLanguage().build());
            registerLanguage("puppet", PuppetLanguage().build());
            registerLanguage("purebasic", PurebasicLanguage().build());
            registerLanguage("python", PythonLanguage().build());
            registerLanguage("q", QLanguage().build());
            registerLanguage("qml", QmlLanguage().build());
            registerLanguage("r", RLanguage().build());
            registerLanguage("reasonml", ReasonmlLanguage().build());
            registerLanguage("rib", RibLanguage().build());
            registerLanguage("roboconf", RoboconfLanguage().build());
            registerLanguage("routeros", RouterosLanguage().build());
            registerLanguage("rsl", RslLanguage().build());
            registerLanguage("ruby", RubyLanguage().build());
            registerLanguage("ruleslanguage", RuleslanguageLanguage().build());
            registerLanguage("rust", RustLanguage().build());
            registerLanguage("sas", SasLanguage().build());
            registerLanguage("scala", ScalaLanguage().build());
            registerLanguage("scheme", SchemeLanguage().build());
            registerLanguage("scilab", ScilabLanguage().build());
            registerLanguage("scss", ScssLanguage().build());
            registerLanguage("shell", ShellLanguage().build());
            registerLanguage("smali", SmaliLanguage().build());
            registerLanguage("smalltalk", SmalltalkLanguage().build());
            registerLanguage("sml", SmlLanguage().build());
            registerLanguage("sqf", SqfLanguage().build());
            registerLanguage("sql", SqlLanguage().build());
            registerLanguage("stan", StanLanguage().build());
            registerLanguage("stata", StataLanguage().build());
            registerLanguage("step21", Step21Language().build());
            registerLanguage("stylus", StylusLanguage().build());
            registerLanguage("subunit", SubunitLanguage().build());
            registerLanguage("swift", SwiftLanguage().build());
            registerLanguage("taggerscript", TaggerscriptLanguage().build());
            registerLanguage("tap", TapLanguage().build());
            registerLanguage("tcl", TclLanguage().build());
            registerLanguage("tex", TexLanguage().build());
            registerLanguage("thrift", ThriftLanguage().build());
            registerLanguage("tp", TpLanguage().build());
            registerLanguage("twig", TwigLanguage().build());
            registerLanguage("typescript", TypescriptLanguage().build());
            registerLanguage("vala", ValaLanguage().build());
            registerLanguage("vbnet", VbnetLanguage().build());
            registerLanguage("vbscript-html", VbscripthtmlLanguage().build());
            registerLanguage("vbscript", VbscriptLanguage().build());
            registerLanguage("verilog", VerilogLanguage().build());
            registerLanguage("vhdl", VhdlLanguage().build());
            registerLanguage("vim", VimLanguage().build());
            registerLanguage("x86asm", X86asmLanguage().build());
            registerLanguage("xl", XlLanguage().build());
            registerLanguage("xml", XmlLanguage().build());
            registerLanguage("xquery", XqueryLanguage().build());
            registerLanguage("yaml", YamlLanguage().build());
            registerLanguage("zephir", ZephirLanguage().build());
//END
        }

        @JvmStatic
        fun findLanguage(name: String) = languageMap[name]

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
    fun highlight(languageName: String, code: String): HighlightResult {
        // Find Language
        val language = findLanguage(languageName) ?: return HighlightResult(0, null, code)

        // Parse
        val renderer = rendererFactory.create(languageName)
        val parser = HighlightParser(language, rendererFactory, renderer)
        val relevance = parser.highlight(code, false, null)
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
    fun highlightAuto(code: String, languageSubset: List<String>? = null): HighlightResult {
        val langs = if (languageSubset.isNullOrEmpty()) languages else languageSubset

        var bestRelevance = -1
        var bestLanguageName: String? = null
        var result: CharSequence? = null
        for (languageName in langs) {
            val language = findLanguage(languageName) ?: continue

            val renderer = rendererFactory.create(languageName)
            val parser = HighlightParser(language, rendererFactory, renderer)
            val relevance = parser.highlight(code, false, null)
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
