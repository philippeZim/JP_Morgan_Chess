// 1. Define the dependency (Logger trait)
trait Logger:
    def log(message: String): Unit

// 2. Implement the dependency (ConsoleLogger)
class ConsoleLogger extends Logger:
    def log(message: String): Unit = println(s"CONSOLE: $message")


class DoubleConsoleLogger extends Logger:
    def log(message: String): Unit = {
        println(s"CONSOLE: $message")
        println(s"CONSOLE: $message")
    }

// 3. Define a service that needs the dependency (UserService)
class UserService:
    def registerUser(name: String)(using logger: Logger): Unit =
        logger.log(s"Registering user: $name\n")
// ... actual user registration logic ...


// 4. Provide the dependency using `given`
given logger1: Logger = new ConsoleLogger

val userService = new UserService()
userService.registerUser("Bob")
/*
given logger2: Logger = new DoubleConsoleLogger

// 5. Use the service, and the dependency is automatically injected
val userService2 = new UserService()
userService2.registerUser("Alice")
*/
