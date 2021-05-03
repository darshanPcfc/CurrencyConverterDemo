package com.example.currencyconverterdemo.base

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.currencyconverterdemo.R


/**
 * Created by Darshan Patel
 * Usage: abstract base fragment for all application fragments
 * How to call: extend it with your fragment
 * Useful parameter: Pass your databinding varaible and veiwmodel object
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    var baseActivity: BaseActivity<*, *>? = null
        private set
    private var mRootView: View? = null
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    //for navigation from navgraph
    lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.baseActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        setHasOptionsMenu(false)
    }

    /*binding UI for fragments done here */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding?.root
        return mRootView
    }

    //when fragments are detached destroying baseActivity object to avoid memory leak
    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    //binding veiwmodel and bindingVariable
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.setVariable(bindingVariable, mViewModel)
        viewDataBinding?.lifecycleOwner = this
        viewDataBinding?.executePendingBindings()
        navController = Navigation.findNavController(view)
    }

    fun displayFailureAlert(title: String,message : String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(getString(R.string.str_dismiss),
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    //hide keyboard
    fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity?.hideKeyboard()
        }
    }

    //show keyboard
    fun showKeyboard() {
        if (baseActivity != null) {
            baseActivity?.showKeyboard()
        }
    }

    fun openActivityOnTokenExpire() {
        if (baseActivity != null) {
            baseActivity?.openActivityOnTokenExpire()
        }
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}