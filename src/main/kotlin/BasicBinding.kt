import kefjs.Ef
import kefjs.instanceEf
import kefjs.prepareEf
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date

class BasicBinding {
    @JsName("main")
    fun main() {
        Ef.inform()
        val Grad = """
>div.grid.{{type}}
  #style = transform: rotate({{deg}}deg);
""".prepareEf()
        val clock = """
>div
  >h2
    .{{hour = 00}}:{{minute = 00}}:{{second = 00}}:{{ms}}
  >div.analog
    +grads
    >div.hand.hour
      #style = transform: rotateZ({{h = 0}}deg) translateZ(0);
    >div.hand.minute
      #style = transform: rotateZ({{m = 0}}deg) translateZ(0);
    >div.hand.second
      #style = transform: rotateZ({{s = 0}}deg) translateZ(0);
    >div.pin
""".instanceEf()
        (0..29).forEach {
            clock.list("grads").push(Grad.newInstance().apply {
                this.data("deg").set(data = it * 6)
                this.data("type").set(if (it % 5 == 0) "long" else "short")
            })
        }
        getTime(clock)
        clock.mount(document.body, Ef.EfOption.APPEND)
        Ef.exec()
    }
    fun padZero(value : Int) : String {
        if (value < 10) return "0$value"
        return value.toString()
    }
    fun getTime(clock: Ef) {
        val date = Date()
        val h = date.getHours()
        val m = date.getMinutes()
        val s = date.getSeconds()
        val ms = date.getMilliseconds()
        clock.data("hour").set(padZero(h))
        clock.data("minute").set(padZero(m))
        clock.data("second").set(padZero(s))
        clock.data("h").set(h * 30 + m / 2 + s / 120 + ms / 1200000)
        clock.data("m").set(m * 6 + s / 10 + ms / 10000)
        clock.data("s").set(s * 6 + ms * 0.006)
        clock.data("ms").set(ms)
        window.requestAnimationFrame { getTime(clock) }
    }
}
