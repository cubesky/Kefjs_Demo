import kefjs.Ef
import kefjs.instanceEf
import kefjs.prepareEf
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date

class Bundled_Rendering {
    @JsName("main")
    fun main() {
        Ef.inform()
        val Grad = """
>div.grad.{{type}}
  #style = transform: rotate({{deg}}deg);
""".prepareEf()
        val clock = """
>div
	>button
  	@click = inform
  	.inform()
  >button
  	@click = exec
  	.exec()
  >button
  	@click = exectrue
    .exec(true)
  >div.clock
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
  >div
  	.Input 1:
    >input
      %value = {{input1 = a}}
    >br
    .Input 2:
    >input
      %value = {{input2 = b}}
    >br
    .Input 3:
    >input
      %value = {{input3 = c}}
    >br
    .Mixed output:
    >input
      %value = 1:{{input1}} 2:{{input2}} 3:{{input3}}
      #disabled
""".instanceEf().apply {
            this.setMethod("inform", object : Ef.MethodFunction1 {
                override fun call(state: Ef.EfInstance) {
                    Ef.inform()
                }
            })
            this.setMethod("exectrue", object : Ef.MethodFunction1{
                override fun call(state: Ef.EfInstance) {
                    Ef.exec(true)
                }
            })
            this.setMethod("exec", object : Ef.MethodFunction1{
                override fun call(state: Ef.EfInstance) {
                    Ef.exec()
                }
            })
        }
        (0..29).forEach {
            clock.listPush("grads", Grad.newInstance().apply {
                this.setData("deg", it * 6)
                this.setData("type", if (it % 5 == 0) "long" else "short")
            })
        }
        getTime(clock)
        clock.mount(document.body)
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
        Ef.bundle(object : Ef.BunbleFunction{
            override fun call():Boolean {
                clock.setData("hour", padZero(h))
                clock.setData("minute", padZero(m))
                clock.setData("second", padZero(s))
                clock.setData("h", h * 30 + m / 2 + s / 120 + ms / 1200000)
                clock.setData("m", m * 6 + s / 10 + ms / 10000)
                clock.setData("s", s * 6 + ms * 0.006)
                clock.setData("ms", ms)
                return false
            }
        })
        window.requestAnimationFrame { getTime(clock) }
    }
}
