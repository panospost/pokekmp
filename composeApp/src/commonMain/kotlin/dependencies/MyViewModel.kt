package dependencies

import androidx.lifecycle.ViewModel

class MyViewModel(
    private val myRepository: MyRepository
): ViewModel() {

    fun getHelloString(): String = myRepository.helloWorld()
}