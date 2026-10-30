package com.nanhesitare.app.ui

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.concurrent.thread
import kotlin.math.sin

object SoundEffect {
    fun playCheerfulChime() {
        thread {
            try {
                val sampleRate = 44100
                val durationMs = 600
                val numSamples = sampleRate * durationMs / 1000
                val buffer = ShortArray(numSamples)

                val freqs = listOf(523.25, 659.25, 783.99, 1046.50) // C5, E5, G5, C6 (Kid-like happy chime)
                for (i in 0 until numSamples) {
                    val time = i.toDouble() / sampleRate
                    val noteIdx = (time * freqs.size / (durationMs / 1000.0)).toInt().coerceIn(0, freqs.size - 1)
                    val freq = freqs[noteIdx]
                    val envelope = 1.0 - (i.toDouble() / numSamples)
                    val sample = (sin(2.0 * Math.PI * freq * time) * 32767 * envelope * 0.6).toInt()
                    buffer[i] = sample.toShort()
                }

                val track = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(buffer.size * 2)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()

                track.write(buffer, 0, buffer.size)
                track.play()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
