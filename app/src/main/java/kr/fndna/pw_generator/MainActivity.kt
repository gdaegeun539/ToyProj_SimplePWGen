package kr.fndna.pw_generator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kr.fndna.pw_generator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var chkSet = BooleanArray(7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 복사 버튼 클립보드 복사 로직
        binding.copyBtn.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val txt: CharSequence = binding.wherePassword.text // Editable
            val clip = ClipData.newPlainText(txt, txt)
            clipboard.setPrimaryClip(clip)
        }

        // 생성 버튼 리스너 로직
        binding.generateBtn.setOnClickListener {
            TODO("클래스 만들어서 구현하기")
        }
    }
}