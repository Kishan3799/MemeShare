package com.kishan.memeshare.domain.use_case

import com.kishan.memeshare.common.Resources
import com.kishan.memeshare.data.remote.dto.toMemesList
import com.kishan.memeshare.domain.model.MemesList
import com.kishan.memeshare.domain.repository.MemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMemesUseCase @Inject constructor(
    private val repository: MemeRepository
) {
    operator fun invoke(): Flow<Resources<MemesList>> = flow {
        try {
            emit(Resources.Loading())
            val memes = repository.getMemes().toMemesList()
            emit(Resources.Success(memes))
        }catch ( e:HttpException){
            emit(Resources.Error(e.localizedMessage ?: "Unexpected Error Occured"))
        }catch (e:IOException){
            emit(Resources.Error(e.localizedMessage ?: "Couldn't reach the server.Please! check your internet connection"))
        }
    }
}