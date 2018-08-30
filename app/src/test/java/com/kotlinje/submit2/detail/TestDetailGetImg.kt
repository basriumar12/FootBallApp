package com.kotlinje.submit2.detail

import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.network.repository.DetailRepository
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback
import com.kotlinje.submit2.presenter.PresenterDetail
import com.kotlinje.submit2.view.DetailView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by User on 24/05/2018.
 */
class TestDetailGetImg {
    @Mock
    private lateinit var view : DetailView
    @Mock
    private lateinit var detailRepository: DetailRepository
    @Mock
    private lateinit var team: ResponseTeam
    @Mock
    private lateinit var teams: ModelTeam
    @Mock
    private lateinit var presenterDetail: PresenterDetail

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenterDetail = PresenterDetail(view, detailRepository)
    }
    @Test
    fun getDetailImg(){

        val id = "133604"
        presenterDetail.forTesting(id)
        argumentCaptor<DetailRepositoryCallback<ModelTeam?>>().apply {

            com.nhaarman.mockito_kotlin.verify(detailRepository).getImgAway(eq(id),capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoadingProgress()
        Mockito.verify(view).hideLoadingProgress()






    }



}