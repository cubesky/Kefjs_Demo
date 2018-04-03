import kefjs.instanceEf
import kotlin.browser.document

class TwoWayBinding {
    @JsName("main")
    fun main() {
"""
>div
  >h2
    .Welcome, {{name = Yukino}}!
  .Input your name here:
  >input
    %value = {{name}}
""".instanceEf().mount(document.body)
"""
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
    /#disabled
""".instanceEf().mount(document.body)
    }
}
