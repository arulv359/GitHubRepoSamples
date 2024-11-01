package com.githubApiCoding.ui.search

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.githubApiCoding.R
import com.githubApiCoding.dao.User
import com.githubApiCoding.ui.activity.MainActivity
import com.githubApiCoding.ui.ext.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.layout_progress.*
import javax.inject.Inject

const val QUERY_KEY = "QUERY_KEY"

/**
 * Search screen
 */
class SearchFragment : Fragment() {

    @Inject lateinit var factory: ViewModelFactory

    private lateinit var viewModel: SearchViewModel
    private val adapter = UserListAdapter { showUserDetails(it) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userList.initList(adapter, LinearLayoutManager.VERTICAL)
        //
        searchView.queryHint = getString(R.string.search_hint)
        searchView.isIconifiedByDefault = false
        searchView.setOnQueryTextListener(QueryTextListener {
            viewModel.search(it)
            activity?.hideKeyboard()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).mainComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        viewModel.state.observe(this, Observer { onStateChanged(it) })
        if (savedInstanceState == null) {
            viewModel.restoreLastQuery()
        } else {
            savedInstanceState.getString(QUERY_KEY)?.let { viewModel.search(it) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        with(viewModel.state.value) {
            if (this is UsersLoaded) outState.putString(QUERY_KEY, this.query)
        }
        super.onSaveInstanceState(outState)
    }

    private fun onStateChanged(state: ViewModelState?) = when (state) {
        is StateProgress -> progressView.progress()
        is SearchRestored -> searchView.setQuery(state.query, true)
        is UsersLoaded -> setUsers(state.users)
        is StateError -> showError(state.throwable, R.string.error_message_search) { progressView.empty() }
        else -> progressView.empty()
    }

    private fun setUsers(users: List<User>) = if (users.isEmpty()) {
        progressView.message()
        adapter.clearItems()
    } else {
        progressView.empty()
        adapter.setItems(users)
    }

    private fun showUserDetails(userId: Int) = activity?.showDetailsFragment(userId)

}

class QueryTextListener(private val action: (String) -> Unit) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query.isNotBlank()) action(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

}

