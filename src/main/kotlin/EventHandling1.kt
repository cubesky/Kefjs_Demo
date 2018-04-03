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
    this.setData("count", 0)
    this.setMethod("add", object : Ef.MethodFunction1 {
        override fun call(state: Ef.EfInstance) {
            state.getKEf().setData("count", (state.getKEf().getData("count") as Int) + 1)
            if ((state.getKEf().getData("count") as Int) > 1) state.getKEf().setData("s", "s")
        }
    })
}.mount(document.body)
    }
}
