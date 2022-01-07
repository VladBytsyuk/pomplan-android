package io.pomplan.domain

@JvmInline
value class Time(private val millis: Long = 0L) : Comparable<Time> {
    constructor(minutes: Int = 0, seconds: Int = 0, milliseconds: Int = 0) :
        this(millis = minutes.minutes + seconds.seconds + milliseconds.milliseconds)

    init {
        require(millis >= 0L) { "Time can't have negative millis." }
    }

    operator fun plus(other: Time) = Time(this.millis + other.millis)
    operator fun minus(other: Time) = Time(maxOf(0, this.millis - other.millis))

    override fun toString(): String {
        val minutes = millis / MILLIS_IN_MINUTE
        val seconds = millis % MILLIS_IN_MINUTE / MILLIS_IN_SECOND
        val millis = millis % MILLIS_IN_SECOND
        return "$minutes:$seconds.$millis"
    }

    override fun compareTo(other: Time) = this.millis.compareTo(other.millis)
}
