import kefjs.Ef
import kefjs.prepareEf
import kotlin.browser.document

class Mounting_Recursive {
    lateinit var addItem : Ef.MethodFunction1
    lateinit var removeItem : Ef.MethodFunction1
    @JsName("main")
    fun main() {
        val _item = """
>div.item
  .folder {{count = 0}}
  >button
    @click = addItem
    .+
  >button
    @click = removeItem
    .-
  +list
        """.prepareEf()
        addItem = object : Ef.MethodFunction1 {
            override fun call(state: Ef.EfInstance) {
                state.getKEf().editUserStore("ic", (state.getKEf().getUserStore<Int>("ic",0)) + 1)
                val ic = state.getKEf().getUserStore<Int>("ic",0)
                state.getKEf().listPush("list", _item.newInstance().apply {
                    this.setData("count", ic)
                    this.setMethod("addItem", addItem)
                    this.setMethod("removeItem", removeItem)
                })
            }
        }
        removeItem = object  : Ef.MethodFunction1 {
            override fun call(state: Ef.EfInstance) {
                state.getKEf().umount()
            }
        }
        _item.newInstance().apply {
            this.setMethod("addItem", addItem)
            this.setMethod("removeItem", removeItem)
        }.mount(document.body)

    }
}
