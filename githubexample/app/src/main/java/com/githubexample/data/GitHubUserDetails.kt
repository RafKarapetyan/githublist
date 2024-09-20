package com.githubexample.data

data class GitHubUserDetails(
    val login: String,
    val name: String?,
    val avatar_url: String,
    val location: String?,
    val followers: Int,
    val following: Int,
    val bio: String?,
    val company: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val updated_at: String?
)