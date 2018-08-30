package com.kotlinje.submit2.utility

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by User on 24/05/2018.
 */
open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}