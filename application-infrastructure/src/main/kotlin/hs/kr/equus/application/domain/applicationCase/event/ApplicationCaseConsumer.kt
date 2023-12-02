package hs.kr.equus.application.domain.applicationCase.event

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.application.domain.applicationCase.usecase.ChangeApplicationCaseUseCase
import hs.kr.equus.application.global.kafka.config.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ApplicationCaseConsumer(
    private val mapper: ObjectMapper,
    private val changeApplicationCaseUseCase: ChangeApplicationCaseUseCase,
) {
    @KafkaListener(
        topics = [KafkaTopics.UPDATE_EDUCATIONAL_STATUS],
        groupId = "change-application-case",
        containerFactory = "kafkaListenerContainerFactory",
    )
    fun changeApplicationCase(message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        changeApplicationCaseUseCase.execute(receiptCode)
    }
}
