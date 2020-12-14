package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.entity.GitRepo
import com.km.exchangediary.data.repository.TestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class TestUseCase(private val testRepository: TestRepository): CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()

    suspend fun get(flag: Int): String {
        val a: List<GitRepo> = withContext(coroutineContext) {
            testRepository.getFlatFormList()
        }
        return if (flag >= 17) {
            a[16].name
        } else {
            a[flag].name
        }
    }
}