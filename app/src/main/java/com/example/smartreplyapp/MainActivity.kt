package com.example.smartreplyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.smartreply.FirebaseTextMessage
import com.google.firebase.ml.naturallanguage.smartreply.SmartReplySuggestionResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {
//lateinit var firebaseAnalytics: FirebaseAnalytics
private var conversion = ArrayList<FirebaseTextMessage>()

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        btn_send.setOnClickListener {
            val msg = Utils.getText(ed_inputText)
                if(Utils.validate(msg)){
//                    logEvent("User Send Message")
                   senderText.text= msg
                    conversion.add(FirebaseTextMessage.createForRemoteUser(msg,System.currentTimeMillis(),"Hassan"))
                    smartReply()
                }else{
                    showToast("Please Fill the input Feilds")
                }
        }
    }
    private fun smartReply(){
    FirebaseNaturalLanguage.getInstance().smartReply.suggestReplies(conversion)

            .addOnSuccessListener { result ->
                if (result.getStatus() == SmartReplySuggestionResult.STATUS_NOT_SUPPORTED_LANGUAGE) {
                    // The conversation's language isn't supported, so the
                    // the result doesn't contain any suggestions.
                } else if (result.getStatus() == SmartReplySuggestionResult.STATUS_SUCCESS) {
                   var reply = ""
                    for(suggestion in result.suggestions){
                        reply +=suggestion.text + " "
                    }
                    receiverText.text = reply
                }
            }
            .addOnFailureListener {
                // Task failed with an exception
                // ...
                showToast(it.message.toString())
            }
    }


//    private fun logEvent(eventName: String) {
//        val bundle = Bundle()
//        bundle.putString("Event", "Hassan")
//        firebaseAnalytics.logEvent(eventName, bundle)
//    }
}