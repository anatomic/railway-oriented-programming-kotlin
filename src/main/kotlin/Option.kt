/*
    Complete the option class...

    Follow the instructions in src/test/OptionTest.kt
*/
sealed class Option<out A> {
    abstract fun <B> map(f: (A) -> B): Option<B>
    abstract fun <B> flatMap(f: (A) -> Option<B>): Option<B>
    abstract fun filter(f: (A) -> Boolean): Option<A>
    fun <B> fold(ifNone: () -> B, ifSome: (A) -> B): B = when (this) {
        is Some<A> -> ifSome(this.value)
        else -> ifNone()
    }

    abstract fun <B> flatTap(f: (A) -> Option<B>): Option<A>

    data class Some<A>(val value: A) : Option<A>() {
        override fun <B> map(f: (A) -> B) = Some(f(this.value))
        override fun <B> flatMap(f: (A) -> Option<B>) = f(this.value)
        override fun filter(f: (A) -> Boolean) = if (f(this.value)) {
            this
        } else {
            None
        }

        override fun <B> flatTap(f: (A) -> Option<B>): Option<A> = this.also { f(this.value) }
    }

    object None : Option<Nothing>() {
        override fun <B> map(f: (Nothing) -> B) = None
        override fun <B> flatMap(f: (Nothing) -> Option<B>) = None
        override fun filter(f: (Nothing) -> Boolean) = None
        override fun <B> flatTap(f: (Nothing) -> Option<B>): Option<Nothing> = None
    }
}


