package com.example.currencyconverterdemo

import android.app.Application
import android.content.Context
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito.mock

/**
 * Created by Darshan Patel
 * Usage: Testing Koin integration in application
 * How to call: Run this class separately
 * Useful parameter: mock context for creation of koin modules(application context,activity,context)
 */
class KoinCheck : KoinTest {

    companion object {
        const val URL = "URL"
    }

    private val SampleModule = module {
        single { AndroidComponentA(androidContext()) }
        single { AndroidComponentB(get()) }
        single { AndroidComponentC(androidApplication()) }
        single { OtherService(getProperty(URL)) }
    }

    class AndroidComponentA(val androidContext: Context)
    class AndroidComponentB(val androidComponent: AndroidComponentA)
    class AndroidComponentC(val application: Application)
    class OtherService(val url: String)

    @Test
    fun `should inject with android context`() {
        val mockedContext = mock(Context::class.java)

        val koin = koinApplication {
            androidContext(mockedContext)
            modules(SampleModule)
        }.koin

        koin.get<AndroidComponentA>()
    }

    @Test
    fun `should inject with android application`() {
        val mockedContext = mock(Application::class.java)

        val koin = koinApplication {
            androidContext(mockedContext)
            modules(SampleModule)
        }.koin

        koin.get<AndroidComponentC>()
    }

    @Test
    fun `should make DI with serveral components`() {
        val mockedContext = mock(Context::class.java)

        val koin = koinApplication {
            androidContext(mockedContext)
            modules(SampleModule)
        }.koin

        val a = koin.get<AndroidComponentA>()
        val b = koin.get<AndroidComponentB>()

        assertEquals(a, b.androidComponent)
    }

    @Test
    fun `should inject property`() {
        val value = "URL"
        val koin = koinApplication {
            properties(hashMapOf(URL to value))
            modules(SampleModule)
        }.koin

        val s = koin.get<OtherService>()

        assertEquals(value, s.url)
    }
}