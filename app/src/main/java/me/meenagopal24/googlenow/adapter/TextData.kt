package me.meenagopal24.googlenow.adapter

import android.speech.tts.Voice

data class TextData(val text : String , val heading : String , var voice: Voice? = null) {

}
