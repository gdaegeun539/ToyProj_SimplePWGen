package kr.fndna.pw_generator

import android.app.Activity
import android.util.Log
import android.widget.Toast

class BackPressedLogic constructor(val activity: Activity){
    val tost = Toast.makeText(activity, R.string.back_pressed, Toast.LENGTH_SHORT)
    var backPressedTime: Long = 0

    fun onBackPressed() {
        // 뒤 버튼 한번 누르면 or 누르고 2초 지나면 토스트 보여줌
        if (System.currentTimeMillis() > backPressedTime + 2000) {
            backPressedTime = System.currentTimeMillis()
            tost.show()
        } else{ // 2초 이내에 한번 더 누르면 끝
            activity.finishAffinity()
            tost.cancel()
        }
    }
}

