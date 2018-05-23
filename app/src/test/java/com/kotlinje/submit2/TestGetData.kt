package com.kotlinje.submit2

import com.kotlinje.submit2.model.ResponseTeam
import com.kotlinje.submit2.presenter.PresenterMain
import com.kotlinje.submit2.view.MainView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by User on 23/05/2018.
 */
class TestGetData {

        @Mock
        private lateinit var view : MainView
        @Mock
        private lateinit var presenterMain: PresenterMain
        @Mock
        private lateinit var responseTeam: ResponseTeam

        @Before
        fun setUp() {
                MockitoAnnotations.initMocks(this)
              //  presenterMain = PresenterMain(view)
        }

        @Test
        fun getLiga(){

        }



}