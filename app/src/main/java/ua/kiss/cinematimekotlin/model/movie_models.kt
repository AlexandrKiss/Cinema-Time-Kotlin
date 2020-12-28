package ua.kiss.cinematimekotlin.model

data class Movie(
        val id: Int,
        val name: String,
        val genre: String,
        val duration: Int,
        val img: String,
        val trailer: String,
        val dateSessions: List<DateSession>)

data class DateSession(val id: Int, val date: String, val sessions: List<Session>)

data class Session(val id: Int, val time: String, val hall: Hall)

data class Hall(val id: Int, val seats: List<Seat>)

data class Seat(val id: Int, val num: Int, val row: Int, val type: Type)

data class Type(val id: Int, val name: String, val price: Double)