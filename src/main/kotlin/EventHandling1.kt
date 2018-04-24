import kefjs.Ef
import kefjs.instanceEf
import kotlin.browser.document

class EventHandling1 {
    @JsName("main")
    fun main() {
"""
>button
  @click = add
  .You have clicked {{count}} time{{s}}.
""".instanceEf().apply {
    this.data("count").set(0)
    this.setMethod("add", object : Ef.MethodFunction1 {
        override fun call(state: Ef) {
            state.data("count").set((state.data("count").get() as Int) + 1)
            if ((state.getData("count") as Int) > 1) state.data("s").set("s")
        }
    })
}.mount(document.body)
    }
}
