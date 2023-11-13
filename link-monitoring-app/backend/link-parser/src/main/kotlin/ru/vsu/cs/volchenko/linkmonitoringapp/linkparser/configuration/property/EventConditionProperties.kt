package ru.vsu.cs.volchenko.linkmonitoringapp.linkparser.configuration.property

data class EventConditionProperties(
        var variable: String,
        var changeType: EventChangeType
) {
    enum class EventChangeType {
        INC,
        DEC
    }
}