import kefjs.Ef
import kefjs.instanceEf
import kefjs.prepareEf
import kotlin.browser.document

class Mounting_Basic {
    @JsName("main")
    fun main() {
        Ef.inform()
        val main = """
>div
  .Mounting point 1:
  >br
  -mp1
  >br
  .Mounting point 2:
  >br
  -mp2
""".instanceEf()
        val Component = """
>div
  .This is component {{count}}.
""".prepareEf()
        val component1 = Component.newInstance().apply { this.setData("count", 1) }
        val component2 = Component.newInstance().apply { this.setData("count", 2) }
        main.subMount("mp1", component1)
        main.subMount("mp2", component2)
        main.mount(document.body)
        Ef.exec()
    }
}
