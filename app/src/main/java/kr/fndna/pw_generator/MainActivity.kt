package kr.fndna.pw_generator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kr.fndna.pw_generator.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    var chkSet = BooleanArray(6)
    lateinit var binding :ActivityMainBinding
    val logic :GenerateLogic = GenerateLogic(chkSet)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 스위치 리스너 로직
        switchListener()

        // 복사 버튼 클립보드 복사 로직
        binding.copyBtn.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val txt: CharSequence = binding.passwordText.text // Editable
            val clip = ClipData.newPlainText(txt, txt)
            clipboard.setPrimaryClip(clip)
        }

        // 생성 버튼 리스너 로직
        binding.generateBtn.setOnClickListener {
            logic.opt = chkSet
            logic.generate()
        }
    }

    // 스위치 리스너 로직
    private fun switchListener(){
        // 기호 포함 체크
        binding.symbolSwitch.setOnCheckedChangeListener { compoundButton, b ->
            chkSet[0] = b
        }
        // 숫자 포함 체크
        binding.numberSwitch.setOnCheckedChangeListener { compoundButton, b ->
            chkSet[1] = b
        }
        // 소문자 포함 체크
        binding.lowerSwitch.setOnCheckedChangeListener { compoundButton, b ->
            chkSet[2] = b
        }
        // 대문자 포함 체크
        binding.upperSwitch.setOnCheckedChangeListener { compoundButton, b ->
            chkSet[3] = b
        }
        // 유사한 문자 제외 체크
        binding.similarSwitch.setOnCheckedChangeListener { compoundButton, b ->
            chkSet[4] = b
        }
        // 모호한 문자 제외 체크
        binding.ambigSwitch.setOnCheckedChangeListener { compoundButton, b ->
            chkSet[5] = b
        }
    }
}