package ch.deletescape.highlight.core

import ch.deletescape.highlight.core.Highlighter.Companion.hasLanguage
import java.io.File
import java.time.Duration.ofSeconds
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertTimeoutPreemptively

class DetectTest {

    @TestFactory
    fun `Detect`() =
        File("src/test/resources/detect").listFiles().filter { hasLanguage(it.name) }.filter { it.isDirectory }.flatMap { file ->
            val lang = file.name
            file.listFiles().map { example ->
                DynamicTest.dynamicTest("Detect $lang-${example.nameWithoutExtension}") {
                    val result = assertTimeoutPreemptively(ofSeconds(3)) {
                        val content = example.readText()
                        val highlighter = Highlighter(TestRendererFactory())
                        highlighter.highlightAuto(content, null, false)
                    }
                    assertEquals(lang, result.language)
                }
            }
        }
}

