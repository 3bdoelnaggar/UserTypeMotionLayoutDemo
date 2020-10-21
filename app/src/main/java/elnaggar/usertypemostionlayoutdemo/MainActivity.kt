package elnaggar.usertypemostionlayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

class MainFragment : Fragment(R.layout.main_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.next).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, SecondFragment.newInstance("", ""))?.addToBackStack(null)?.commit()

        }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, MainFragment()).addToBackStack(null)
            .commit()
    }
}