package kr.fndna.pw_generator

class GenerateLogic(chkSet: BooleanArray) {
    var opt: BooleanArray = chkSet
    var user_settings: String = ""
    val chkArr: Array<String> = arrayOf("""
            (~!@#$%^&*()_+-={}[];':",./<>?\|)
        """.trimIndent() ,"(0-9)" ,"(A-Z)", "(a-z)", "유사문자", "모호문자")
    // TODO: 유사문자 모호문자 아직 없음

    fun generate(): String {
        patternCheck()
        TODO()
    }

    // 설정값에 따라
    fun patternCheck() {
        user_settings = ""
        for (i: Int in opt.indices) {
            if (opt[i]) {
                user_settings += chkArr[i]
            }
        }
    }

}