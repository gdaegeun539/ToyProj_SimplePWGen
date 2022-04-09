package kr.fndna.pw_generator

import android.content.Context
import android.util.Log
import java.io.File

class OptionsSaveLoadLogic(appContext: Context, var opt: BooleanArray) {
    val file = File(appContext.getExternalFilesDir(null), "options.dat")
    var optString = ""

    // 설정 저장
    fun saveChkSet() {
        var outputWriter = file.writer() // 스트림 열기
        optToStr()
        // 스트림 저장 및 닫기
        outputWriter.write(optString)
        outputWriter.flush()
        outputWriter.close()
    }

    fun loadChkSet(): BooleanArray {
        var inputReader = file.reader().buffered()
        optString = inputReader.readLine()
        return opt
    }

    // 배열 문자열화 - true/false가 문자열화됨
    private fun optToStr() {
        for (op in opt) {
            optString += op.toString()
        }
    }
    
    // 문자열 배열화
    private fun strToOpt() {
        var idx = 0
        for (it in optString.asIterable()) {
            Log.d("OptionsSaveLogic", it.toString())
//            opt[idx++] = java.lang.Boolean.valueOf(it.toString())
//            opt[idx++] = if ( it.toInt() ) true else false
        }
    }
}