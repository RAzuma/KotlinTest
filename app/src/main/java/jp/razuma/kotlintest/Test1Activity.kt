package jp.razuma.kotlintest

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import jp.razuma.kotlintest.databinding.ActivityMainBinding
import jp.razuma.kotlintest.databinding.ActivityTest1Binding
import java.util.*

class Test1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTest1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test1)

        binding.btn.setOnClickListener {
            binding.fragmentContainer.visibility = View.VISIBLE
            binding.frame.visibility = View.VISIBLE
        }

        binding.fragmentContainer.setOnTouchListener { view, motionEvent ->
            true
        }

        binding.frame.setOnTouchListener { view, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_DOWN){
                binding.fragmentContainer.visibility = View.INVISIBLE
                binding.frame.visibility = View.INVISIBLE
            }

            true
        }

        val firstFragment = Test1Fragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, firstFragment)
        fragmentTransaction.commit()

        binding.fragmentContainer.visibility = View.INVISIBLE
        binding.frame.visibility = View.INVISIBLE
    }
}