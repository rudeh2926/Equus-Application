package hs.kr.equus.application.global.feign.client

import hs.kr.equus.application.global.feign.client.dto.response.StatusInfoElement
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "StatusClient", url = "\${url.status}")
interface StatusClient {
    @GetMapping("/internal/status/list")
    fun getStatusList(): List<StatusInfoElement>

    @GetMapping("/internal/status/{receipt-code}")
    fun getStatusByReceiptCode(@PathVariable("receipt-code") receiptCode: Long): StatusInfoElement?

    @PutMapping("/internal/status/{receipt-code}/exam-code")
    fun updateExamCode(@PathVariable("receipt-code") receiptCode: Long, @RequestParam examCode: String)
}

//@Component
//class StatusFallback(
//     private val statusPort: StatusPort
//) : StatusClient {
//    override fun getStatusList(): List<StatusInfoElement> {
//        return ArrayList()
//    }

//    override fun getStatusByReceiptCode(receiptCode: Long): StatusInfoElement? {
//        val status = statusPort.queryStatusByReceiptCodeInCache(receiptCode)
//        return status?.let {
//            StatusInfoElement(
//                id = it.receiptCode,
//                isPrintsArrived = it.isPrintsArrived,
//                isSubmitted = it.isSubmitted,
//                examCode = it.examCode,
//                isFirstRoundPass = it.isFirstRoundPass,
//                isSecondRoundPass = it.isSecondRoundPass,
//                receiptCode = it.receiptCode
//            )
//        } ?: StatusInfoElement(
//            id = 0,
//            isPrintsArrived = false,
//            isSubmitted = false,
//            examCode = null,
//            isFirstRoundPass = false,
//            isSecondRoundPass = false,
//            receiptCode = receiptCode
//        )
//    }
//}