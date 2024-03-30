package com.example.goespudd.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.example.goespudd.R
import com.example.goespudd.data.datasource.category.DummyCategoryDataSource
import com.example.goespudd.data.datasource.menu.DummyMenuDataSource
import com.example.goespudd.data.model.Category
import com.example.goespudd.data.model.Menu
import com.example.goespudd.data.repository.CategoryRepository
import com.example.goespudd.data.repository.CategoryRepositoryImpl
import com.example.goespudd.data.repository.MenuRepository
import com.example.goespudd.data.repository.MenuRepositoryImpl
import com.example.goespudd.databinding.FragmentHomeBinding
import com.example.goespudd.databinding.LayoutHeaderMenuBinding
import com.example.goespudd.presentation.detailmenu.DetailMenuActivity
import com.example.goespudd.presentation.home.adapter.CategoryAdapter
import com.example.goespudd.presentation.home.adapter.changelayoutmenu.OnItemClickedListener

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val headerMenuBinding: LayoutHeaderMenuBinding by lazy {
        LayoutHeaderMenuBinding.bind(binding.layoutHeaderMenu.root)
    }

    private var isUsingGridMode: Boolean = true

    private val viewModel: HomeViewModel by viewModels {
        val menuDataSource = DummyMenuDataSource()
        val menuRepository: MenuRepository = MenuRepositoryImpl(menuDataSource)
        val categoryDataSource = DummyCategoryDataSource()
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
        GenericViewModelFactory.create(HomeViewModel(categoryRepository, menuRepository))
    }

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter {

        }
    }

    private var menuAdapter: com.example.goespudd.presentation.home.adapter.changelayoutmenu.MenuAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCategoryList(viewModel.getCategory())
        bindMenuList(isUsingGridMode, viewModel.getMenu())
        setAction()
    }

    private fun setAction() {
        headerMenuBinding.ivLogoListMenu.setOnClickListener {
            isUsingGridMode = !isUsingGridMode
            setButtonImage(isUsingGridMode)
            bindMenuList(isUsingGridMode, viewModel.getMenu())
        }
    }

    private fun bindCategoryList(data: List<Category>) {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
        categoryAdapter.submitData(data)
    }

    private fun bindMenuList(isUsingGrid: Boolean, data: List<Menu>) {
        val listMode = if (isUsingGrid) com.example.goespudd.presentation.home.adapter.changelayoutmenu.MenuAdapter.MODE_GRID else com.example.goespudd.presentation.home.adapter.changelayoutmenu.MenuAdapter.MODE_LIST
        menuAdapter = com.example.goespudd.presentation.home.adapter.changelayoutmenu.MenuAdapter(
            listMode = listMode,
            listener = object : OnItemClickedListener<Menu> {
                override fun onItemClicked(item: Menu) {
                    //navigate to detail
                    navigateToDetailMenu(item)
                }
            })
        binding.rvMenu.apply {
            adapter = menuAdapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingGrid) 2 else 1)
        }
        menuAdapter?.submitData(data)
    }

    private fun navigateToDetailMenu(item: Menu) {
        val intent = Intent(requireContext(), DetailMenuActivity::class.java)
        intent.putExtra(DetailMenuActivity.EXTRA_MENU, item)
        startActivity(intent)
    }

    private fun setButtonImage(usingGridMode: Boolean) {
        headerMenuBinding.ivLogoListMenu.setImageResource(if (usingGridMode) R.drawable.img_logo_list1 else R.drawable.img_logo_list2)
    }

}