package hs.kr.equus.application.global.util.pdf

import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider
import com.itextpdf.io.font.FontProgramFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class ConverterPropertiesCreator {

    @Value("\${font.path}")
    private lateinit var fontPath: String

    fun createConverterProperties(): ConverterProperties {
        val properties = ConverterProperties()
        val fontProvider = DefaultFontProvider(false, false, false)

        Font.fonts.forEach { font ->
            try {
                val fontProgram = FontProgramFactory.createFont("$fontPath$font")
                fontProvider.addFont(fontProgram)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        properties.setFontProvider(fontProvider)
        return properties
    }
}
