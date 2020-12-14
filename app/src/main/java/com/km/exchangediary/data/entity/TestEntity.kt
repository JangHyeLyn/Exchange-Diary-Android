package com.km.exchangediary.data.entity

/*
{
  "code": 200,
  "message": [
    "daum",
    "naver"
  ],
  "status": "OK"
}
* */

data class GitRepo (
    val id: Long,
    val name: String,
    val html_url: String,
    val description: String,
    val stargazers_count: Long,
    val watchers_count: Long,
    val forks_count: Long
)