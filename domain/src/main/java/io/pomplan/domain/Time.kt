package io.pomplan.domain

@JvmInline
value class Time(private val millis: Long = 0L) : Comparable<Time> {
    constructor(minutes: Int = 0, seconds: Int = 0, milliseconds: Int = 0) :
        this(millis = minutes.minutes + seconds.seconds + milliseconds.milliseconds)

    operator fun plus(other: Time) = Time(millis = this.millis + other.millis)
    operator fun minus(other: Time) = Time(millis = maxOf(0, this.millis - other.millis))

    override fun compareTo(other: Time) = this.millis.compareTo(other.millis)
}
