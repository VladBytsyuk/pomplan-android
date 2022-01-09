package io.pomplan.domain

class Pomodoro(
    val type: Type,
    val blockIndex: Int,
    val fullTime: Time,
    val elapsedTime: Time = Time(millis = 0L),
) {
    enum class Type { WORK, SHORT_BREAK, LONG_BREAK }

    val restTime: Time get() = fullTime - elapsedTime
    val isCompleted: Boolean get() = restTime.isEmpty

    operator fun plus(time: Time) = Pomodoro(
        type = this.type,
        blockIndex = this.blockIndex,
        fullTime = this.fullTime,
        elapsedTime = this.elapsedTime + time
    )
}
