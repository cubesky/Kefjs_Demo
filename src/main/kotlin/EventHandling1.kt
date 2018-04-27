import kefjs.Ef
import kefjs.instanceEf
import kefjs.kefconfig
import kotlin.browser.document

class EventHandling1 {
    @JsName("main")
    fun main() {
"""
>button
  @click = add
  .You have clicked {{count}} time{{s}}.
""".instanceEf(kefconfig {
    data {
        "count" setTo 0
    }
    methods {
        "add" bind { state ->
            state.data["count"] = (state.data["count"] as Int) + 1
            if ((state.data["count"] as Int) > 1) state.data["s"] = "s"
        }
    }
}).mount(document.body)
    }
}
