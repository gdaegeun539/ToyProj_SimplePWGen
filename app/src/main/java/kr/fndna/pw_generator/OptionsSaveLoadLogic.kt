package kr.fndna.pw_generator

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class OptionsSaveLoadLogic(appContext: Context?, var opt: BooleanArray) {
    val file = File(appContext?.getExternalFilesDir(null), "options.dat")
    var optString: String? = ""

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
        
        // 스트림에서 불러오기 및 파싱
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
        // 혹시 파일이 비어있을 수 있음 - 파일이 비어있지 않을 때만 파싱 후 할당
        if (!optString.isNullOrEmpty()) {
            // 돌면서 넣어줌
            for ((idx, it) in optString?.trimEnd()?.split(" ")?.withIndex()!!) {
                Log.d("OptionsSaveLoadLogic", "$idx")
                opt[idx] = it.toBoolean()
            }
        }
        
        return opt
    }
}