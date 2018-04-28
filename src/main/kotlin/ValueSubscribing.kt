import kefjs.Ef
import kefjs.instanceEf
import kefjs.kefconfig
import kefjs.prepareEf
import org.w3c.dom.events.Event
import kotlin.browser.document

class ValueSubscribing {
    val reverse =  Ef.createFunc { state ,value ,_ ->
        state.data["reversed"] = value.reversed()
    }
    val subscribe: (state: Ef, value: String, e: Event) -> Unit = Ef.createFunc { state, _, _ ->
        state.subscribe("inputVal", reverse)
        state.setMethod("toggle", unsubscribe)
        state.data["caption"] = "Unsubscribe"
    }
    val unsubscribe : (state: Ef, value: String, e: Event) -> Unit = Ef.createFunc { state, _ ,_ ->
        state.unsubscribe("inputVal", reverse)
        state.setMethod("toggle", subscribe)
        state.data["caption"] = "Subscribe"
    }
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
""".instanceEf(kefconfig {
            methods {
                "toggle" bind subscribe
            }
        })
        demo.mount(document.body, Ef.EfOption.APPEND)
    }
}
