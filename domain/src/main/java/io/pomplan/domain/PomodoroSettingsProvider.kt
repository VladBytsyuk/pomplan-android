package io.pomplan.domain

interface PomodoroSettingsProvider {
    val workTime: Time
    val shortBreakTime: Time
    val longBreakTime: Time
    val fullPomodoroWorkCount: Int
}
