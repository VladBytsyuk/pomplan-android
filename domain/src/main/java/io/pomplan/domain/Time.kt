package io.pomplan.domain

private const val MILLIS_IN_SECOND = 1000L
private const val SECONDS_IN_MINUTE = 60L
private const val MILLIS_IN_MINUTE = MILLIS_IN_SECOND * SECONDS_IN_MINUTE

@JvmInline
value class Time(private val millis: Long = 0L) : Comparable<Time> {
    constructor(
        minutes: Int = 0,
        seconds: Int = 0,
        milliseconds: Int = 0
    ) : this(millis = minutes * MILLIS_IN_MINUTE + seconds * MILLIS_IN_SECOND + milliseconds)

    init {
        require(millis >= 0L) { "Time can't have negative millis." }
    }

    operator fun plus(other: Time) = Time(this.millis + other.millis)
    operator fun minus(other: Time) = Time(maxOf(0, this.millis - other.millis))

    override fun toString() = "$minutes:$seconds.$milliseconds"

    override fun compareTo(other: Time) = this.millis.compareTo(other.millis)

    val minutes: Int get() = (millis / MILLIS_IN_MINUTE).toInt()
    val seconds: Int get() = (millis % MILLIS_IN_MINUTE / MILLIS_IN_SECOND).toInt()
    val milliseconds: Int get() = (millis % MILLIS_IN_SECOND).toInt()
    val totalMilliseconds: Long get() = millis

    val isEmpty: Boolean get() = millis == 0L
}
