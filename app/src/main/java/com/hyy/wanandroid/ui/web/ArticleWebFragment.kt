package com.hyy.wanandroid.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.hyy.wanandroid.base.BaseFragment
import com.hyy.wanandroid.databinding.FragmentArticleWebBinding
import com.just.agentweb.AgentWeb


class ArticleWebFragment : BaseFragment<FragmentArticleWebBinding>() {
    private lateinit var mAgentWeb : AgentWeb

    private val title : String? by lazy {
        arguments?.getString(TITLE, "")
    }

    private val url : String? by lazy {
        arguments?.getString(URL, "")
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentArticleWebBinding = FragmentArticleWebBinding.inflate(inflater, container, false)

    override fun setupViews() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(mBinding.toolbar)
        mBinding.toolbar.title = title
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(mBinding.webContainer, FrameLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(url)
    }

    override fun setupListeners() {
        mBinding.toolbar.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    override fun setupObservers() {
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    companion object {

        private const val TITLE = "title"
        private const val URL = "url"
        fun create(title: String, url: String) : ArticleWebFragment{
            return ArticleWebFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putString(URL, url)
                }
            }
        }
    }
}