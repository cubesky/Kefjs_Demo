import kefjs.kefconfig
import kefjs.kefhook
import kefjs.prepareEf
import kotlin.browser.document

class KefExp_Mount {
    @JsName("main")
    fun main() {
        val tpl = """
>h1
  .Hello Ef
  -mountpoint
""".prepareEf(kefhook {
            mount { func, that ->
                println("Before Mount Root")
                func()
                println("After Mount Root")
            }
        }).newInstance(kefconfig {
            onMount {
                println("On Mount Root")
            }
        })
        tpl.mount(document.body)
        val tplm = """
>h2
  .Mounting
""".prepareEf(kefhook {
            mount { func, that ->
                println("Before Mount Point")
                func()
                println("After Mount Point")
            }
        }).newInstance(kefconfig {
            onMount {
                println("On Mount Point")
            }
        })
        tpl.mount("mountpoint", tplm)
    }
}