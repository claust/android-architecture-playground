package dk.delectosoft.githubmvvm

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import dk.delectosoft.githubmvvm.databinding.ContributorItemViewBinding
import dk.delectosoft.githubmvvm.models.Contributor


class ContributorAdapter : RecyclerView.Adapter<ContributorAdapter.MyViewHolder>() {
    val contributors = ArrayList<Contributor>()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contributors[position])
    }

    override fun getItemCount(): Int {
        return contributors.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ContributorItemViewBinding.bind(parent)
        return MyViewHolder(binding)
    }

    class MyViewHolder(private var binding: ContributorItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contributor: Contributor) {
            binding.contributor = contributor
        }
    }
}