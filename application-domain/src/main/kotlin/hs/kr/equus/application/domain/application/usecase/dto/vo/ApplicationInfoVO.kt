package hs.kr.equus.application.domain.application.usecase.dto.vo

import hs.kr.equus.application.domain.application.model.Application
import hs.kr.equus.application.domain.applicationCase.model.GraduationCase
import hs.kr.equus.application.domain.graduationInfo.model.Graduation
import hs.kr.equus.application.domain.school.model.School
import hs.kr.equus.application.domain.score.model.Score

data class ApplicationInfoVO (
    val application: Application,
    val graduation: Graduation? = null,
    val graduationCase: GraduationCase? = null,
    val score: Score? = null,
    val school: School? = null
)