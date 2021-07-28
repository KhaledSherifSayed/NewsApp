package com.ibtikar.mvvm_starter_koin_coroutines.utils.intents

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder


/**
 * opens an activity that share the same transitionName in xml attrs with
 * the starter activity
 */
fun <T> Fragment.openActivityWithTransition(
    to: Class<*>?,
    data: T? = null,
    vararg animatedViews: View,
    finish: Boolean = false,
    finishAffinity: Boolean = false
) {

    activity?.let { activity ->
        if (animatedViews.isNotEmpty()) {

            // get images and their transition names into a list
            val viewsAndTransitionNamesList: ArrayList<Pair<View, String>> = arrayListOf()
            for (view in animatedViews) {
                viewsAndTransitionNamesList.add(
                    Pair(view, (ViewCompat.getTransitionName(view) ?: "empty_transition_name"))
                )
            }

            // create the option
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                *viewsAndTransitionNamesList.toTypedArray()
            )

            val intent = Intent(activity.applicationContext, to)
            if (data != null) {
                intent.putExtraJson(data)
            }

            // start the activity
            activity.startActivity(
                intent,
                options.toBundle()
            )

            // finish if needed
            if (finishAffinity)
                activity.finishAffinity()
            if (finish)
                activity.finish()
        }
    }
}

/**
 *  open an Activity with the ability of finishing and finishAffinity
 *  From an Activity
 */
fun FragmentActivity.openActivity(
    to: Class<*>?,
    finish: Boolean = false,
    finishAffinity: Boolean = false,
    outerIntent: Intent? = null
) {
    val intent = Intent(this, to)
    outerIntent?.extras?.let {
        intent.putExtras(it)
    }
    startActivity(intent)

    if (finishAffinity)
        this.finishAffinity()
    if (finish)
        this.finish()
}

/**
 *  open an Activity with and return with result the ability of finishing and finishAffinity
 *  From an Activity
 */
fun FragmentActivity.openActivityForResult(
    to: Class<*>?, outerIntent: Intent? = null,
    launchCode: Int
) {
    val intent = Intent(this, to)
    outerIntent?.extras?.let {
        intent.putExtras(it)
    }
    startActivityForResult(intent, launchCode)
}

/**
 * opens an activity that share the same transitionName in xml attrs with
 * the starter activity
 */
fun FragmentActivity.openActivityWithTransition(
    to: Class<*>?,
    animatedView: View,
    finish: Boolean = false,
    finishAffinity: Boolean = false
) {

    startActivity(
        Intent(this, to),
        ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            animatedView,
            ViewCompat.getTransitionName(animatedView)!!
        ).toBundle()
    )

    if (finishAffinity)
        this.finishAffinity()
    if (finish)
        this.finish()
}

/**
 *  open an Activity with the ability of finishing and finishAffinity
 *  From a Fragment
 */

fun Fragment.openActivity(
    to: Class<*>?, finish: Boolean = false, finishAffinity: Boolean = false,
    outerIntent: Intent? = null
) {

    val intent = Intent(this.context, to)
    outerIntent?.extras?.let {
        intent.putExtras(it)
    }
    activity?.let {
        startActivity(intent)
        if (finishAffinity)
            it.finishAffinity()
        if (finish)
            it.finish()
    }
}


fun FragmentActivity.openAppInStore(appId: String) {
    if (appId.isNotEmpty()) {

        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(appId)
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}

object IntentUtil {
    @Suppress("SpellCheckingInspection")
    val gson: Gson = GsonBuilder().create()
}

fun Intent.putExtraJson(name: String, src: Any) {
    putExtra(name, IntentUtil.gson.toJson(src))
}

const val DEFAULT_NAME = "CONST_NAME_FOR_VALUES"

fun Intent.putExtraJson(src: Any) {
    putExtra(DEFAULT_NAME, IntentUtil.gson.toJson(src))
}

fun <T> Intent.getJsonExtra(name: String, `class`: Class<T>): T? {
    val stringExtra = getStringExtra(name)
    if (stringExtra != null) {
        return IntentUtil.gson.fromJson(stringExtra, `class`)
    }
    return null
}

fun <T> Intent.getJsonExtra(`class`: Class<T>): T? {
    val stringExtra = getStringExtra(DEFAULT_NAME)
    if (stringExtra != null) {
        return IntentUtil.gson.fromJson(stringExtra, `class`)
    }
    return null
}


inline fun <reified T : Any> Context.launchActivities(
    stackIntents: ArrayList<Intent>,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivities(ArrayList<Intent>().also {
        it.addAll(stackIntents)
        it.add(intent)
    }.toTypedArray())
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)