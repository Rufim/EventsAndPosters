package ru.kazantsev.eventsandposters.ui.datasource

interface ProgressNotifierCallback {
    fun onStartLoad()
    fun onFinishLoad()
    fun onError()
}