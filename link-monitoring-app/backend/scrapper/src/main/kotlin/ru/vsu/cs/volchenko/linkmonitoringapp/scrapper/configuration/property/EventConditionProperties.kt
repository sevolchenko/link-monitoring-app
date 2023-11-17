package ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.configuration.property

data class EventConditionProperties(
    // TODO validation
    val variable: String,
    val changeType: EventChangeType
) {
    enum class EventChangeType {
        INC,
        DEC
    }
}