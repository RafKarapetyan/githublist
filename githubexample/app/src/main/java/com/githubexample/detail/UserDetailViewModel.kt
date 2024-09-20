package com.githubexample.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githubexample.data.GitHubUser
import com.githubexample.data.GitHubUserDetails
import com.githubexample.retrofit.RetrofitInstance.api
import kotlinx.coroutines.launch


class UserDetailViewModel : ViewModel() {
    private val _userDetail: MutableLiveData<GitHubUserDetails> = MutableLiveData()
    val userDetail: LiveData<GitHubUserDetails> get() = _userDetail

    fun getUserDetails(userName:String) {
        viewModelScope.launch {
            api.getUserDetails(userName).let {
                _userDetail.postValue(it)
            }
        }
    }
}