package com.kishan.memeshare.domain.repository

import com.kishan.memeshare.data.remote.dto.MemeDto
import com.kishan.memeshare.data.remote.dto.MemeListDto



interface MemeRepository {
//    suspend fun getMeme():MemeDto

    suspend fun getMemes(): MemeListDto
}