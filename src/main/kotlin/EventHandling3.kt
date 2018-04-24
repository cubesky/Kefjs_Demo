import kefjs.Ef
import kefjs.instanceEf
import kotlin.browser.document

class EventHandling3 {
    @JsName("main")
    fun main() {
"""
>div
  .{{msg = No message}} has been detected.
  >br
  >button
    @click = sendMsg:{{msg1}}
    .{{msg1 = Direct click}}
  >button
    @click.shift = sendMsg:{{msg2}}
    .{{msg2 = Hold SHIFT and click}}
  >button
    @click.shift.alt = sendMsg:{{msg3}}
    .{{msg3 = Hold SHIFT && ALT and then click}}
  >br
  .Press ENTER to send message: &
  >input
    %value = {{msg4 = Merry Christmas}}
    @keypress.13 = sendMsg:"{{msg4}}"
  >br
  .Press ENTER with CTRL key hold to send message: &
  >input
    %value = {{msg5 = Across the Great Wall we can reach every corner in the world}}
    @keypress.13.ctrl = sendMsg:"{{msg5}}"
  >br
  .You cannot type in "H", "J", "K", and "L" here (with IME off): &
  >input
    @keydown.72.74.75.76.prevent = sendMsg:{{msg6 = "You are not supposed to type in this letter."}}
    @keydown.72.74.75.76.shift.prevent = sendMsg:{{msg6}}
""".instanceEf().apply {
    this.setMethod("sendMsg", object : Ef.MethodFunction2 {
        override fun call(state: Ef, value: String) {
            state.data()["msg"] = value
        }
    })
}.mount(document.body)
    }
}
