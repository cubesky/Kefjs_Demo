import kefjs.Ef
import kefjs.kefconfig
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
            override fun invoke(state: Ef) {
                state.editUserStore("ic", (state.getUserStore<Int>("ic",0)) + 1)
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
        }
        removeItem = object  : Ef.MethodFunction1 {
            override fun invoke(state: Ef) {
                state.umount()
            }
        }
        _item.newInstance(kefconfig {
            methods {
                "addItem" bind addItem
                "removeItem" bind removeItem
            }
        }).mount(document.body)

    }
}
