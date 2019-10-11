package ch.deletescape.highlight.core

import ch.deletescape.highlight.core.Highlighter.Companion.hasLanguage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.io.File

/**
 * Created by Sergej Kravcenko on 5/17/2017.
 * Copyright (c) 2017 Sergej Kravcenko
 */
class HighlighterTest {
    @Test
    fun `Highlight simple Java`() {
        val highlighter = Highlighter(TestRendererFactory())
        val input = "import test;"
        val result = highlighter.highlight("java", input)
        assertTrue(result.result != input)
    }

    @Disabled //TODO: we currently stalls completely for some import
    @TestFactory
    fun `Detect`() =
        File("src/test/resources/detect").listFiles().filter { hasLanguage(it.name) }.filter { it.isDirectory }.flatMap { file ->
            val lang = file.name
            file.listFiles().map { example ->
                DynamicTest.dynamicTest("Detect $lang-${example.nameWithoutExtension}") {
                    val content = example.readText()
                    val highlighter = Highlighter(TestRendererFactory())
                    val result = highlighter.highlightAuto(content, null, false)
                    assertEquals(lang, result.language)
                }
            }
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