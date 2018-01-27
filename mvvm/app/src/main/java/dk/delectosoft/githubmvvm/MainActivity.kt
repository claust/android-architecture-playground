package dk.delectosoft.githubmvvm


import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import dk.delectosoft.githubmvvm.databinding.ActivityMainBinding
import org.jetbrains.anko.design.snackbar
import timber.log.Timber
import java.util.*


class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 123

    // Choose authentication providers
    private val providers: List<AuthUI.IdpConfig> = Arrays.asList(
            AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.user.observe(this, Observer {
            Timber.d("observe")
            if (it != null) {
                Timber.d("Binding user")
                binding.user = it
                snackbar(binding.root, "Welcome back, ${it.displayName}")

            } else {
                snackbar(binding.root, "Welcome back, stranger")
            }
        })

        if (!loggedIn) {
            login()
        }
    }

    private val loggedIn: Boolean
        get() = FirebaseAuth.getInstance().currentUser != null

    private fun login() {
        Timber.d("login")
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                snackbar(binding.root, "Welcome ${user?.displayName}")

            } else {
                // Sign in failed, check response for error code

            }
        }
    }
}
