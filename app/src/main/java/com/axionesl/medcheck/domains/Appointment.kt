package com.axionesl.medcheck.domains

data class Appointment(
    val id: String? = null,
    val doctorName: String? = null,
    val patientName: String? = null,
    val patientNumber: String? = null,
    val date: String? = null,
    val time: String? = null,
    var status: String? = "Not Reviewed",
    var doctorStatus: String? = null
)