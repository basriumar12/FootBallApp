package com.kotlinje.submit2.detail

import com.kotlinje.submit2.model.EventLiga
import com.kotlinje.submit2.model.ModelTeam
import com.kotlinje.submit2.model.ResponseTeam
import com.kotlinje.submit2.network.ApiRepos
import com.kotlinje.submit2.network.ServiceGetListLiga
import com.kotlinje.submit2.network.repository.DetailRepository
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.network.repository.MatchRepositoryCallback
import com.kotlinje.submit2.presenter.PresenterDetail
import com.kotlinje.submit2.presenter.PresenterMain
import com.kotlinje.submit2.utility.TestContextProvider
import com.kotlinje.submit2.view.DetailView
import com.kotlinje.submit2.view.MainView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
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
class TestDetail {
    @Mock
    private lateinit var view : DetailView
    @Mock
    private lateinit var detailRepository: DetailRepository
    @Mock
    private lateinit var team: ModelTeam
    @Mock
    private lateinit var presenterDetail: PresenterDetail

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenterDetail = PresenterDetail(view, detailRepository)
    }
    @Test
    fun getDetail(){

        val id = "4328"
        presenterDetail.forTesting(id)
        argumentCaptor<DetailRepositoryCallback<ResponseTeam?>>().apply {

            com.nhaarman.mockito_kotlin.verify(detailRepository).getImgAway(eq(id), capture())
            firstValue.onDataLoaded(team)
        }
        Mockito.verify(view).showLoadingProgress()
        Mockito.verify(view).onDataLoaded(team)
        Mockito.verify(view).hideLoadingProgress()






    }



}