package com.example.goespudd.data.datasource.category

import com.example.goespudd.data.model.Category

class DummyCategoryDataSource: CategoryDataSource {
    override fun getCategory(): List<Category> {
        return mutableListOf(
            Category(
                name = "Nasi",
                imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/category_img/img_rice.jpeg?raw=true"
            ),
            Category(
                name = "Mie",
                imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/category_img/img_noddle.jpeg?raw=true"
            ),
            Category(
                name = "Snack",
                imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/category_img/img_snack.jpeg?raw=true"
            ),
            Category(
                name = "Minuman",
                imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/category_img/img_drink.jpeg?raw=true"
            ),
            Category(
                name = "Junk Food",
                imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/category_img/img_junkfood.jpeg?raw=true"
            ),
            Category(
                name = "Daging Ayam",
                imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/category_img/img_chicken_meat.jpeg?raw=true"
            ),
            Category(
                name = "Daging Sapi",
                imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/category_img/img_beef.jpeg?raw=true"
            ),
            Category(
                name = "Daging Bebek",
                imgUrl = "https://github.com/DenyIM/goespudd-assets/blob/main/assets/category_img/img_duck_meat.jpeg?raw=true"
            )
        )
    }
}