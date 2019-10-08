package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

/*
Language = Swift
Author = Chris Eidhof <chris@eidhof.nl>
Contributors = Nate Cook <natecook@gmail.com>, Alexander Lichter <manniL@gmx.net>
Category = system
*/


/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class SwiftLanguage : LanguageBuilder  {
  val SWIFT_KEYWORDS = listOf(
      Keyword(className = "keyword",

 value = "#available #colorLiteral #column #else #elseif #endif #file #fileLiteral #function #if #imageLiteral #line #selector #sourceLocation _ __COLUMN__ __FILE__ __FUNCTION__ __LINE__ Any as as! as? associatedtype associativity break case catch class continue convenience default defer deinit didSet do dynamic dynamicType else enum extension fallthrough false fileprivate final for func get guard if import in indirect infix init inout internal is lazy left let mutating nil none nonmutating open operator optional override postfix precedence prefix private protocol Protocol public repeat required rethrows return right self Self set static struct subscript super switch throw throws true try try! try? Type typealias unowned val weak where while willSet"),
      Keyword(className = "literal",

 value = "true false nil"),
      Keyword(className = "built_in",

 value = "abs advance alignof alignofValue anyGenerator assert assertionFailure bridgeFromObjectiveC bridgeFromObjectiveCUnconditional bridgeToObjectiveC bridgeToObjectiveCUnconditional c contains count countElements countLeadingZeros debugPrint debugPrintln distance dropFirst dropLast dump encodeBitsAsWords enumerate equal fatalError filter find getBridgedObjectiveCType getVaList indices insertionSort isBridgedToObjectiveC isBridgedVerbatimToObjectiveC isUniquelyReferenced isUniquelyReferencedNonObjC join lazy lexicographicalCompare map max maxElement min minElement numericCast overlaps partition posix precondition preconditionFailure print println quickSort readLine reduce reflect reinterpretCast reverse roundUpToAlignment sizeof sizeofValue sort split startsWith stride strideof strideofValue swap toString transcode underestimateCount unsafeAddressOf unsafeBitCast unsafeDowncast unsafeUnwrap unsafeReflect withExtendedLifetime withObjectAtPlusZero withUnsafePointer withUnsafePointerToObject withUnsafeMutablePointer withUnsafeMutablePointers withUnsafePointer withUnsafePointers withVaList zip"
    ));

  val TYPE = Mode(
    className = "type",

    begin = "\\b[A-Z][\\w\u00C0-\u02B8\"]*",

    relevance = 0
  );
  // slightly more special to swift
  val OPTIONAL_USING_TYPE = Mode(
    className = "type",

    begin = "\\b[A-Z][\\w\u00C0-\u02B8\"]*[!?]"
  )
  val BLOCK_COMMENT = hljs.COMMENT(
    "/\\*",

    "\\*/",

    Mode(
      contains = listOf("self")
    )
  );
  val SUBST = Mode(
    className = "subst",

    begin = """\\\(""",

 end = "\\)",

    keywords = keywords(SWIFT_KEYWORDS),
    contains = listOf() // assigned later
  );
  val STRING = Mode(
    className = "string",

    contains = listOf(hljs.BACKSLASH_ESCAPE, SUBST),
    variants = listOf(
      Mode(begin = """"""""",

 end = """""""""),
      Mode(begin = """"""",

 end = """"""")
    )
  );
  val NUMBERS = Mode(
      className = "number",

      begin = "\\b(listOf(\\d_]+(\\.[\\deE_]+)?|0x[a-fA-F0-9_]+(\\.[a-fA-F0-9p_]+)?|0b[01_]+|0o[0-7_]+)\\b",

      relevance = 0
  );
  SUBST.contains = listOf(NUMBERS);

  override fun build() = Mode(
    keywords = keywords(SWIFT_KEYWORDS),
    contains = listOf(
      STRING,
      hljs.C_LINE_COMMENT_MODE,
      BLOCK_COMMENT,
      OPTIONAL_USING_TYPE,
      TYPE,
      NUMBERS,
      Mode(
        className = "function",

        beginKeywords = keywords("func",

 end = "{",

 excludeEnd = true),
        contains = listOf(
          hljs.inherit(hljs.TITLE_MODE, Mode(
            begin = """[A-Za-z${'$'}_][0-9A-Za-z${'$'}_]*"""
          )),
          Mode(
            begin = """<""",

 end = """>"""
          ),
          Mode(
            className = "params",

            begin = """\(""",

 end = """\)""",

 endsParent = true,
            keywords = keywords(SWIFT_KEYWORDS),
            contains = listOf(
              

              NUMBERS,
              STRING,
              hljs.C_BLOCK_COMMENT_MODE,
              Mode(begin = ":") // relevance booster
            ),
            illegal = """["\"]\"\""
          )
        ),
        illegal = """\[|%"""
      ),
      Mode(
        className = "class",

        beginKeywords = keywords("struct protocol class extension enum"),
        keywords = keywords(SWIFT_KEYWORDS),
        end = "\\{",

        excludeEnd = true,
        contains = listOf(
          hljs.inherit(hljs.TITLE_MODE, Mode(begin = """[A-Za-z${'$'}_][\u00C0-\u02B80-9A-Za-z${'$'}_]*"""))
        )
      ),
      Mode(
        className = "meta",
 // @attributes
        begin = "(@discardableResult|@warn_unused_result|@exported|@lazy|@noescape|@NSCopying|@NSManaged|@objc|@objcMembers|@convention|@required|@noreturn|@IBAction|@IBDesignable|@IBInspectable|@IBOutlet|@infix|@prefix|@postfix|@autoclosure|@testable|@available|@nonobjc|@NSApplicationMain|@UIApplicationMain)"

      ),
      Mode(
        beginKeywords = keywords("import",

 end = """${'$'}"""),
        contains = listOf(hljs.C_LINE_COMMENT_MODE, BLOCK_COMMENT)
      )
    )
  );
}
