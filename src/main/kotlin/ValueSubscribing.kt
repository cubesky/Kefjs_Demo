import kefjs.Ef
import kefjs.instanceEf
import kefjs.prepareEf
import kotlin.browser.document

class ValueSubscribing {
    lateinit var subscribe :Ef.MethodFunction1
    lateinit var unsubscribe :Ef.MethodFunction1
    @JsName("main")
    fun main() {
        val demo = """
>div
  .Type something here:
  >br
  >textarea
    #rows = 5
    #cols = 40
    %value = {{inputVal = 1234567890&nabcdefg}}
  >br
  >button
    @click = toggle
    .{{caption = Subscribe}}
  >br
  .Reversed output:
  >pre
    .{{reversed}}
""".instanceEf()
        val reverse = object : Ef.MethodFunction2 {
            override fun call(state: Ef, value: String) {
                state.data("reversed").set(value.reversed())
            }
        }
        subscribe = object : Ef.MethodFunction1 {
            override fun call(state: Ef) {
                state.subscribe("inputVal", reverse)
                state.setMethod("toggle", unsubscribe)
                state.data("caption").set("Unsubscribe")
            }
        }
        unsubscribe = object : Ef.MethodFunction1 {
            override fun call(state: Ef) {
                state.unsubscribe("inputVal", reverse)
                state.setMethod("toggle", subscribe)
                state.data("caption").set("Subscribe")
            }
        }
        demo.setMethod("toggle", subscribe)
        demo.mount(document.body, Ef.EfOption.APPEND)
    }
}
