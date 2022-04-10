package kr.fndna.pw_generator

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class OptionsSaveLoadLogic(appContext: Context?, var opt: BooleanArray) {
    val file = File(appContext?.getExternalFilesDir(null), "options.dat")
    var optString = ""

    // 설정 저장
    fun saveChkSet() {
        // 스트림 열기 - 덮어쓰기 모드
        var outputWriter = OutputStreamWriter(FileOutputStream(file, false))
        optToStr()
        // 스트림 저장 및 닫기
        outputWriter.write(optString)
        outputWriter.flush()
        outputWriter.close()
    }

    fun loadChkSet(): BooleanArray {
        var inputReader = file.reader().buffered()
        optString = inputReader.readLine()

        opt = strToOpt()
        return opt
    }

    // 배열 문자열화 - true/false 가 문자열화됨
    fun optToStr() {
        optString = ""
        // 돌면서 더해줌
        for (op in opt) {
            optString += "$op "
        }
    }
    
    // 문자열 배열화
    fun strToOpt(): BooleanArray {
        // 돌면서 넣어줌
        for ((idx, it) in optString.trimEnd().split(" ").withIndex()) {
            Log.d("OptionsSaveLoadLogic", "$idx")
            opt[idx] = it.toBoolean()
        }
        return opt
    }
}