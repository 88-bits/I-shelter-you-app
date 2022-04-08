package io.henrikhorbovyi.ishelteryou.intent

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.core.content.ContextCompat


fun Context.sendMessage(phoneNumber: String, message: String = "") {
    val uri = Uri.parse("smsto:$phoneNumber")
    Intent(Intent.ACTION_SENDTO, uri).apply {
        // putExtra("sms_body", "Here you can set the SMS text to be sent")
        flags = FLAG_ACTIVITY_NEW_TASK
        startActivity(Intent.createChooser(this, "Send message..."))
    }
}

fun Context.sendEmail(email: String) {
    val emailIntent = Intent(
        Intent.ACTION_SENDTO,
        Uri.fromParts("mailto", email, null)
    ).apply {
        flags = FLAG_ACTIVITY_NEW_TASK
        putExtra(Intent.EXTRA_SUBJECT, "Subject")
        putExtra(Intent.EXTRA_TEXT, "Body")
    }
    startActivity(Intent.createChooser(emailIntent, "Send email..."))
}

fun Context.openOnMap(address: String) {
    val gmmIntentUri = Uri.parse("geo:0,0?q=$address&z=10")
    Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
        flags = FLAG_ACTIVITY_NEW_TASK
        startActivity(Intent.createChooser(this, "Open with..."))
    }
}
