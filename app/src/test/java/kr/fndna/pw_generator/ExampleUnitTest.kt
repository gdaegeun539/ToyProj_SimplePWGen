package kr.fndna.pw_generator

import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var inputBoolArr = booleanArrayOf(true, true, false, false, false, true)
    var optionsSaveLoadLogic = OptionsSaveLoadLogic(null, inputBoolArr)

    // 배열 문자열화 및 재배열화 테스트
    @Test
    fun isStrOptCorrect() {
        optionsSaveLoadLogic.optToStr()
        var optArr = optionsSaveLoadLogic.strToOpt()

//        var temp = 0
        for ((idx, it) in optArr.withIndex()) {
//            Assertions.assertThat(idx).isEqualTo(temp)
//            temp++
            Assertions.assertThat(it).isEqualTo(inputBoolArr[idx])
        }
    }
}