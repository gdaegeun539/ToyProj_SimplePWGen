package kr.fndna.pw_generator

class GenerateLogic(chkSet: BooleanArray) {
    var len: Int = 0
    var opt: BooleanArray = chkSet
    var allowedChars: MutableList<Char>? = null
    var makeString: String? = null
    //TODO: 유사문자 모호문자 아직 없음

    fun generate(): String? {
        patternCheck()
        for (it in 0..len) {
            makeString += allowedChars?.random()
        }
        return makeString
    }

    // 설정값에 따라
    private fun patternCheck() {
        if (opt[0]) { // 특수문자 포함
            allowedChars?.addAll("""(~!@#$%^&*()_+-={}[];':",./<>?\|)""".asSequence())
            if (opt[5]) { // 모호한 문자 제외
                allowedChars?.removeAll("""{}[]()/\'"`~,;:.<>""".asSequence())
            }
        }
        if (opt[1]) { // 숫자 포함
            allowedChars?.addAll('0'..'9')
            if (opt[4]) { // 유사한 숫자 제외
                allowedChars?.remove('0')
                allowedChars?.remove('1')
            }
        }
        if (opt[2]) { // 소문자 포함
            allowedChars?.addAll('a'..'z')
            if (opt[4]) { // 유사한 소문자 제외
                //todo
            }
        }
        if (opt[3]) { // 대문자 포함
            allowedChars?.addAll('A'..'Z')
            if (opt[4]) { // 유사한 대문자 제외
                //todo
            }
        }

    }

}