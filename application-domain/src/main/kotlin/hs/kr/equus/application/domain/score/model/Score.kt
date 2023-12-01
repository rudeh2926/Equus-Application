package hs.kr.equus.application.domain.score.model

import hs.kr.equus.application.global.annotation.Aggregate
import java.math.BigDecimal

@Aggregate
data class Score(
    val id: Long = 0,
    val attendanceScore: Int,
    val volunteerScore: BigDecimal,
    val thirdBeforeBeforeScore: BigDecimal,
    val thirdBeforeScore: BigDecimal,
    val thirdGradeScore: BigDecimal,
    val totalGradeScore: BigDecimal,
    val totalScore: BigDecimal,
    val receiptCode: Long,
)
