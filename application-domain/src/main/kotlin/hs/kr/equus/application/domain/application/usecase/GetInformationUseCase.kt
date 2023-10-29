package hs.kr.equus.application.domain.application.usecase

import hs.kr.equus.application.domain.application.spi.QueryApplicationPort
import hs.kr.equus.application.domain.application.usecase.dto.response.GetInformationResponse
import hs.kr.equus.application.global.annotation.ReadOnlyUseCase
import hs.kr.equus.application.global.photo.spi.PhotoPort
import hs.kr.equus.application.global.security.spi.SecurityPort

@ReadOnlyUseCase
class GetInformationUseCase(
    private val securityPort: SecurityPort,
    private val queryApplicationPort: QueryApplicationPort,
    private val photoPort: PhotoPort,
) {
    fun execute(): GetInformationResponse {
        val userId = securityPort.getCurrentUserId()
        val application = queryApplicationPort.queryApplicationByUserId(userId)

        return application.run {
            GetInformationResponse(
                sex = sex,
                birthDate = birthDate,
                photoUrl = photoFileName?.let { photoPort.getPhotoUrl(it) },
                applicantName = applicantName,
                applicantTel = applicantTel,
                parentName = parentName,
                parentTel = parentTel,
                streetAddress = streetAddress,
                postalCode = postalCode,
                detailAddress = detailAddress,
            )
        }
    }
}
