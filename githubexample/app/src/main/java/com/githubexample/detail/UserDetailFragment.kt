package com.githubexample.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.githubexample.R
import com.githubexample.data.GitHubUserDetails
import com.githubexample.databinding.FragmetUserDetailBinding

class UserDetailFragment : Fragment() {

    private var _binding: FragmetUserDetailBinding? = null
    val binding: FragmetUserDetailBinding get() = _binding!!
    private val args: UserDetailFragmentArgs by navArgs()


    val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmetUserDetailBinding.inflate(inflater, container, false).apply {
            _binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserDetails(args.username)
        binding.apply {
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        viewModel.userDetail.observe(viewLifecycleOwner) {
            updateUI(it)
        }


    }

    fun updateUI(userDetail: GitHubUserDetails) {
        binding.apply {
            Glide.with(binding.root.context)
                .load(userDetail.avatar_url)
                .circleCrop()
                .into(avatarImageView)
            nameTextView.text = userDetail.name
            locationTextView.text = userDetail.location
            followersTextView.text = getString(R.string.followers, userDetail.followers)
            followingTextView.text = getString(R.string.following, userDetail.following)

            bioTextView.isGone = userDetail.bio.isNullOrEmpty()
            bioTextView.text = userDetail.bio

            company.isGone = userDetail.company.isNullOrEmpty()
            company.text = userDetail.company

            repositoryTextView.text = userDetail.public_repos.toString()
            gistTextView.text = userDetail.public_gists.toString()
            updateDateTextView.text = userDetail.updated_at
        }
    }
}