package io.pomplan.domain

import io.pomplan.domain.Pomodoro.Type.*

fun pomodoroSequence(settings: PomodoroSettingsProvider) = generateSequence(
    seed = buildWorkPomodoro(blockIndex = 0, settings)
) { pomodoro ->
    when {
        pomodoro.type == LONG_BREAK -> buildWorkPomodoro(blockIndex = 0, settings)
        pomodoro.type == SHORT_BREAK -> buildWorkPomodoro(blockIndex = pomodoro.blockIndex + 1, settings)
        pomodoro.blockIndex == 2 * settings.blockSize - 2 -> buildLongBreakPomodoro(settings)
        else -> buildShortBreakPomodoro(blockIndex = pomodoro.blockIndex + 1, settings)
    }
}

private fun buildWorkPomodoro(blockIndex: Int, settings: PomodoroSettingsProvider) = Pomodoro(
    type = WORK,
    blockIndex = blockIndex,
    fullTime = settings.workTime,
)

private fun buildShortBreakPomodoro(blockIndex: Int, settings: PomodoroSettingsProvider) = Pomodoro(
    type = SHORT_BREAK,
    blockIndex = blockIndex,
    fullTime = settings.shortBreakTime,
)

private fun buildLongBreakPomodoro(settings: PomodoroSettingsProvider) = Pomodoro(
    type = LONG_BREAK,
    blockIndex = 2 * settings.blockSize - 1,
    fullTime = settings.longBreakTime,
)
