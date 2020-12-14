package com.km.exchangediary.data.repository

import com.km.exchangediary.data.entity.GitRepo
import com.km.exchangediary.data.remote.datasource.TestDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class TestRepository(private val testDataSource: TestDataSource): CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    suspend fun getFlatFormList(): List<GitRepo> =
        withContext(coroutineContext) {
            testDataSource.getGitRepoList()
        }

}