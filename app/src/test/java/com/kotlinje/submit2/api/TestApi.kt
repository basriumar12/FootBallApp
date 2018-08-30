package com.kotlinje.submit2

import com.kotlinje.submit2.network.ApiRepos
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by User on 24/05/2018.
 */
class TestApi {

    @Test
    fun testRetro (){
        val apiRepos = mock (ApiRepos::class.java)
        var ligaID : Int =4328
        apiRepos.getLast(ligaID)
        verify(apiRepos).getLast(ligaID)
    }
}