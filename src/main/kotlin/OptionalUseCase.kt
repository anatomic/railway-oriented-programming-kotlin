package ExecuteUseCaseWithOption

import Option

fun executeUseCase(requestId: Int): Option<String> = receiveRequest(requestId)
    .map { canonicalizeEmail(it) }
    .filter { validateRequest(it) }
    .flatMap { db.updateDbFromRequest(it) }
    .flatTap { smtpServer.sendEmail(it.email) }
    .map { "OK" }

data class Request(var email: String, val firstName: String, val surname: String, val id: Int)

fun receiveRequest(requestId: Int) =
    when (val item = validRequests.find { it.id == requestId }) {
        is Request -> Option.Some(item)
        else -> Option.None
    }

val validRequests = listOf(
    Request("", "Steve", "Smith", 111),
    Request("", "Eve", "Jones", 222),
    Request("", "Chris", "Piper", 333),
    Request("", "", "Wilson", 444),
    Request("", "Prince", "", 555),
    Request("", "Beel", "Zebub", 666),
    Request("", "Prince", "", 7777)
)

fun canonicalizeEmail(request: Request) =
    request.copy(email = "${request.firstName}.${request.surname}@railway.com")

fun validateRequest(request: Request): Boolean =
    with(request) {
        email.isNotEmpty() && firstName.isNotEmpty() && surname.isNotEmpty()
    }

val db = Database()

class Database {
    fun updateDbFromRequest(request: Request) =// not a nice DB, we only persist even Request ids!
        if (request.id % 2 == 0)
            Option.Some(request)
        else
            Option.None
}

val smtpServer = SMTPServer()

class SMTPServer {
    fun sendEmail(email: String) =
        if ("\\w{1,}\\.\\w{1,}@railway.com$".toRegex().matches(email))
            Option.Some(email) // email sent
        else
            Option.None // email not sent :( not in correct format
}

val log = Log()

class Log {
    fun error(s: String) = System.err.println(s)
}






