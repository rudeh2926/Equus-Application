package hs.kr.equus.application.domain.application.usecase

import hs.kr.equus.application.domain.application.spi.IntroductionPdfGeneratorPort
import hs.kr.equus.application.domain.application.spi.QueryApplicationPort
import hs.kr.equus.application.global.annotation.ReadOnlyUseCase

@ReadOnlyUseCase
class GetIntroductionPdfUseCase(
    private val applicationPort: QueryApplicationPort,
    private val introductionPdfGeneratorPort: IntroductionPdfGeneratorPort
) {
    fun execute(): ByteArray {
        val applicationList = applicationPort.queryAllFirstRoundPassedApplication()
        return introductionPdfGeneratorPort.generate(applicationList)
    }

}