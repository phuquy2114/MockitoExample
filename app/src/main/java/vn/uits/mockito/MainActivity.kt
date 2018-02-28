package vn.uits.mockito

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import vn.uits.mockito.model.SharedPreferenceEntry
import vn.uits.mockito.widget.EmailValidator
import java.util.*


class MainActivity : AppCompatActivity() {

    // Logger for this class.
    private val TAG = "MainActivity"

    // The helper that manages writing to SharedPreferences.
    private var mSharedPreferencesHelper: SharedPreferencesHelper? = null

    // The input field where the user enters his name.
    private var mNameText: EditText? = null

    // The date picker where the user enters his date of birth.
    private var mDobPicker: DatePicker? = null

    // The input field where the user enters his email.
    private var mEmailText: EditText? = null

    // The validator for the email input field.
    private var mEmailValidator: EmailValidator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Shortcuts to input fields.
        mNameText = findViewById<View>(R.id.userNameInput) as EditText
        mDobPicker = findViewById<View>(R.id.dateOfBirthInput) as DatePicker
        mEmailText = findViewById<View>(R.id.emailInput) as EditText

        // Setup field validators.
        mEmailValidator = EmailValidator()
        mEmailText!!.addTextChangedListener(mEmailValidator)

        // Instantiate a SharedPreferencesHelper.
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        mSharedPreferencesHelper = SharedPreferencesHelper(sharedPreferences)

        // Fill input fields from data retrieved from the SharedPreferences.
        populateUi()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    /**
     * Initialize all fields from the personal info saved in the SharedPreferences.
     */
    private fun populateUi() {
        val sharedPreferenceEntry: SharedPreferenceEntry
        sharedPreferenceEntry = mSharedPreferencesHelper!!.getPersonalInfo()
        mNameText!!.setText(sharedPreferenceEntry.name)
        val dateOfBirth = sharedPreferenceEntry.dateOfBirth
        mDobPicker!!.init(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH),
                dateOfBirth.get(Calendar.DAY_OF_MONTH), null)
        mEmailText!!.setText(sharedPreferenceEntry.email)
    }


    /**
     * Called when the "Save" button is clicked.
     */
    fun onSaveClick(view: View) {
        // Don't save if the fields do not validate.
        if (!mEmailValidator!!.isValid()) {
            mEmailText!!.setError("Invalid email")
            return
        }

        // Get the text from the input fields.
        val name = mNameText!!.getText().toString()
        val dateOfBirth = Calendar.getInstance()
        dateOfBirth.set(mDobPicker!!.getYear(), mDobPicker!!.getMonth(), mDobPicker!!.getDayOfMonth())
        val email = mEmailText!!.getText().toString()

        // Create a Setting model class to persist.
        val sharedPreferenceEntry = SharedPreferenceEntry(name, dateOfBirth, email)

        // Persist the personal information.
        val isSuccess = mSharedPreferencesHelper!!.savePersonalInfo(sharedPreferenceEntry)
        if (isSuccess) {
            Toast.makeText(this, "Personal information saved", Toast.LENGTH_LONG).show()
        } else {
            Log.e("xxx", "Failed to write personal information to SharedPreferences")
        }
    }

    /**
     * Called when the "Revert" button is clicked.
     */
    fun onRevertClick(view: View) {
        populateUi()
        Toast.makeText(this, "Personal information reverted", Toast.LENGTH_LONG).show()
    }
}
