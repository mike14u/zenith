package com.mikelau.zenith.dialogs

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import android.view.View
import com.mikelau.zenith.R
import com.mikelau.zenith.interfaces.AlertInterface
import com.mikelau.zenith.utils.AnimatorHelper

class DialogHelper(private val mContext: Context?) {

    private var mAlert: AlertDialog? = null

    /** AlertDialog with 1 option mainly for positive only **/
    @JvmOverloads
    fun getConfirmDialog(title: String, message: String, positive: String,
                         target: AlertInterface.SingleButton, enableOutsideTap: Boolean = true): AlertDialog? {

        mAlert = mContext?.let {
            AlertDialog.Builder(it, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive) { dialogInterface, i -> target.positiveMethod(dialogInterface, i) }.create()
        }

        AnimatorHelper.startSpringAnimation(mAlert?.window?.decorView!!, 0.70f)

        mAlert?.setCancelable(enableOutsideTap)
        mAlert?.setCanceledOnTouchOutside(false)

        if (enableOutsideTap) {
            mAlert?.setOnCancelListener { target.positiveMethod(mAlert!!, 0) }
        }

        return mAlert
    }

    /** AlertDialog with 2 options mainly for positive and negative **/
    @JvmOverloads
    fun getConfirmDialog(title: String, message: String, positive: String, negative: String,
                         target: AlertInterface.WithNoNeutral, enableOutsideTap: Boolean = true): AlertDialog? {

        mAlert = mContext?.let {
            AlertDialog.Builder(it, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive) { dialogInterface, i -> target.positiveMethod(dialogInterface, i) }
                .setNegativeButton(negative) { dialogInterface, i -> target.negativeMethod(dialogInterface, i) }.create()
        }

        AnimatorHelper.startSpringAnimation(mAlert?.window?.decorView!!, 0.70f)

        mAlert?.setCancelable(enableOutsideTap)
        mAlert?.setCanceledOnTouchOutside(false)

        if (enableOutsideTap) {
            mAlert?.setOnCancelListener { target.negativeMethod(mAlert!!, 0) }
        }

        return mAlert
    }

    /** AlertDialog with 3 options mainly for positive, negative, and neutral **/
    @JvmOverloads
    fun getConfirmDialog(title: String, message: String, positive: String, neutral: String, negative: String,
                         target: AlertInterface.WithNeutral, enableOutsideTap: Boolean = true): AlertDialog? {

        mAlert = mContext?.let {
            AlertDialog.Builder(it, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive) { dialogInterface, i -> target.positiveMethod(dialogInterface, i) }.setNeutralButton(neutral) { dialogInterface, i -> target.neutralMethod(dialogInterface, i) }.setNegativeButton(negative) { dialogInterface, i -> target.negativeMethod(dialogInterface, i) }.create()
        }

        AnimatorHelper.startSpringAnimation(mAlert?.window?.decorView!!, 0.70f)

        mAlert?.setCancelable(enableOutsideTap)
        mAlert?.setCanceledOnTouchOutside(false)
        mContext?.let { ContextCompat.getColor(it, R.color.color_gray_variant) }?.let { mAlert?.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(it) }

        if (enableOutsideTap) {
            mAlert?.setOnCancelListener { target.negativeMethod(mAlert!!, 0) }
        }

        return mAlert
    }

    private fun getCustomDialog(view: View, positive: String, negative: String,
                                target: AlertInterface.WithNoNeutral, enableOutsideTap: Boolean): AlertDialog? {

        mAlert = mContext?.let {
            AlertDialog.Builder(it, R.style.AlertDialogTheme)
                .setView(view)
                .setPositiveButton(positive) { dialogInterface, i -> target.positiveMethod(dialogInterface, i) }
                .setNegativeButton(negative) { dialogInterface, i -> target.negativeMethod(dialogInterface, i) }.create()
        }

        AnimatorHelper.startSpringAnimation(mAlert?.window?.decorView!!, 0.70f)

        mAlert?.setCancelable(enableOutsideTap)
        mAlert?.setCanceledOnTouchOutside(false)

        if (enableOutsideTap) {
            mAlert?.setOnCancelListener { target.negativeMethod(mAlert!!, 0) }
        }

        return mAlert
    }

    private fun getSingle(view: View, positive: String,
                         target: AlertInterface.SingleButton, enableOutsideTap: Boolean): AlertDialog? {

        mAlert = mContext?.let {
            AlertDialog.Builder(it, R.style.AlertDialogTheme)
                    .setView(view)
                    .setPositiveButton(positive) { dialogInterface, i -> target.positiveMethod(dialogInterface, i) }.create()
        }

        AnimatorHelper.startSpringAnimation(mAlert?.window?.decorView!!, 0.70f)

        mAlert?.setCancelable(enableOutsideTap)
        mAlert?.setCanceledOnTouchOutside(false)

        if (enableOutsideTap) {
            mAlert?.setOnCancelListener { target.positiveMethod(mAlert!!, 0) }
        }

        return mAlert
    }

    /** Legacy Support **/
    @JvmOverloads
    fun getCustomDialog(view: View, positive: String, negative: String, target: AlertInterface.WithNoNeutral): AlertDialog? {
        return getCustomDialog(view, positive, negative, target, true)
    }
    /** Legacy Support **/

    @JvmOverloads
    fun getCustomSingle(view: View, positive: String, target: AlertInterface.SingleButton, cancellable: Boolean): AlertDialog? {
        return getSingle(view, positive, target, cancellable)
    }

    @JvmOverloads
    fun getCustomDouble(view: View, positive: String, negative: String, target: AlertInterface.WithNoNeutral, cancellable: Boolean): AlertDialog? {
        return getCustomDialog(view, positive, negative, target, cancellable)
    }

    @JvmOverloads
    fun dismiss() {
        if (mAlert != null)
            mAlert?.dismiss()
    }

}
