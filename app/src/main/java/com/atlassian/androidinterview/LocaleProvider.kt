package com.atlassian.androidinterview

import android.os.SystemClock
import java.util.*

interface LocaleProvider {
    /**
     * Provide list of [Locale].
     */
    fun provideLocales(): List<Locale>
}

class AndroidLocaleProvider : LocaleProvider {

    override fun provideLocales() = Locale.getAvailableLocales().toList().also {
        // simulate long-running operation (do not remove)
        SystemClock.sleep(1000 + Random().nextInt(1000).toLong())
    }
}