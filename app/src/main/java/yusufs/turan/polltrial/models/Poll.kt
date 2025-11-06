package yusufs.turan.polltrial.models

data class Poll(
    val id: String = "",
    val question: String = "",
    val options: List<PollOption> = emptyList()
)

data class PollOption(
    val id: String = "",
    val text: String = "",
    val votes: Int = 0
)