package com.kotlinje.submit2

import com.kotlinje.submit2.model.EventLiga
import com.kotlinje.submit2.model.ModelTeam
import com.kotlinje.submit2.model.ResponseTeam
import com.kotlinje.submit2.network.ApiRepos
import com.kotlinje.submit2.network.ServiceGetListLiga
import com.kotlinje.submit2.presenter.PresenterMain
import com.kotlinje.submit2.utility.TestContextProvider
import com.kotlinje.submit2.view.MainView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

/**
 * Created by User on 24/05/2018.
 */
class TestMatch {
    @Mock
    private lateinit var view : MainView
    @Mock
    private lateinit var apiRepos: ApiRepos
    @Mock
    private lateinit var retrofit: Retrofit
    @Mock
    private lateinit var serviceGetListLiga: ServiceGetListLiga
    @Mock
    private lateinit var presenterMain: PresenterMain

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenterMain = PresenterMain(view,TestContextProvider())
    }
    @Test
    fun getMatch(){
        val liga: MutableList<EventLiga> = mutableListOf()
        val response =  ResponseTeam()
        var ligaID : Int =4328


        presenterMain.getLast(ligaID)


        Mockito.verify(view).showLoadingProgress()
        Mockito.verify(view).showEventList(liga)
        Mockito.verify(view).hideLoadingProgress()

    }



}