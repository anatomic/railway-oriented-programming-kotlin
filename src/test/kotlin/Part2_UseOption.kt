import ExecuteUseCaseWithOption.executeUseCase
import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

/*


WELCOME TO PART 2 :)

* Convert the code in "src/main/OptionalUseCase.kt" to use your Option type.

* The functions used by the usecase shoukd return option<...>, instead of booleans/throwing exceptions, etc.

* Uncomment the lines in the tests and make them pass!

If you get stuck, take a peek at the solution in solutions/ExecuteUseCaseFunctional.kt

*/
class Part2_UseOption : StringSpec({

    "valid requests" {
        val rows = listOf(222, 666).toRows()
        forall(*rows) { id: Int ->
            // UNCOMMENT THIS...
             executeUseCase(id) shouldBe Option.Some("OK")
        }
    }

    "invalid requests" {
        forall(*listOf(111, 333, 444, 555, 777).toRows()) { id: Int ->
            // UNCOMMENT THIS...
             executeUseCase(id) shouldBe Option.None
        }
    }

})

private fun <E> List<E>.toRows() = this.map(::row).toTypedArray()
