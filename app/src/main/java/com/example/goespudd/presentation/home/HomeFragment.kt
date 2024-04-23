package com.example.goespudd.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.catnip.firebaseauthexample.data.source.firebase.FirebaseService
import com.catnip.firebaseauthexample.data.source.firebase.FirebaseServiceImpl
import com.example.goespudd.R
import com.example.goespudd.data.datasource.authentication.AuthDataSource
import com.example.goespudd.data.datasource.authentication.FirebaseAuthDataSource
import com.example.goespudd.data.datasource.category.CategoryApiDataSource
import com.example.goespudd.data.datasource.category.CategoryDataSource
import com.example.goespudd.data.datasource.menu.MenuApiDataSource
import com.example.goespudd.data.datasource.menu.MenuDataSource
import com.example.goespudd.data.datasource.pref.PrefDataSource
import com.example.goespudd.data.datasource.pref.PrefDataSourceImpl
import com.example.goespudd.data.model.Category
import com.example.goespudd.data.model.Menu
import com.example.goespudd.data.repository.CategoryRepository
import com.example.goespudd.data.repository.CategoryRepositoryImpl
import com.example.goespudd.data.repository.MenuRepository
import com.example.goespudd.data.repository.MenuRepositoryImpl
import com.example.goespudd.data.repository.PrefRepository
import com.example.goespudd.data.repository.PrefRepositoryImpl
import com.example.goespudd.data.repository.UserRepository
import com.example.goespudd.data.repository.UserRepositoryImpl
import com.example.goespudd.data.source.local.pref.UserPreference
import com.example.goespudd.data.source.local.pref.UserPreferenceImpl
import com.example.goespudd.data.source.network.services.GoespuddApiService
import com.example.goespudd.databinding.FragmentHomeBinding
import com.example.goespudd.databinding.LayoutHeaderMenuBinding
import com.example.goespudd.presentation.detailmenu.DetailMenuActivity
import com.example.goespudd.presentation.home.adapter.CategoryAdapter
import com.example.goespudd.presentation.home.adapter.changelayoutmenu.MenuAdapter
import com.example.goespudd.presentation.home.adapter.changelayoutmenu.OnItemClickedListener
import com.example.goespudd.utils.GenericViewModelFactory
import com.example.goespudd.utils.proceedWhen

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val headerMenuBinding: LayoutHeaderMenuBinding by lazy {
        LayoutHeaderMenuBinding.bind(binding.layoutHeaderMenu.root)
    }

    private val viewModel: HomeViewModel by viewModels {
        val service = GoespuddApiService.invoke()
        val menuDataSource: MenuDataSource = MenuApiDataSource(service)
        val menuRepository: MenuRepository = MenuRepositoryImpl(menuDataSource)
        val categoryDataSource: CategoryDataSource = CategoryApiDataSource(service)
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)

        val userPreference: UserPreference = UserPreferenceImpl(requireContext())
        val prefDataSource: PrefDataSource = PrefDataSourceImpl(userPreference)
        val prefRepository: PrefRepository = PrefRepositoryImpl(prefDataSource)

        val firebaseService: FirebaseService = FirebaseServiceImpl()
        val userDataSource: AuthDataSource = FirebaseAuthDataSource(firebaseService)
        val userRepository: UserRepository = UserRepositoryImpl(userDataSource)
        GenericViewModelFactory.create(
            HomeViewModel(
                categoryRepository,
                menuRepository,
                prefRepository,
                userRepository
            )
        )
    }

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter {
            getMenuData(it.slug)
        }
    }

    private lateinit var menuAdapter: MenuAdapter

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
        setupListCategory()
        setAction()
        observeListMode()
        getCategoryData()
    }

    private fun getCategoryData(){
        viewModel.getCategory().observe(viewLifecycleOwner){getCategory->
            getCategory.proceedWhen(
                doOnSuccess = {
                    binding.tvError.isVisible = false
                    binding.piMenu.isVisible = false
                    it.payload?.let {data ->
                        bindCategoryList(data)
                    }
                },
                doOnLoading = {
                    binding.tvError.isVisible = false
                    binding.piMenu.isVisible = true
                },
                doOnError = {
                    binding.tvError.isVisible = true
                    binding.piMenu.isVisible = false
                }
            )
        }
    }

    private fun getMenuData(category: String? = null) {
        viewModel.getMenu(category).observe(viewLifecycleOwner){getCatalog->
            getCatalog.proceedWhen (
                doOnSuccess = {
                    binding.tvError.isVisible = false
                    binding.piMenu.isVisible = false
                    it.payload?.let{data->
                        bindMenuList(data)
                    }
                },
                doOnLoading = {
                    binding.tvError.isVisible = false
                    binding.piMenu.isVisible = true
                },
                doOnError = {
                    binding.tvError.isVisible = true
                    binding.piMenu.isVisible = false
                }
            )
        }
    }

    private fun setupListCategory() {
        binding.rvCategory.apply {
            adapter = this@HomeFragment.categoryAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }

    private fun setAction() {
        headerMenuBinding.ivLogoListMenu.setOnClickListener {
            viewModel.changeListMode()
        }
    }

    private fun observeListMode(){
        viewModel.isUsingGridMode.observe(viewLifecycleOwner){isUsingGridMode ->
            getMenuData(null)
            setGridOrList(isUsingGridMode)
            setListMenu(isUsingGridMode)
        }
    }

    private fun setGridOrList(isUsingListMode: Boolean){
        headerMenuBinding.ivLogoListMenu.setImageResource(
            if (isUsingListMode){
                R.drawable.img_logo_list2
            } else {
                R.drawable.img_logo_list1
            }
        )
    }

    private fun setListMenu(usingListMode: Boolean) {
        val listMode = if (usingListMode) {
            MenuAdapter.MODE_LIST
        } else {
            MenuAdapter.MODE_GRID
        }

        menuAdapter = MenuAdapter(
            listMode = listMode,
            listener = object: OnItemClickedListener<Menu> {
                override fun onItemClicked(item: Menu) {
                    navigateToDetailMenu(item)
                }
            }
        )

        binding.rvMenu.apply {
            adapter = this@HomeFragment.menuAdapter
            layoutManager = if (usingListMode){
                LinearLayoutManager(requireContext())
            } else {
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
        }

    }

    private fun bindCategoryList(data: List<Category>){
        categoryAdapter.submitData(data)
    }

    private fun bindMenuList(data: List<Menu>){
        menuAdapter.submitData(data)
    }

    private fun navigateToDetailMenu(item: Menu) {
        val intent = Intent(requireContext(), DetailMenuActivity::class.java)
        intent.putExtra(DetailMenuActivity.EXTRA_MENU, item)
        startActivity(intent)
    }
}