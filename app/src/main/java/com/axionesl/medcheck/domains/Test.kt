package com.axionesl.medcheck.domains

data class Test (
    val id: String? = null,
    val weight: Double? = 0.0,
    val height: Double? = 0.0,
    val bpm: Double? = 0.0,
    val bloodPressure: Double? = 0.0,
    val glucoseLevel: Double? = 0.0,
    val oxygenLevel: Double? = 0.0,
    var status: String? = "In Queue",
    var checkedBy: String? = null,
    var patient: String? = null
)