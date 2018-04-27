import kefjs.Ef
import kefjs.instanceEf
import kefjs.kefconfig
import kefjs.prepareEf
import kotlin.browser.document

class EventHandling2 {
    @JsName("main")
    fun main() {
"""
>div
  .Input something here: &
  >input
    %value = {{inputVal = ef.js is awesome!}}
  . &
  >button
    @click = showMsg:The value been passed is "{{inputVal}}"
    .>>>And then click here<<<
""".instanceEf(kefconfig {
    methods {
        "showMsg" bind { state, value ->
            kotlin.browser.window.alert(value)
        }
    }
}).mount(document.body)
    }
}
