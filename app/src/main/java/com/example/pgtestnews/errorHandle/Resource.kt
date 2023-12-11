package com.example.pgtestnews.errorHandle

sealed class Resource <T> (val data : T?=null , val messgage : String?=null){

    class Success <T> (data: T?) : Resource<T>(data = data)

    class Error <T> (messgage: String?) : Resource<T>(messgage = messgage)

}
