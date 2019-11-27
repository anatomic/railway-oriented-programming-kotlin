import io.kotlintest.assertSoftly
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

/*
                         _-====-__-======-__-========-_____-============-__
                       _(                                                 _)
                    OO(        Welcome to                                  )_
                   0  (_          Railway-Oriented-Programming              _)
                 o0     (_                                                _)
                o         '=-___-===-_____-========-___________-===-_--='
              .o                                _________
             . ______          ______________  |         |      _____
           _()_||__|| ________ |            |  |_________|   __||___||__
          (         | |      | |            | __Y______00_| |_         _|
         /-OO----OO""="OO--OO"="OO--------OO"="OO-------OO"="OO-------OO"=P
        #####################################################################


    Hopefully you remember the "one-track" type of function from the talk.

    It's a function that is is successful and yields a result, or, it fails an you get nothing. Nada. Zip.

    We represent this type of computation in a data type known as Option, but you may have also seen it called: "Maybe"
    or "Optional". As we're in Kotlin land, we're calling it Option because thats what the Kotlin' folk like to call it.

    Are you ready? ALL ABOARD...

    You'll Implement "Option" as a sealed Class to represent a datatype which may or may not have a value.

    To get started uncomment the code in the first test, implement the code in the Option class, and
    then move on.

    Scroll down for hint/s tips! if you get stuck.

    You'll need a "Some" class and a "None" class inside Option to cope with the cases when you have data or when you
    don't.

    If you get stuck check the hints and tips below the tests, if you don't want any help, don't scroll too far! :)
*/

class Part1_CreateOption : StringSpec({

    "Create Option with Optional.Some(...) " {

        // uncomment the lines below, and make them compile!

        val some1: Option<Int> = Option.Some(1)

        val someA: Option<Char> = Option.Some('a')

        val someStr: Option<String> = Option.Some("Choo-choo!")
    }

    /*  ~~~~~ HINTS / TIPS ~~~~~

    a) The Some class needs to be a "data class", inside the Option class.

    b) the Generic type will be <out A>

    c) The Some class needs to have one instance variable, of type A, make the variable private.

    If you're still stuck don't worry check the solution folder and see if it makes sense.
    Copy over the solution and move on, don't sweat it.

    --------------------------------------------------------------------

    2. Cool we can make a Some-thing, but can we make nothing?
    */
    "Create an empty Option with Optional.None " {

        // uncomment the lines below, and make them compile!

        val none: Option<Int> = Option.None

        val some1: Option<Int> = Option.None

        val someA: Option<Char> = Option.None

        val someStr: Option<String> = Option.None
    }

    /*   ~~~~~ HINTS / TIPS ~~~~~

    a) make None as an 'object' in Optional, (as opposed as a class or data class)

    b) The None object will have the type: Option<Nothing>

    If you're still stuck don't worry check the solution folder and see if it makes sense.
    Copy over the solution and move on, don't sweat it.

    --------------------------------------------------------------------

    ALRIGHT! We defined an optional, high5!

    we can match on it using a when...
    */

    "Just an example of pattern matching" {

        // When you get here, just uncomment the lines, and see some pattern matching on your Option data type

        val choo: Option<String> = Option.Some("SUCCESS!")

        when (choo) {
            is Option.Some -> println("Result was ${choo.value})")
            is Option.None -> println("Result a complete failure :'( ")
        }

        /*
            This might seem familiar, you might have implemented similar sealed classes?

            It's good, but it's only our first stop! There's plenty more cool stuff to come, our next station is "map"!
         */
    }

    /*
    3.  Map is handle little function that you'll have no doubt used in Kotlin already on lists and other types.

    So lets implement it in our Option data type.

    Map is a function, that takes a function of type (A) -> B.

    By convention, people tend to use f as the variable name for functions like this
    */
    "Map over an optional" {

        assertSoftly {

            // uncomment, make this compile and pass the tests.

            val some1: Option<Int> = Option.Some(1)
            some1.map { it * 2 } shouldBe Option.Some(2)

            val optionalList: Option<List<Int>> = Option.Some(listOf(1, 2, 3, 4))
            optionalList.map { it.size } shouldBe Option.Some(4)

            // But what happens whe we map a function over None?
            val someNone: Option<Int> = Option.None
            someNone.map { it * 2 } shouldBe Option.None

            val noneList: Option<List<Int>> = Option.None
            noneList.map { it.size } shouldBe Option.None
        }
    }

    /*   ~~~~~ HINTS / TIPS  ~~~~~

    a) Add the following abstract function to the Option class:

    abstract fun <B> map(f: (A) -> B) : Option<B>

    ... and then implement it in your Some and None classes.

    b) f is the function you apply to the value in Option (in the case of Some!).

    c) If you have no value in the Option, then you can only reply with one thing, yup... None!

    If you're still stuck don't worry check the solution folder and see if it makes sense.
    Copy over the solution and move on.

    ~~~~~ TERMINOLOGY INTERLUDE ~~~~

    A function that takes or returns another function is known as a "Higher Order Function"!

    ~~~~~ WEIRD NAME INTERLUDE ~~~~

    If you got your map working, your Option data type is now a Functor! ... FUNC YEAH!

    Functor is a maths term from a field called Category Theory. The name might sound weird or scary, you might be
    put off when you come across these terms, they are usually simple concepts but with a very precise word...
    And you just made the hell outta one! Good work.

    ~~~~ WHY DO I NEED TO KNOW THAT?! ~~~~

    You don't! But the more you hang around the functional programmers, the more likely they are to mention these terms.

    Why do they use these complex sounding words? Because they accurately describe something.

    We could re-name them to something like "Mappable", "Mapper", or "Steve", but:
    * The mathematicians beat us to it when they discovered these concepts, so it's only right they get to name them.
    * There's already a wealth of knowledge which you can find when you search for these terms.

    Sorry Steve :(

    --------------------------------------------------------------------

    4.  FlatMap is another one you've probably seen before too.

        So lets implement it in our Option data type.

        FlatMap is also often called "bind" in other languages.
    */
    "FlatMap over an optional" {

        assertSoftly {

            // uncomment, make this compile and pass the tests.

            val some1: Option<Int> = Option.Some(1)
            some1.flatMap { num -> if (num % 2 == 0) Option.Some(num) else Option.None } shouldBe Option.None

            val some2: Option<Int> = Option.Some(2)
            some2.flatMap { num -> if (num % 2 == 0) Option.Some(num) else Option.None } shouldBe Option.Some(2)

            // But what happens whe we flatMap a function over None?
            val someNone: Option<Int> = Option.None
            someNone.flatMap { num -> if (num % 2 == 0) Option.Some(num) else Option.None } shouldBe Option.None

            // yo dawg I heard you liked Option, so we put an option in an option...
            val nested: Option<Option<Int>> = Option.Some(Option.Some(4))
            nested.flatMap { it } shouldBe Option.Some(4)
        }
    }

    /* ~~~~~ HINTS / TIPS  ~~~~~

    a) Add the following abstract function to Option:

    abstract fun <B> flatMap(f: (A) -> Option<B>): Option<B>

    ... and then implement it in your Some and None classes.

    b) f is the function you apply to value in Option, you don't need to wrap it in an Option constructor this time,
        because the function that is the parameter to flatMap will return an Option

    c) If you have nothing in the Option, then you can only reply with one thing, yup... None!

    ~~~~~ WEIRD NAME INTERLUDE ~~~~

    If you got your flatMap working, your Option data type is now a Monad! ... Wait, what was all the fuss about?!

    There's a little bit more to Monads than this, but this should be a good first introduction.

    Monad, is, you guessed it, another maths term from Category Theory.

    --------------------------------------------------------------------

    5. Let's filter some data out of your Option.
    */
    "Filter an optional" {

        assertSoftly {

            // uncomment, make this compile and pass the tests.

            val some1: Option<Int> = Option.Some(1)
            some1.filter { it % 2 == 0 } shouldBe Option.None

            val some2: Option<Int> = Option.Some(2)
            some2.filter { it % 2 == 0 } shouldBe Option.Some(2)
        }
    }

    /*
    No Hints and tips this time, you're on your own.

    But something to think about - notice how the filter tests are kinda doing the same thing as the flatMap.
    In the flatMap tests we returned None if the number was odd. Monads are pretty powerful!

    --------------------------------------------------------------------

    6. Fold is a function used to "break down" a data type to another single value, using the provided function.

    Again these are pretty powerful functions. You can do many things in a fold.... Except that!? Why would you even
    think that.
    */
    "Fold your option" {

        // uncomment, make this compile and pass the tests.

        val some2: Option<Int> = Option.Some(2)
        some2.fold(
            ifNone = { 0 },
            ifSome = { it * 4 }
        ) shouldBe 8
    }

    /*
    ~~~~~ WEIRD NAME INTERLUDE ~~~~
    A fold is a function that breaks down the structure of a data type using the supplied function, to a resulting
    single value. The single value can be of a different type than the original type contained within the type.

    A fold is a implementation of a Catamorphism.

    In Greek, "Cata" means 'down', and "morphism" means 'form' or 'type'.

    This gives some intuition about what a catamorphism does, it breaks DOWN the FORM/TYPE of our Option data type!

    In this example, we have a Option<A>, and we "break it down" to a type of B.

    N.B. 'B' could be anything, a List<String>, another Option<A>, or just a Int!

    This is the first function that returns a non Option type, however, you could still return an Option if you wanted.

    --------------------------------------------------------------------

    7. flatTap is like flatMap, but it ignores the result of the function that is passed to it.

    This is handy when you want to perform an action but you want to ignore the result, and keep what you had before.

    The test will hopefully explain it further.
     */

    "flatTap" {
         val flatTapped = Option.Some("Hello World").flatTap{ Option.Some(it.length) }

         flatTapped shouldBe Option.Some("Hello World")
    }

    /*

    ~~~~~ HINTS / TIPS  ~~~~~
    a) the function flatTap takes is of type  (A) -> Option<B>

    b) your flatTap will return: Option<B>

    --------------------------------------------------------------------

    You made it to the end of implementing tons of cool FP stuff in a Option data type! Great work.

    Next, let's put it to use and clean up some code spaghetti...

    Head over to src/test/Part2_UseOption.kt
    */
})
