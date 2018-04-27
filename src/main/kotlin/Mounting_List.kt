import kefjs.Ef
import kefjs.instanceEf
import kefjs.kefconfig
import kefjs.prepareEf
import kotlin.browser.document

class Mounting_List {
    @JsName("main")
    fun main() {
        val container = """
>div
  .Container:
  >ul
    +list
""".instanceEf()
        val Item = """
>li
  .Item {{count}}
""".prepareEf()
        (0..9).forEach {
            container.list("list").push(Item.newInstance(kefconfig {
                data {
                    "count" setTo it
                }
            }))
        }
        container.mount(document.body)
        Ef.exec()
    }
}
