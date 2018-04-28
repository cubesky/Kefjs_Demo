import kefjs.Ef
import kefjs.kefconfig
import kefjs.prepareEf
import org.w3c.dom.events.Event
import kotlin.browser.document

class Mounting_Recursive {
    lateinit var addItem : (state: Ef, value: String, e: Event) -> Unit
    lateinit var removeItem : (state: Ef, value: String, e: Event) -> Unit
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
        addItem =  { state ,_, _ ->
            state.editUserStore("ic", (state.getUserStore("ic",0)) + 1)
            val ic = state.getUserStore<Int>("ic",0)
            state.list("list").push(_item.newInstance(kefconfig {
                data {
                    "count" setTo ic
                }
                methods {
                    "addItem" bind addItem
                    "removeItem" bind removeItem
                }
            }))
        }
        removeItem =  { state ,_ , _  ->
            state.umount()
        }
        _item.newInstance(kefconfig {
            methods {
                "addItem" bind addItem
                "removeItem" bind removeItem
            }
        }).mount(document.body)

    }
}
