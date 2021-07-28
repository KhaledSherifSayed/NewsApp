package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

/**
 * Created by Meslmawy on 6/10/2021
 */

abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel>(
    private val layoutId: Int
) : AppCompatActivity() {

    protected abstract val viewModel: VM

    /**
     *  in case we needed to access the views
     */
    lateinit var binder: T

    private lateinit var loader: LoaderDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, layoutId)
        loader = LoaderDialog[this]
        setup()
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                baseRender(it)
            }
        }
    }

    abstract fun setup()

    private fun baseRender(state: ViewState) {
        when (state) {
            is ViewState.Initial -> hideLoading()
            is ViewState.Loading -> if (state.loading) showLoading() else hideLoading()
            is ViewState.Empty -> {/*empty response*/}
            is ViewState.Error -> showError(state)
            else -> {
                hideLoading()
                render(state)
            }
        }
    }

    abstract fun render(state: ViewState)

    fun showLoading() {
        loader.show()
    }

    fun hideLoading() {
        loader.hide()
    }

    fun showError(error: ViewState.Error) {
        hideLoading()
        val errorMessage = error.exception.errorMessage
            ?: run {
                return@run if (error.exception.errorMessageRes != null) {
                    getString(error.exception.errorMessageRes)
                } else null
            }
            ?: "Unexpected Error"
        if (errorMessage.isNotEmpty())
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}