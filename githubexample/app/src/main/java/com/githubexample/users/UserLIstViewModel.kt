package com.githubexample.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githubexample.data.GitHubUser
import com.githubexample.retrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class UserLIstViewModel:ViewModel() {

    private val _users: MutableLiveData<List<GitHubUser>> = MutableLiveData()
    val users:LiveData<List<GitHubUser>> get() = _users

    fun getGithubUsers(){
        viewModelScope.launch{
            api.getUsers().let {
                println(it)
                _users.postValue(it.take(30))
            }
        }
    }
}