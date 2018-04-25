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
    this.data["count"] = 0
    this.setMethod("add", object : Ef.MethodFunction1 {
        override fun invoke(state: Ef) {
            state.data["count"] = (state.data["count"] as Int) + 1
            if ((state.data["count"] as Int) > 1) state.data["s"] = "s"
        }
    })
}.mount(document.body)
    }
}
