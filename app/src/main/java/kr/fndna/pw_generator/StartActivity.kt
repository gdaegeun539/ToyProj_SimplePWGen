package kr.fndna.pw_generator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed

class StartActivity : AppCompatActivity() {
    lateinit var backPressedLogic: BackPressedLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        backPressedLogic = BackPressedLogic(this)

        // handler 사용해 로고 보여주는 초 지연
        Handler(Looper.getMainLooper()).postDelayed(2000){
            var intent = Intent(applicationContext, MainActivity::class.java) // main으로 가자

            if (!isFinishing) { // 스타트 액티비티 끝나면 인텐트 안던짐
                startActivity(intent)
            }
        }

    }

    override fun onBackPressed() {
        backPressedLogic.onBackPressed()
    }
}