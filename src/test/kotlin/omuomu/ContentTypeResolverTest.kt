package omuomu

import java.lang.reflect.Method

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals

import omuomu.ContentTypeResolver

public class ContentTypeResolverTest {

	@Test
	fun testHtml() {
		assertEquals(ContentTypeResolver().getContentType("index.html"), "text/html")
		assertEquals(ContentTypeResolver().getContentType("index.htm"), "text/html")
		assertEquals(ContentTypeResolver().getContentType("INDEX.HTML"), "text/html")
		assertEquals(ContentTypeResolver().getContentType("INDEX.htm"), "text/html")
		assertEquals(ContentTypeResolver().getContentType("/foo/bar/index.html"), "text/html")
		assertEquals(ContentTypeResolver().getContentType("/foo/bar/index.htm"), "text/html")
	}

	@Test
	fun testCss() {
		assertEquals(ContentTypeResolver().getContentType("style.css"), "text/css")
		assertEquals(ContentTypeResolver().getContentType("STYLE.CSS"), "text/css")
	}

	@Test
	fun testJavaScript() {
		assertEquals(ContentTypeResolver().getContentType("script.js"), "text/javascript")
		assertEquals(ContentTypeResolver().getContentType("SCRIPT.JS"), "text/javascript")
	}

	@Test
	fun testJpegImage() {
		assertEquals(ContentTypeResolver().getContentType("image.jpeg"), "image/jpeg")
		assertEquals(ContentTypeResolver().getContentType("IMAGE.JPEG"), "image/jpeg")
		assertEquals(ContentTypeResolver().getContentType("image.jpg"), "image/jpeg")
		assertEquals(ContentTypeResolver().getContentType("IMAGE.JPG"), "image/jpeg")
	}

	@Test
	fun  testPngImage() {
		assertEquals(ContentTypeResolver().getContentType("image.png"), "image/png")
		assertEquals(ContentTypeResolver().getContentType("IMAGE.PNG"), "image/png")
	}

	@Test
	fun  testPlainText() {
		assertEquals(ContentTypeResolver().getContentType("test.txt"), "text/plain")
		assertEquals(ContentTypeResolver().getContentType("TEST.TXT"), "text/plain")
	}

	// @Test
	// fun  testGetFileExtension() {
	// 	val getFileExtension: Method = ContentTypeResolver().class.getDeclaredMethod("getFileExtension", String.class)
	// 	val getFileExtension.setAccessible(true)

	// 	assertEquals(getFileExtension.invoke(null, "index.html"),"html")
	// 	assertEquals(getFileExtension.invoke(null, "INDEX.HTML"),"html")

	// 	assertEquals(getFileExtension.invoke(null, "index./html"), "")
	// }

}