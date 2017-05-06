package lt.welovedotnot.ktu_ais_app.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.activity_main.*
import lt.welovedotnot.ktu_ais_app.R
import lt.welovedotnot.ktu_ais_app.db.User
import lt.welovedotnot.ktu_ais_app.startActivityNoBack

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn.setOnClickListener {

            if(!credentialsEmpty()) {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()

                User.login(username, password) { isSuccess ->
                    if (isSuccess) {
                        onProceed()
                    } else {
                        onFailure()
                    }
                }
            }
        }
    }

    fun onProceed() {
        startActivityNoBack(HomeActivity::class.java)
    }

    fun onFailure() {
        etPassword.setText("")
        Toast.makeText(this, "Error message", Toast.LENGTH_SHORT).show()
    }

    fun credentialsEmpty(): Boolean {
        if (etUsername.isEmpty() || etPassword.isEmpty()) {
            Toast.makeText(this, "Enter credentials", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    fun MaterialEditText.isEmpty() = this.text.toString().isEmpty()
}
