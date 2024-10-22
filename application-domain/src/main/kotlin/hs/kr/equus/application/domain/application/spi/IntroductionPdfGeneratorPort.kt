package hs.kr.equus.application.domain.application.spi

import hs.kr.equus.application.domain.application.model.Application

interface IntroductionPdfGeneratorPort {
    fun generate(application: List<Application>): ByteArray
}