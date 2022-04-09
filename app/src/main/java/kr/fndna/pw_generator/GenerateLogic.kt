package kr.fndna.pw_generator

import android.content.Context
import android.util.Log

class GenerateLogic(chkSet: BooleanArray, ln: Long) {
    var len: Long = ln
    var opt: BooleanArray = chkSet
    var allowedChars: MutableList<Char> = mutableListOf()
    var makeString: String = ""

    // 생성 로직
    fun generate(): String {
        Log.d("GenerateLogic", "$len")
        Log.d("GenerateLogic", "${opt[0]} / ${opt[1]}")
        if (len in 6..129) { // 길이 맞으면 수행
            patternCheck()
            Log.d("GenerageLogic", "${allowedChars.isEmpty()}")
            if (allowedChars.isNotEmpty()) { // 조건 하나라도 체크하면 수행
                for (it in 0..len) { // 생성
                    makeString += allowedChars.random()
                }
            }
        } // 길이 안 맞거나 조건 체크 안하면 빈 문자열 반환
        return makeString
    }

    // 설정값에 따라 문자 패턴 정함
    private fun patternCheck() {
        if (opt[0]) { // 특수문자 포함
            allowedChars.addAll("""(~!@#$%^&*()_+-={}[];':",./<>\|?)""".asSequence())
            if (opt[4]) { // 유사한 문자 제외
                allowedChars.remove('|')
            }
            if (opt[5]) { // 모호한 문자 제외
                allowedChars.removeAll("""{}[]()/\'"`~,;:.<>""".asSequence())
            }
        }
        if (opt[1]) { // 숫자 포함
            allowedChars.addAll('0'..'9')
            if (opt[4]) { // 유사한 숫자 제외
                allowedChars.remove('0')
                allowedChars.remove('1')
                allowedChars.remove('9')
            }
        }
        if (opt[2]) { // 소문자 포함
            allowedChars.addAll('a'..'z')
            if (opt[4]) { // 유사한 소문자 제외
                allowedChars.remove('o')
                allowedChars.remove('l')
                allowedChars.remove('g')
            }
        }
        if (opt[3]) { // 대문자 포함
            allowedChars.addAll('A'..'Z')
            if (opt[4]) { // 유사한 대문자 제외
                allowedChars.remove('O')
                allowedChars.remove('I')
            }
        }

    }

}