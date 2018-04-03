import kefjs.Ef
import kefjs.prepareEf
import kotlin.browser.document

class QuickStart {
    @JsName("main")
    fun main() {
        val tpl = """
>h1
  .Hello World!
"""
        val HelloWorld = Ef.create(tpl)
        val HelloEf = """
>h1
  .Hello ef.js!
""".prepareEf()
        HelloWorld.newInstance().mount(document.body)
        HelloEf.newInstance().mount(document.body)
    }
}
