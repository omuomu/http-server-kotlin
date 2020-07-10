import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals
import omuomu.impl.HttpHeaderImpl

class HttpHeaderImplTest {
 
    @Test
    @DisplayName("Header exception handling")
    fun headerExcepition() {
        // Koltinではnullの例外処理がされるためコメントアウト
        // assertFailsWith<NullPointerException> { val test1 = HttpHeaderImpl("Host", null){} }
        // assertFailsWith<NullPointerException> { val test2 = HttpHeaderImpl(null, "www.dreamarts.co.jp"){} }
        val header1 = HttpHeaderImpl("Host", "www.dreamarts.co.jp")
        assertEquals(header1.getName(), "Host")
        assertEquals(header1.getValue(), "www.dreamarts.co.jp")

        // 8192バイト以下であること
        val buf = StringBuilder()
        for(i in 1..8192) {
            buf.append(".")
        }
        val str8192: String = buf.toString()
		val str8193: String = str8192 + "."

        val header2 = HttpHeaderImpl(str8192, str8192)

        assertEquals(header2.getName(), str8192)
        assertEquals(header2.getValue(), str8192)

        assertFailsWith<IllegalArgumentException> { HttpHeaderImpl("Host", str8193) }
        assertFailsWith<IllegalArgumentException> { HttpHeaderImpl(str8193, "www.dreamarts.co.jp") }
    }
}