package com.kotlinje.submit2.match

import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.network.repository.MatchRepositoryCallback
import com.kotlinje.submit2.presenter.PresenterMain
import com.kotlinje.submit2.view.MainView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by User on 24/05/2018.
 */
class TestMatch {
    @Mock
    private lateinit var view : MainView
    @Mock
    private lateinit var matchRepository: MatchRepository
    @Mock
    private lateinit var team: ResponseTeam
    @Mock
    private lateinit var presenterMain: PresenterMain

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenterMain = PresenterMain(view, matchRepository)
    }
    @Test
    fun getMatch(){

        val id = "4328"
        presenterMain.thisForTesting(id)
        argumentCaptor<MatchRepositoryCallback<ResponseTeam?>>().apply {

            verify(matchRepository).getLastMatch(eq(id),capture())
            firstValue.onDataLoaded(team)
        }
        Mockito.verify(view).showLoadingProgress()
        Mockito.verify(view).onDataLoaded(team)
        Mockito.verify(view).hideLoadingProgress()






    }



}