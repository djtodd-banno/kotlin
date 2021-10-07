fun <T> T.f(E: (y: T) -> String): Boolean = E(this).isEmpty()

fun fu1() = (String).f { v -> "" }

fun box(): String {
    assertEquals(fu1(), true)
    return "OK"
}