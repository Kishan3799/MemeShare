package com.kishan.memeshare.data.repository

import com.kishan.memeshare.data.remote.MemeApi
import com.kishan.memeshare.data.remote.dto.MemeDto
import com.kishan.memeshare.data.remote.dto.MemeListDto

import com.kishan.memeshare.domain.repository.MemeRepository
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    private val api: MemeApi
):MemeRepository {
//    override suspend fun getMeme(): MemeDto {
//        return api.getMeme()
//    }

    override suspend fun getMemes(): MemeListDto {
        return api.getMemes()
    }

}