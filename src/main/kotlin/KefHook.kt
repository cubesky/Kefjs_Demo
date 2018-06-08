
import kefjs.kefconfig
import kefjs.kefhook
import kefjs.prepareEf
import kotlin.browser.document

class KefHook {
    @JsName("main")
    fun main() {
        val tpl = """
>h1
  .Hello Ef
  +mountpoint
""".prepareEf(kefhook {
            mount { func, that ->
                println("Before Mount")
                func()
                println("After Mount")
            }
        }).newInstance()
        tpl.mount(target = document.body)
        val mt = """
>p
  .Hello {{num}}
""".prepareEf(kefhook {
            mount { func, that ->
                println("Before List Mount ${that.data["num"]}")
                func()
                println("After List Mount ${that.data["num"]}")
            }
            umount { func, that ->
                println("Before List UMount ${that.data["num"]}")
                func()
                println("After List UMount ${that.data["num"]}")
            }
        })
        (0..9).forEach {
            tpl.list("mountpoint").push(mt.newInstance(kefconfig {
                data {
                    "num" setTo it
                }
            }))
        }
        tpl.list("mountpoint").remove(0)
        tpl.list("mountpoint").empty()
    }
}