package com.example.githubapp.core.data.source.remote
import com.example.githubapp.core.data.source.remote.network.ApiResponse
import com.example.githubapp.core.data.source.remote.network.ApiService
import com.example.githubapp.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        const val PAGE: Int = 1
        const val PER_PAGE: Int = 30
    }

    suspend fun getAllUsers(): Flow<ApiResponse<List<UserResponse>>> {
        return flow<ApiResponse<List<UserResponse>>> {
            try {
                val response = apiService.getSearchUser("language:", PAGE, PER_PAGE)
                val userList = response.items
                if (userList.isNotEmpty())
                    emit(ApiResponse.Success(response.items))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchUsers(query: String): Flow<ApiResponse<List<UserResponse>>> {
        return flow<ApiResponse<List<UserResponse>>> {
            try {
                val response = apiService.getSearchUser(query, PAGE, PER_PAGE)
                val userList = response.items
                if (userList.isNotEmpty())
                    emit(ApiResponse.Success(response.items))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollowers(username: String): Flow<ApiResponse<List<UserResponse>>> {
        return flow<ApiResponse<List<UserResponse>>> {
            try {
                val response = apiService.getFollowers(username, PAGE, PER_PAGE)
                if (response.isNotEmpty())
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollowing(username: String): Flow<ApiResponse<List<UserResponse>>> {
        return flow<ApiResponse<List<UserResponse>>> {
            try {
                val response = apiService.getFollowing(username, PAGE, PER_PAGE)
                if (response.isNotEmpty())
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getDetailUser(username: String): Flow<ApiResponse<UserResponse>> {
        return flow<ApiResponse<UserResponse>> {
            try {
                val response = apiService.getDetailUser(username)
                if (response != null)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
