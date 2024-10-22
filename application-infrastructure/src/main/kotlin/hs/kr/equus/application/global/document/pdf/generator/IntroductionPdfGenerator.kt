package hs.kr.equus.application.global.document.pdf.generator

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.utils.PdfMerger
import com.itextpdf.layout.Document
import hs.kr.equus.application.domain.application.model.Application
import hs.kr.equus.application.domain.application.spi.IntroductionPdfGeneratorPort
import hs.kr.equus.application.global.document.pdf.data.IntroductionPdfConverter
import hs.kr.equus.application.global.document.pdf.data.TemplateFileName
import hs.kr.equus.application.global.document.pdf.facade.PdfDocumentFacade
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream

@Component
class IntroductionPdfGenerator (
    private val pdfProcessor: PdfProcessor,
    private val introductionPdfConverter: IntroductionPdfConverter,
    private val templateProcessor: TemplateProcessor,
    private val pdfDocumentFacade: PdfDocumentFacade
): IntroductionPdfGeneratorPort {

    override fun generate(applicationList: List<Application>): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val mergedDocument = PdfDocument(PdfWriter(outputStream))
        val pdfMerger = PdfMerger(mergedDocument)

        applicationList.forEach { application ->
            val pdfStream = generateIntroductionPdf(application)
            val pdfDoc = pdfDocumentFacade.getPdfDocument(pdfStream)
            mergeDocument(pdfMerger, pdfDoc)
        }

        mergedDocument.close()
        return outputStream.toByteArray()
    }

    private fun generateIntroductionPdf(application: Application): ByteArrayOutputStream {
        val data = introductionPdfConverter.execute(application)
        val template = TemplateFileName.ADMIN_INTRODUCTION

        val html = templateProcessor.convertTemplateIntoHtmlString(template, data.toMap())
        val pdfStream = pdfProcessor.convertHtmlToPdf(html)
        return pdfStream
    }

    private fun mergeDocument(merger: PdfMerger, document: PdfDocument?) {
        if (document != null) {
            merger.merge(document, 1, document.numberOfPages)
            document.close()
        }
    }
}
