package kr.fndna.pw_generator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kr.fndna.pw_generator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /** chkset 배열 확인 요소
     * 0번 특수문자 1번 숫자 2번 소문자 3번 대문자 4번 비슷한거제외 5번 모호한거제외 */
    var chkSet = BooleanArray(6)
    var pwdLen: Long = 0
    var makeString: String = ""
    lateinit var binding :ActivityMainBinding
    lateinit var logic :GenerateLogic
    lateinit var optSaveLoader: OptionsSaveLoadLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        optSaveLoader = OptionsSaveLoadLogic(this, chkSet)

        // 스위치 리스너 로직
        switchListener()

        // 복사 버튼 클립보드 복사 로직
        binding.copyBtn.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val txt: CharSequence = binding.passwordText.text // Editable 타입
            val clip = ClipData.newPlainText(txt, txt)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, R.string.clipboard_complete, Toast.LENGTH_SHORT).show()
        }

        // 생성 버튼 리스너 로직
        binding.generateBtn.setOnClickListener {
            var pwdLenStr = binding.inputLength.text.toString()
            if (pwdLenStr.isEmpty()) { // 혹시나 길이 입력 안 할수 있음
                Toast.makeText(this, R.string.pattern_not_match, Toast.LENGTH_SHORT).show()
            } else {
                pwdLen = binding.inputLength.text.toString().toLong() // 길이
                logic = GenerateLogic(chkSet, pwdLen) // 객체 생성
                makeString = logic.generate() // 문자열 생성

                when (makeString.isEmpty()) {
                    true -> { // 길이나 옵션 안맞았을때
                        Toast.makeText(this, R.string.pattern_not_match, Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        binding.passwordText.setText(makeString)
                        Toast.makeText(this, R.string.generate_successful, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // 툴바 초기화
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        applicationContext
        //todo 내장 접근 밑 저장 로직 수행
        return true
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