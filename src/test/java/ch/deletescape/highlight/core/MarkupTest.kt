package ch.deletescape.highlight.core

import ch.deletescape.highlight.core.Highlighter.Companion.hasLanguage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertTimeoutPreemptively
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.io.File
import java.time.Duration.ofSeconds

class MarkupTest {
    @Test
    fun `Highlight simple Java`() {
        val highlighter = Highlighter(TestRendererFactory())
        val input = "import test;"
        val result = highlighter.highlight("java", input)
        assertTrue(result.result != input)
    }

    @TestFactory
    fun `Markup`() =
        File("src/test/resources/markup").listFiles().filter { hasLanguage(it.name) }.filter { it.isDirectory }.flatMap { file ->
            val lang: String = file.name
            file.listFiles().filter { it.name.contains(".expect.txt") }.map { expectFile ->
                val contentFile = File(expectFile.path.replace(".expect", ""))
                val testName = contentFile.nameWithoutExtension.replace(Regex("$lang-?"), "")
                DynamicTest.dynamicTest("Markup $lang-$testName") {
                    val content = contentFile.readText()
                    val expect = expectFile.readText()
                    val highlighter = Highlighter(TestRendererFactory())
                    val result = highlighter.highlight(lang, content, false)
                    assertEquals(
                        expect.trim { it <= ' ' },
                        result.result.toString().trim { it <= ' ' }
                    )
                }
            }
        }
}