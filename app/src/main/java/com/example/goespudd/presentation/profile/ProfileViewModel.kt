package com.example.goespudd.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goespudd.data.model.Profile

class ProfileViewModel : ViewModel() {

    val profileData = MutableLiveData(
        Profile(
            username = "Deny Iqbal Mubarok",
            email = "denyiqbalmubarok773@gmail.com",
            phoneNumber = "081212345678",
            imgProfile = "https://avatars.githubusercontent.com/u/101798303?v=4"
        )
    )

    val isEditMode = MutableLiveData(false)

    fun changeEditMode() {
        val currentValue = isEditMode.value ?: false
        isEditMode.postValue(!currentValue)
    }
}